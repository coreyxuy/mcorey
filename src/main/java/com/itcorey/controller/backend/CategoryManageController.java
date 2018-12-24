package com.itcorey.controller.backend;

import com.itcorey.common.Const;
import com.itcorey.common.ResponseCode;
import com.itcorey.common.ServerResponse;
import com.itcorey.pojo.User;
import com.itcorey.service.ICategoryService;
import com.itcorey.service.IUserService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by ：Corey
 * 15:08 2018/12/24
 * 类别controller
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {


    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;



    @RequestMapping("addCategroy.do")
    @ResponseBody
    public ServerResponse addCategroy(HttpSession session, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
        }
        //校验是否是管理员
        ServerResponse serverResponse = iUserService.checkAdminRole(user);
        if (serverResponse.isSuccess()) {
            //是管理员

            //增加我们处理的逻辑
            return iCategoryService.addCategory(categoryName,parentId);

        } else {
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员操作!");
        }
    }


    /**
     * 跟新品类信息
     * @param session
     * @param category
     * @param categoryName
     * @return
     */
    public ServerResponse setCategoryName(HttpSession session,Integer category,String categoryName){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){

        }else {
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限!");
        }

        return null;
    }


}
