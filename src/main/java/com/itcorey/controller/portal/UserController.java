package com.itcorey.controller.portal;

import com.itcorey.common.Const;
import com.itcorey.common.ResponseCode;
import com.itcorey.common.ServerResponse;
import com.itcorey.pojo.User;
import com.itcorey.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

/**
 * Created by ：Corey
 * 16:49 2018/12/20
 * 用户登录页面
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;


    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @param session  域对象
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> user = iUserService.login(username, password);
        if (user.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, user.getData());
        }
        return user;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }


    /**
     * 注册校验文本框
     *检查用户名是否有效
     * @param str
     * @param type
     * @return
     */
    @RequestMapping(value = "checkValue.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValue(String str, String type) {
        return iUserService.checkValue(str, type);
    }

    /**
     * 获取用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "getUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("当前用户未登录,用户信息无法获取!");
    }

    /**
     * 忘记密码
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return iUserService.selectQuestion(username);
    }


    /**
     * 校验找回密码问题是否正确(本地缓存)
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "forgetCheckAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return iUserService.checkAnswer(username, question, answer);
    }


    /**
     * 修改密码
     *
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @RequestMapping(value = "forgetRestPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return iUserService.forgetRestPassword(username, passwordNew, forgetToken);
    }


    /**
     * 登录状态修改密码
     *
     * @param session
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping(value = "restPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> restPassword(HttpSession session, String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录!");
        }
        return iUserService.restPassword(passwordOld, passwordNew, user);
    }


    /**
     * 登录状态更新用户信息接口
     *
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "updatInfomation.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updatInfomation(HttpSession session, User user) {
        User countUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (countUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录!");
        }
        user.setId(countUser.getId());
        user.setUsername(countUser.getUsername());
        ServerResponse<User> response = iUserService.updatInfomation(user);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }



    /**
     * 获取用户详细信息
     * @param session
     * @return
     */
    @RequestMapping(value = "getInfomation.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInfomation(HttpSession session){
        User countUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (countUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,需要强制登录status = 10");
        }
       return iUserService.getInformation(countUser.getId());
    }



}
