package com.view;

import com.service.UserService;
import com.serviceimpl.UserServiceImpl;

/**
 * 用户登录界面，只做登录页显示和账户匹配，匹配结束后跳转到相应界面。
 * @author Administrator
 */
public class UserLoginView extends View {
    private int inputNumber;
    private UserServiceImpl mUserService = new UserServiceImpl();

    @Override
    public View showView() {
        for (int i = 0 ;i < 48;i++){
            System.out.print("=");
            if (i== 23){
                System.out.println("\n欢迎进入二嗨租车登录门！");
            }
        }

        System.out.println("\n请输入用户名:");
        String userName = mScanner.next();
        System.out.println("请输入密　码:");
        String userPwd = mScanner.next();
        while (true){
            int checkNum = (int)(Math.random()*9000+1000);
            System.out.println("请输入验证码:\t" + checkNum);
            inputNumber = mScanner.nextInt();
            if (inputNumber != checkNum){
                System.out.println("验证码不匹配，请重新输入！");
                continue;
            }
            break;
        }

        int result = mUserService.userLogin(userName,userPwd);
        switch (result){
            case 0: System.out.println("登陆成功！");
                    mView = new UserMainView();
                break;
            case 1: System.out.println("密码错误！");
                    mView = this;
                break;
            case 2: System.out.println("用户名不存在！");
                    mView = new RegisterView(userName);
                break;
            default:
                break;
        }
        return mView;
    }
}
