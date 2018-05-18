package com.view;

import com.serviceimpl.CarServiceImpl;

/**
 * 用户主页面，显示所有可租借汽车，并实现用户增删改查
 * @author Administrator
 */
public class UserMainView extends View {
    private String mChoose;
    private CarServiceImpl mCarService;
    @Override
    public View showView() {
        for (int i = 0 ;i < 48;i++){
            System.out.print("=");
            if (i== 23){
                System.out.println("\n欢迎进入二嗨租车用户大门!");
            }
        }
        System.out.println("");
        for(int i = 0;i < 35;i++){
            System.out.print("=");
        }
        System.out.println("");
        mCarService = new CarServiceImpl();
        mCarService.queryCarbyUser(1);
        while (true){
            System.out.println("输入0退出");
            System.out.println("输入1+汽车编号 进入租车订单");
            System.out.println("输入2+1 按价格降序排序   2+2 按价格升序排序");
            System.out.println("输入3+类型编号 按类型搜索");
            System.out.println("输入4+品牌编号 按品牌搜索");
            System.out.println("输入5 查看所有汽车");
            System.out.println("输入6 查看我的租车记录");
            System.out.println("输入7+汽车编号 还车");
            mChoose = mScanner.next();
            String a;
            String b;
            String c;
            int d;
            A: if (mChoose.length() == 1){
                a = mChoose.substring(0,1);
                switch (a){
                    case "0" :
                        mView = null;
                        System.out.println("欢迎下次光临！");
                        break A;
                    case "5" :
                        mCarService = new CarServiceImpl();
                        mCarService.queryCarbyUser(0);
                        break;
                    case "6" :
                        mCarService = new CarServiceImpl();
                        mCarService.queryRecordbyUser(0);
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
                        if ("+".equals(b) && d > 0){
                            mCarService = new CarServiceImpl();
                            mCarService.rentCarbyUser(d);
                        }
                        break;
                    case "2" :
                        if ("+".equals(b)  && "1".equals(c)){
                            mCarService = new CarServiceImpl();
                            mCarService.queryCarbyUser(0);
                        }else if ("+".equals(b) && "2".equals(c)){
                            mCarService = new CarServiceImpl();
                            mCarService.queryCarbyUser(1);
                        }else {
                            System.out.println("输入错误，请重新输入！");
                        }
                        break;
                    case "3" :
                        if ("+".equals(b) && d < 6 && d > 0){
                            mCarService = new CarServiceImpl();
                            mCarService.queryCarbyCategory(d);
                        }else {
                            System.out.println("输入错误，请重新输入！");
                        }
                        break;
                    case "4" :
                        if ("+".equals(b) && d < 5 && d > 0){
                            mCarService = new CarServiceImpl();
                            mCarService.queryCarbyBrand(d);
                        }else {
                            System.out.println("输入错误，请重新输入！");
                        }
                        break;
                    case "7" :
                        if ("+".equals(b)){
                            mCarService = new CarServiceImpl();
                            mCarService.rentCarbyUser(d);
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
