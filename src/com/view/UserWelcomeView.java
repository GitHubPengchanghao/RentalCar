package com.view;

import java.util.InputMismatchException;

/**
 * 用户欢迎界面
 * @author Administrator
 */
public class UserWelcomeView extends View {
    private int chooseNum;

    @Override
    public View showView() {
        for (int i = 0 ;i < 48;i++){
            System.out.print("=");
            if (i== 23){
                System.out.println("\n欢迎进入二嗨租车用户门！");
            }
        }
        System.out.println("\n1.登录 2.注册 3.退出");
        while (true){
                    chooseNum = mScanner.nextInt();
                    if (chooseNum > 3 || chooseNum < 0) {
                        System.out.println("选择错误，请输入数字1-3.");
                        continue;
                    }
            break;
        }
        switch (chooseNum){
            case 1: mView = new UserLoginView();
                break;
            case 2: mView = new RegisterView("");
                break;
            case 3: mView = null;
                System.out.println("欢迎下次光临！");
                break;
            default:
                break;
        }
        return mView;
    }
}
