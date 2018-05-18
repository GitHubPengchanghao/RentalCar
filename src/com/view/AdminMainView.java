package com.view;

import com.serviceimpl.CarServiceImpl;

/**
 * 管理员主页面，显示所有汽车列表，并实现管理员增删改查
 * @author Administrator
 */
public class AdminMainView extends View {
    private int chooseNum;
    private String mChoose;
    private CarServiceImpl mCarService;

    @Override
    public View showView() {
        for (int i = 0 ;i < 48;i++){
            System.out.print("=");
            if (i== 23){
                System.out.println("\n欢迎进入二嗨租车管理员大门!");
            }
        }
        System.out.println("");
        for(int i = 0;i < 35;i++){
            System.out.print("=");
        }
        System.out.println("");
        mCarService = new CarServiceImpl();
        mCarService.queryCarbyAdmin(1);
        while (true) {
            System.out.println("输入0退出");
            System.out.println("输入1+汽车编号 查看指定汽车");
            System.out.println("输入5 查看所有汽车");
            System.out.println("输入6 添加汽车");
            System.out.println("输入7+汽车编号 修改汽车信息");
            System.out.println("输入8 查看汽车记录");
            mChoose = mScanner.next();
            String a;
            String b;
            String c;
            int d;
            B: if (mChoose.length() == 1){
                a = mChoose.substring(0,1);
                switch (a){
                    case "0" :
                        mView = null;
                        System.out.println("欢迎下次光临！");
                        break B;
                    case "5" :
                        mCarService = new CarServiceImpl();
                        mCarService.queryCarbyAdmin(0);
                        break;
                    case "6" :
                        mCarService = new CarServiceImpl();
                        mCarService.addCar();
                        break;
                    case "8" :
                        mCarService = new CarServiceImpl();
                        mCarService.queryRecordbyAdmin();
                        break;
                    default:
                        System.out.println("选择错误，请按条件输入.");
                        break;
                }
                continue;
            }else if (mChoose.length() == 3){
                a = mChoose.substring(0,1);
                b = mChoose.substring(1,2);
                c = mChoose.substring(2,3);
                d = Integer.parseInt(c);
                switch (a){
                    case "1":
                        if ("+".equals(b)) {
                            mCarService = new CarServiceImpl();
                            mCarService.queryCarbyId(d);
                        }
                        break;
                    case "7" :
                        if ("+".equals(b)){
                            mCarService = new CarServiceImpl();
                            mCarService.chargeCar(d);
                        }else {
                            System.out.println("输入错误，请重新输入！");
                        }
                        break;
                    default:
                        System.out.println("选择错误，请按条件输入.");
                        break;
                }
                continue;
            }else {
                continue;
            }
            break;
        }
        return mView;
    }
}
