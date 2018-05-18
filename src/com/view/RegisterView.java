package com.view;

import com.bean.User;
import com.serviceimpl.UserServiceImpl;

/**
 * 用户注册界面，直接注册或者对匹配不到的用户进行注册。
 * @author Administrator
 */
public class RegisterView extends View {
    private String userName;
    private UserServiceImpl mUserService;

    public RegisterView(String userName) {
        mUserService = new UserServiceImpl();
        if (userName == null){
            userName = "";
        }else {
            this.userName = userName;
        }
    }

    @Override
    public View showView() {
        for (int i = 0 ;i < 48;i++){
            System.out.print("=");
            if (i== 23){
                System.out.println("\n欢迎进入二嗨租车注册门!");
            }
        }
        System.out.println("\n请注意：管理员账户不能注册！此处注册为用户账户！");
        User mUser = new User();
            if (userName.length() == 0){
                while (true){
                    System.out.println("请输入用户名：");
                    userName = mScanner.next();
                    int result = mUserService.userNameOnly(userName);
                    if (result == 0){
                        System.out.println("用户名已存在！");
                        continue;
                    }else if (result == 1){
                        System.out.println("用户名可用！");
                        break;
                    }
                }
            }else {
                System.out.println("用户名：" + userName);
            }
        System.out.print("请输入密　码:\t");
        String userPwd = mScanner.next();
        String userIdNumber = null;
        while (true){
            System.out.println("请输入身份证号码");
            userIdNumber = mScanner.next();
            int result = mUserService.userIdNumberOnly(userIdNumber);
            if (result == 0){
                System.out.println("身份证号已存在！");
                continue;
            }else if (result == 1){
                System.out.println("身份证号可用！");
                break;
            }
        }
        System.out.println("请输入联系方式");
        String userTel = mScanner.next();
        System.out.println("请输入家庭住址");
        String userAddress = mScanner.next();
        System.out.println("请输入性别");
        int userGender = 0;
        while (true){
            userGender = mScanner.nextInt();
            if (userGender == 0 || userGender == 1){
                break;
            }else {
                System.out.println("请输入性别");
                continue;
            }
        }

        mUser.setUserId(0);
        mUser.setUserName(userName);
        mUser.setUserPwd(userPwd);
        mUser.setUserIdNumber(userIdNumber);
        mUser.setUserTel(userTel);
        mUser.setUserAddress(userAddress);
        mUser.setUserGender(userGender);

        if(mUserService.userRegister(mUser)){
            System.out.println("注册成功");
            mView = new UserLoginView();
        }else {
            mView = this;
        }
        return mView;
    }
}
