package com.itcorey.service;

import com.github.pagehelper.PageInfo;
import com.itcorey.common.ServerResponse;
import com.itcorey.vo.OrderVo;

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

    /**
     * 生成第订单
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse createOrder(Integer userId, Integer shippingId);

    /**
     * 取消订单
     * @param userId
     * @param orderNo
     * @return
     */
    ServerResponse<String> cancel(Integer userId,Long orderNo);

    /**
     * 获取购物车中选中的商品
     * @param userId
     * @return
     */
    ServerResponse getOrderCartProduct(Integer userId);

    /**
     * 购物车产品详情
     * @param userId
     * @param orderNo
     * @return
     */
    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    /**
     * 个人中心订单列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> getOrderrList(Integer userId, int pageNum, int pageSize);

    /**
     * 后台管理员查看订单
     * @param pageeNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> manageList(int pageeNum,int pageSize);

    /**
     * 后台查看订单详情
     * @param orderNo
     * @return
     */
    ServerResponse<OrderVo> manageDetail(Long orderNo);

    /**
     * 后台搜索查询
     * @param OrderNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> manageSearch(Long OrderNo, int pageNum, int pageSize);

    /**
     * 后台管理员发货
     * @param orderNo
     * @return
     */
    ServerResponse<String> manageSendGoods(Long orderNo);
}
