package com.itcorey.service.Impl;

import com.itcorey.common.Const;
import com.itcorey.common.ServerResponse;
import com.itcorey.common.TokenCache;
import com.itcorey.dao.UserMapper;
import com.itcorey.pojo.User;
import com.itcorey.service.IUserService;
import com.itcorey.util.MD5Util;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by ：Corey
 * 16:55 2018/12/20
 */
@Service("iUserService")
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<User> login(String username, String password) {
        //校验用户是否存在
        int resultCount = userMapper.chackUserName(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在!");
        }
        //todo 密码登录 MD5
        String Md5password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, Md5password);
        if (null == user) {
            return ServerResponse.createByErrorMessage("密码错误!");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);

    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public ServerResponse<String> register(User user) {
        ServerResponse vailResponse = this.checkValue(user.getUsername(), Const.USERNAME);
        if (!vailResponse.isSuccess()) {
            return vailResponse;
        }
        ServerResponse<String> stringServerResponse = this.checkValue(user.getEmail(), Const.EMAIL);
        if (!stringServerResponse.isSuccess()) {
            return stringServerResponse;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //用户信息MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int insert = userMapper.insert(user);
        if (insert == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功!");
    }

    /**
     * 注册校验文本框
     *
     * @param str
     * @param type
     * @return
     */
    public ServerResponse<String> checkValue(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            //校验用户
            if (Const.USERNAME.equals(type)) {
                int resultCount = userMapper.chackUserName(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名存在!");
                }
            }
            if (Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("Email 已经存在!");
                }
            }

        } else {
            return ServerResponse.createByErrorMessage("参数有误!");
        }
        return ServerResponse.createBySuccessMessage("校验成功!");
    }


    public ServerResponse<String> selectQuestion(String username) {
        ServerResponse<String> valueResponce = this.checkValue(username, Const.USERNAME);
        if (valueResponce.isSuccess()) {
            return ServerResponse.createByErrorMessage("用户不存在!");
        }
        String qusetion = userMapper.selectQuestionByUserName(username);
        if (StringUtils.isNotBlank(qusetion)) {
            return ServerResponse.createBySuccess(qusetion);
        }
        return ServerResponse.createByErrorMessage("找回密码的问题为空!");
    }

    /**
     * 校验问题答案 找回密码
     *
     * @param username
     * @param question
     * @param answer
     * @return
     */
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int resoultCount = userMapper.checkAnswer(username, question, answer);
        if (resoultCount > 0) {
            //问题答案都正确
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PERFIX + username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题答案错误");
    }


    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误,token需要传递!");
        }
        ServerResponse validResponse = this.checkValue(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            //用户不存在
            return ServerResponse.createByErrorMessage("用户不存在!");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PERFIX + username);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("token无效或过期!");
        }
        if (StringUtils.equals(forgetToken, token)) {
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            //修改密码
            int count = userMapper.updatePasswordByusername(username, md5Password);
            if (count > 0) {
                return ServerResponse.createBySuccessMessage("修改密码成功!");
            }
        } else {
            return ServerResponse.createBySuccessMessage("token错误,请重新获取重置的密码!");
        }
        return ServerResponse.createByErrorMessage("修改密码失败!");
    }

    /**
     * 登录状态修改Miami
     *
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    @Override
    public ServerResponse<String> restPassword(String passwordOld, String passwordNew, User user) {
        //防止横向越权,所以校验用户的旧密码,一定要指定这个用户,因为会查询一个count(1) 结果就是true count>0
        int count = userMapper.chackPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        if (count > 0) {
            return ServerResponse.createByErrorMessage("旧密码错误!");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("密码跟新成功!");
        }
        return ServerResponse.createByErrorMessage("Miami跟新失败!");
    }

    /**
     * 跟新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> updatInfomation(User user) {
        //username 不能跟新
        //email 也需要校验,校验新的email是不是已经存在 如果email相同的化 不能是我们这个相同的用户
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("email已经存在,请跟换新的email");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0) {
            return ServerResponse.createBySuccess("跟新个人信息成功!", updateUser);
        }
        return ServerResponse.createByErrorMessage("跟新个人信息失敗！");
    }

    /**
     * 获取当前用户的详细信息,并强制登录
     *
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<User> getInformation(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createByErrorMessage("找不到当前用户信息!");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }


    /**
     * 校验是否是管理员
     *
     * @param user
     * @return
     */
    public ServerResponse checkAdminRole(User user) {
        if (user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }


}
