package com.itcorey.controller.portal;

import com.itcorey.common.Const;
import com.itcorey.common.ResponseCode;
import com.itcorey.common.ServerResponse;
import com.itcorey.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by ：Corey
 * 10:44 2018/12/28
 * 购物车
 */
@Controller
@RequestMapping("/cart/")
public class CartController {

    @RequestMapping("/add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session ,Integer count,Integer productId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }

    }


}
