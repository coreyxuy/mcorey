package com.itcorey.controller.backend;

import com.itcorey.common.Const;
import com.itcorey.common.ResponseCode;
import com.itcorey.common.ServerResponse;
import com.itcorey.pojo.Product;
import com.itcorey.pojo.User;
import com.itcorey.service.IProductService;
import com.itcorey.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by ：Corey
 * 15:43 2018/12/25
 * 商品类信息
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProductService iProductService;


    /**
     * 保存商品信息
     *
     * @param session
     * @param product
     * @return
     */
    @RequestMapping("productSave.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Product product) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户为登录,请登录后再试!");
        }
        //判断管理员权限
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充产品的增加业务逻辑
            return iProductService.saveOrUpdateProduct(product);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作!");
        }
    }


    /**
     * 产品上下架(产品的销售状态)
     *
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSlaeStatus(HttpSession session, Integer productId, Integer statue) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户为登录,请登录后再试!");
        }
        //判断管理员权限
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充产品的增加业务逻辑
            return iProductService.setSaleStatus(productId, statue);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作!");
        }
    }


    /**
     * 获取商品的详情
     *
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户为登录,请登录后再试!");
        }
        //判断管理员权限
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充产品的增加业务逻辑
            return iProductService.mamageProductDetail(productId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作!");
        }
    }


}
