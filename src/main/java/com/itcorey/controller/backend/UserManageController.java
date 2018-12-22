package com.itcorey.controller.backend;

import com.itcorey.common.ServerResponse;
import com.itcorey.pojo.User;
import com.itcorey.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by ：Corey
 * 15:05 2018/12/21
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    public ServerResponse<User> login(HttpSession session,String password,String username){

        return null;
    }


}
