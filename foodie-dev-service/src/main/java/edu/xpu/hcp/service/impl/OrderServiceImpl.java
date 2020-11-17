package edu.xpu.hcp.service.impl;

import edu.xpu.hcp.bo.SubmitOrderBO;
import edu.xpu.hcp.enums.OrderStatusEnum;
import edu.xpu.hcp.enums.YesOrNo;
import edu.xpu.hcp.mapper.OrderItemsMapper;
import edu.xpu.hcp.mapper.OrderStatusMapper;
import edu.xpu.hcp.mapper.OrdersMapper;
import edu.xpu.hcp.pojo.*;
import edu.xpu.hcp.service.ItemService;
import edu.xpu.hcp.service.OrderService;
import edu.xpu.hcp.service.UserAddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public String createOrder(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();
        //包邮费用设置为0
        Integer postAmount = 0;
        //1、新订单数据保存
        String orderId = sid.nextShort();
        Orders order = new Orders();
        order.setId(orderId);
        order.setUserId(userId);
        UserAddress userAddress = userAddressService.queryUserAddress(userId, addressId);
        order.setReceiverName(userAddress.getReceiver());
        order.setReceiverMobile(userAddress.getMobile());
        order.setReceiverAddress(userAddress.getProvince()+"-"+userAddress.getCity()+"-"+userAddress.getDistrict()+"-" + userAddress.getDetail());
        order.setPostAmount(postAmount);
        order.setPayMethod(payMethod);
        order.setLeftMsg(leftMsg);
        order.setIsComment(YesOrNo.NO.type);
        order.setIsDelete(YesOrNo.NO.type);
        order.setCreatedTime(new Date());
        order.setUpdatedTime(new Date());
        //2、循环根据itemSpecIds保存订单商品信息
        String[] ids = itemSpecIds.split(",");
        Integer totalAmount = 0;
        Integer realAmount = 0;
        for (String id : ids) {
            //根据规格ID，查询规格的具体信息，主要获取价格
            ItemsSpec itemsSpec = itemService.queryItemSpecById(id);
            //TODO 整合Redis后，商品购买的数量重新从Redis的购物车中获取
            int buyCounts = 1;
            totalAmount += itemsSpec.getPriceNormal() * buyCounts;
            realAmount += itemsSpec.getPriceDiscount() * buyCounts;

            //根据规格ID获取商品信息和商品图片
            String itemId = itemsSpec.getItemId();
            Items item = itemService.queryItemById(itemId);
            String mainImgUrl = itemService.queryItemMainImgById(itemId);
            //循环保存子订单数据到数据库
            OrderItems subOrder = new OrderItems();
            String subOrderId = sid.nextShort();
            subOrder.setId(subOrderId);
            subOrder.setOrderId(orderId);
            subOrder.setItemId(itemId);
            subOrder.setItemName(item.getItemName());
            subOrder.setItemImg(mainImgUrl);
            subOrder.setBuyCounts(buyCounts);
            subOrder.setItemSpecId(id);
            subOrder.setItemSpecName(itemsSpec.getName());
            subOrder.setPrice(itemsSpec.getPriceDiscount());
            orderItemsMapper.insert(subOrder);

            //规格表扣除库存
            itemService.decreaseItemSpecStock(id,buyCounts);
        }
        order.setTotalAmount(totalAmount);
        order.setRealPayAmount(realAmount);
        ordersMapper.insert(order);
        //3、保存订单状态表
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(waitPayOrderStatus);

        //4、构建商户订单，传给支付中心

        return orderId;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus paidStatus = new OrderStatus();
        paidStatus.setOrderId(orderId);
        paidStatus.setOrderStatus(orderStatus);
        paidStatus.setPayTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(paidStatus);
    }
}
