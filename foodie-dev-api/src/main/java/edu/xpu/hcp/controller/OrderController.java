package edu.xpu.hcp.controller;

import edu.xpu.hcp.bo.SubmitOrderBO;
import edu.xpu.hcp.common.JSONResult;
import edu.xpu.hcp.enums.OrderStatusEnum;
import edu.xpu.hcp.enums.PayMethod;
import edu.xpu.hcp.pojo.OrderStatus;
import edu.xpu.hcp.service.OrderService;
import edu.xpu.hcp.utils.CookieUtils;
import edu.xpu.hcp.vo.MerchantOrdersVO;
import edu.xpu.hcp.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.omg.CORBA.TRANSACTION_MODE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author huchengpeng
 * @date 2020/11/17 13:17
 * @version V1.0.1
 * @Description 订单相关接口
 */
@Api(value = "订单相关接口",tags = {"订单相关接口"})
@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "创建订单",notes = "创建订单",httpMethod = "POST")
    @PostMapping("/create")
    public JSONResult createOrder(@RequestBody SubmitOrderBO submitOrderBO, HttpServletRequest request, HttpServletResponse response){
        if(submitOrderBO.getPayMethod().equals( PayMethod.WEIXIN.type) &&
                submitOrderBO.getPayMethod().equals( PayMethod.ALIPAY.type)){
            return JSONResult.errorMsg("不支持的支付方式");
        }
        //1、创建订单
        OrderVO orderVO = orderService.createOrder(submitOrderBO);
        String orderId = orderVO.getOrderId();
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);
        //TODO 2、创建订单后移除购物车中已结算的商品

        CookieUtils.setCookie(request,response,FOODIE_SHOPCART,"", true);

        //3、向支付中心发送当前订单，用于保存支付中心的订单数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId","imooc");
        headers.add("password","imooc");
        //测试使用0.1元
        merchantOrdersVO.setAmount(1);
        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO, headers);
        ResponseEntity<JSONResult> resp = restTemplate.postForEntity(paymentUrl, entity, JSONResult.class);
        JSONResult result = resp.getBody();
        if(result.getStatus() != 200){
            return JSONResult.errorMsg("支付中心订单创建失败");
        }
        return JSONResult.ok(orderId);
    }

    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId){
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    @PostMapping("/getPaidOrderInfo")
    public JSONResult getPaidOrderInfo(@RequestParam String orderId){
        OrderStatus orderStatus = orderService.queryOrderStatus(orderId);
        if(orderStatus == null){
            return JSONResult.errorMsg("错误的订单编号");
        }
        return JSONResult.ok(orderStatus);
    }
}
