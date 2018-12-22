package com.itcorey.dao;

import com.itcorey.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 校验用户登录
     * @param username
     * @return
     */
    int  chackUserName(String username);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User selectLogin(@Param("username") String username, @Param("password")String password);

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    int checkEmail(String email);

    /**
     * 忘记密码
     * @return
     */
    String selectQuestionByUserName(String username);

    /**
     * 找回密码
     * @param username
     * @param question
     * @param answer
     * @return
     */
    int checkAnswer(@Param("username") String username,@Param("question") String question,@Param("answer") String answer);

    /**
     * 跟新密码
     * @param username
     * @param passwordNew
     * @return
     */
    int updatePasswordByusername(@Param("username") String username, @Param("passwordNew") String passwordNew);

    /**
     * 校验密码是否存在
     * @param password
     * @param id
     * @return
     */
    int chackPassword(@Param("password") String password,@Param("userId") Integer userId);

    /**
     * 校验邮箱
     * @param email
     * @param userId
     * @return
     */
    int checkEmailByUserId(@Param("email") String email, @Param("userI") Integer userId);
}
