package com.view;

/**
 * 管理员欢迎界面，不能注册！
 * @author Administrator
 */
public class AdminWelcomeView extends View {
    private int chooseNum;

    @Override
    public View showView() {
        for (int i = 0 ;i < 48;i++){
            System.out.print("=");
            if (i== 23){
                System.out.println("\n欢迎进入二嗨租车管理门！");
            }
        }
        System.out.println("\n1.登录 2.退出");
        while (true){
            chooseNum = mScanner.nextInt();
            if (chooseNum > 2 || chooseNum < 1){
                System.out.println("选择错误，请输入数字1或2.");
                continue;
            }
            break;
        }
        switch (chooseNum){
            case 1: mView = new AdminLoginView();
                break;
            case 2: mView = null;
                System.out.println("欢迎下次管理！");
                break;
            default:
                break;
        }
        return mView;
    }
}
