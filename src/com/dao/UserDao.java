package com.dao;

import com.bean.User;

import java.sql.SQLException;

/**
 * 用户类接口，数据操作，包含增删改查
 * @author Administrator
 */
public interface UserDao {
    /**
     * 获取用户名
     * @param username
     * @return
     */
    User getUserName(String username) throws SQLException;
    int addUser(User user) throws SQLException;
}
