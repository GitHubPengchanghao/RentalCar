package com.serviceimpl;

import com.bean.User;
import com.dao.UserDao;
import com.daoimpl.UserDaoImpl;
import com.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDaoImpl mUserDao;

    public UserServiceImpl() {
        mUserDao = new UserDaoImpl();
    }

    @Override
    public int userLogin(String userName, String userPwd) {
        User mUser = null;
        try {
            mUser = mUserDao.getUserName(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (mUser == null){
            return 2;
        }
        if (!mUser.getUserPwd().equals(userPwd)){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean userRegister(User user) {
        int result = 0;
        try {
            result = mUserDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result > 0){
            return true;
        }
        return false;
    }

    /**
     * 注册时验证用户名唯一性
     * @param userName
     * @return 返回1则用户名可用，返回0则用户名已存在
     */
    public int userNameOnly(String userName){
        User mUser = null;
        try {
            mUser = mUserDao.getUserName(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (mUser == null){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 这测试验证身份证号唯一性
     * @param userIdNumber
     * @return 返回1则身份证号码可用，返回0则号码已存在
     */
    public int userIdNumberOnly(String userIdNumber) {
        User mUser = null;
        try {
            mUser = mUserDao.getUserIdNumber(userIdNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (mUser == null){
            return 1;
        }else {
            return 0;
        }
    }
}
