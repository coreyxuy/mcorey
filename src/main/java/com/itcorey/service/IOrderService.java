package com.itcorey.service;

import com.itcorey.common.ServerResponse;

import java.util.Map;

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


    /**
     * 支付宝回调
     * @param params
     * @return
     */
    ServerResponse aliCallback(Map<String,String> params);

    /**
     * 查询订单
     * @param userId
     * @param orderNo
     * @return
     */
    ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);

}
