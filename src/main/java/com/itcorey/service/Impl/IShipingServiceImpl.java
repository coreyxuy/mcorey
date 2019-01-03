package com.itcorey.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.itcorey.common.ServerResponse;
import com.itcorey.dao.ShippingMapper;
import com.itcorey.pojo.Shipping;
import com.itcorey.service.IShipingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ：Corey
 * 16:06 2019/1/3
 */
@Service("iShipingService")
public class IShipingServiceImpl implements IShipingService {


    @Autowired
    private ShippingMapper shippingMapper;

    /**
     * 添加收货地址
     *
     * @param userId
     * @param shipping
     * @return
     */
    public ServerResponse add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if (rowCount > 0) {
            Map map = Maps.newHashMap();
            map.put("shipingId", shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功", map);
        }
        return ServerResponse.createByErrorMessage("新建地址失败!");
    }

    /**
     * 删除收货地址
     *
     * @param userId
     * @param shippingId
     * @return
     */
    public ServerResponse<String> del(Integer userId, Integer shippingId) {
        int resultCount = shippingMapper.deleteByShippingIdUserId(userId, shippingId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除地址成功!");
        }
        return ServerResponse.createByErrorMessage("删除地址失败!");
    }


    /**
     * 跟新收货地址
     *
     * @param userId
     * @param shipping
     * @return
     */
    public ServerResponse<String> update(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int resultCount = shippingMapper.updateByShiping(shipping);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("跟新地址成功!");
        }
        return ServerResponse.createByErrorMessage("跟新地址失败!");

    }

    /**
     * 查询收货地址
     *
     * @param userId
     * @param shippingId
     * @return
     */
    public ServerResponse<Shipping> select(Integer userId, Integer shippingId) {
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId, shippingId);
        if (shipping == null) {
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        return ServerResponse.createBySuccess("更新地址成功", shipping);
    }

    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> list = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }


}
