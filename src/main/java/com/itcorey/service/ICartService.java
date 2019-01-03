package com.itcorey.service;

import com.itcorey.common.ServerResponse;
import com.itcorey.vo.CartVo;

/**
 * Created by ：Corey
 * 10:53 2018/12/28
 */
public interface ICartService {

    /**
     * 添加到购物车中
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);


    /**
     * 跟新购物车操作
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    /**
     * 删除购物车商品
     * @param productIds
     * @return
     */
    ServerResponse<CartVo> deleteProduct(Integer userId,String productIds);

    /**
     * 查询购物车
     * @param userId
     * @return
     */
    ServerResponse<CartVo> list(Integer userId);

    /**
     * 全选或者全反选
     * @param userId
     * @param checked
     * @return
     */
    ServerResponse<CartVo> selectOrSelect(Integer userId,Integer productId,Integer checked);


    /**
     * 获取搜友购物车信息
     * @param userId
     * @return
     */
    ServerResponse<Integer> getCartProductCount(Integer userId);

}

