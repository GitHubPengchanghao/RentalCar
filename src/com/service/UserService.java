package com.service;

import com.bean.User;

/**
 * 所有账户的业务行为，匹配账户，注册账户（仅用户）
 * @author Administrator
 */
public interface UserService {
    /**
     * 获取用户账户并返回结果
     * @param userName 用户名
     * @param userPwd 密码
     * @param type 用户类型
     * @return 0:登录成功,1:代表密码 2:代表用户名不存在
     */
    int userLogin(String userName,String userPwd,int type);

    /**
     * @param user 创建用户类对象，提供注册信息
     * @return true注册成功,false注册失败
     */
    boolean userRegister(User user);
}
