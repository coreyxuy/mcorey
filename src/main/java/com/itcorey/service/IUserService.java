package com.itcorey.service;


import com.itcorey.common.ServerResponse;
import com.itcorey.pojo.User;

/**
 * Created by ：Corey
 * 16:53 2018/12/20
 */
public interface IUserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    ServerResponse<User> login(String username , String password);


    /**
     * 注册
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);


    /**
     * 注册校验文本框
     * @param str
     * @param type
     * @return
     */
    ServerResponse<String> checkValue(String str, String type);


    /**
     * 找回密码
     * @param username
     * @return
     */
    ServerResponse<String> selectQuestion(String username);

    /**
     * 问题答案找回密码
     * @param username
     * @param question
     * @param answer
     * @return
     */
    ServerResponse<String> checkAnswer(String username,String question,String answer);

    /**
     * 修改密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    ServerResponse<String> forgetRestPassword(String username,String passwordNew ,String forgetToken);

    /**
     * 登录状态修改密码
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    ServerResponse<String> restPassword(String passwordOld, String passwordNew, User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    ServerResponse<User> updatInfomation(User user);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    ServerResponse<User> getInformation(Integer userId);

    /**
     * 校验是否是管理员
     * @param user
     * @return
     */
    ServerResponse checkAdminRole(User user);
}
