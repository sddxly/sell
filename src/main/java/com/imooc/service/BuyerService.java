package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * @author Liam Liu
 * @date 2018/6/14 16:39
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
