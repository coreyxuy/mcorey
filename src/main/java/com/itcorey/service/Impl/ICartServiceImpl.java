package com.itcorey.service.Impl;

import com.itcorey.common.Const;
import com.itcorey.common.ServerResponse;
import com.itcorey.dao.CartMapper;
import com.itcorey.pojo.Cart;
import com.itcorey.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ：Corey
 * 10:53 2018/12/28
 * 购物车实现类
 */
@Service("ICartService")
public class ICartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    public ServerResponse add(Integer userId, Integer productId, Integer count) {
        Cart cart = cartMapper.selectCartByUserIdProduct(userId, productId);
        if (cart == null) {
            //这个产品不在这个购物车类,需要新增一个购物车记录
            Cart cartItem = new Cart();
            cartItem.setQuantity(count);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartItem.setProductId(productId);
            cartItem.setUserId(userId);
            cartMapper.updateByPrimaryKey(cartItem);
        }else {
            //产品存在购物车,如果存在就 相加数量
            int i = cart.getQuantity() + count;

        }
        return  null;
    }

}
