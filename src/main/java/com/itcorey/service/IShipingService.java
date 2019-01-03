package com.itcorey.service;

import com.github.pagehelper.PageInfo;
import com.itcorey.common.ServerResponse;
import com.itcorey.pojo.Shipping;

/**
 * Created by ：Corey
 * 16:05 2019/1/3
 */
public interface IShipingService {

    /**
     * 添加收货地址
     * @param userId
     * @param shipping
     * @return
     */
    ServerResponse add(Integer userId, Shipping shipping);

    /**
     * 删除收货地址
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse del(Integer userId, Integer shippingId);

    /**
     * 更新收货地址
      * @param userId
     * @param shipping
     * @return
     */
    ServerResponse<String> update(Integer userId, Shipping shipping);

    /**
     * 获取收货地址
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    /**
     * 分页查询
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
