package edu.xpu.hcp.service;

import edu.xpu.hcp.bo.SubmitOrderBO;
import edu.xpu.hcp.pojo.OrderStatus;
import edu.xpu.hcp.vo.OrderVO;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author huchengpeng
 * @date 2020/11/17 13:31
 * @version V1.0.1
 * @Description 订单
 */
public interface OrderService {

    /**
     * 创建订单
     * @param submitOrderBO 订单BO
     * @return OrderVO 订单VO
     */
    OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     * @param orderId 订单ID
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String orderId,Integer orderStatus);

    /**
     * 查询订单状态
     * @param orderId 订单ID
     * @return 订单状态
     */
    OrderStatus queryOrderStatus(String orderId);

    /**
     * 关闭超市未支付订单
     */
    void closeOrder();
}
