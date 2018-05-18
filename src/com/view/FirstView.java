package com.view;

/**
 * 系统首页，对应身份进入欢迎页，任意门为退出！
 * @author Administrator
 */
public class FirstView extends View {
    private int chooseNum;

    @Override
    public View showView() {
        for (int i = 0 ;i < 48;i++){
            System.out.print("=");
            if (i== 23){
                System.out.println("\n欢迎访问二嗨租车!");
            }
        }
        System.out.println("\n请选择身份\n1.管理员 2.用户 3.任意门");
        while (true){
            chooseNum = mScanner.nextInt();
            if (chooseNum > 3 || chooseNum < 1){
                System.out.println("选择错误，请输入数字1-3.");
                continue;
            }
            break;
        }
        switch (chooseNum){
            case 1: mView = new AdminWelcomeView();
                break;
            case 2: mView = new UserWelcomeView();
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
