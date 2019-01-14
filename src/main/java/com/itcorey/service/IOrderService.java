package com.itcorey.service;

import com.itcorey.common.ServerResponse;

/**
 * Created by ：Corey
 * 15:26 2019/1/9
 * 订单接口
 */
public interface IOrderService {
    /**
     * 订单支付
     * @param orderNo
     * @param userId
     * @param path
     * @return
     */
    ServerResponse pay(Long orderNo, Integer userId, String path);
}
