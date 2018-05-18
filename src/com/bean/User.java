package com.bean;

import java.io.Serializable;

/**
 * 用户表
 * @author Administrator
 */
public class User implements Serializable,Comparable<User>{
    /**
     * 用户ID（自增），用户权限类型（0,1），用户性别（0,1）；
     * 用户名，密码，身份证，电话，地址。
     */
    private int userId;
    private int userType;
    private int userGender;

    private String userName;
    private String userPwd;
    private String userIdNumber;
    private String userTel;
    private String userAddress;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserIdNumber() {
        return userIdNumber;
    }

    public void setUserIdNumber(String userIdNumber) {
        this.userIdNumber = userIdNumber;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public User(int userId, int userType, int userGender, String userName, String userPwd, String userIdNumber, String userTel, String userAddress) {
        this.userId = userId;
        this.userType = userType;
        this.userGender = userGender;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userIdNumber = userIdNumber;
        this.userTel = userTel;
        this.userAddress = userAddress;
    }

    public User() {
    }

    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userType=" + userType +
                ", userGender=" + userGender +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userIdNumber='" + userIdNumber + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }

    /**
     * 重写compareTo方法，以用户ID对用户表进行排序。
     * @param o
     * @return
     */
    @Override
    public int compareTo(User o) {
        return this.getUserId() - o.getUserId();
    }
}
