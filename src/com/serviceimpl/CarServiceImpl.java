package com.serviceimpl;

import com.daoimpl.CarDaoImpl;
import com.service.CarService;

import java.sql.SQLException;

public class CarServiceImpl implements CarService {
    private CarDaoImpl mCarDao;

    public CarServiceImpl() {
        mCarDao = new CarDaoImpl();
    }

    /**
     * 管理员通过汽车编号查看车
     * @param carNumber 汽车编号
     */
    @Override
    public void queryCarbyId(int carNumber) {
        mCarDao.getCarbyId(carNumber);
    }

    /**
     * 用户通过品牌编号查询车
     * @param brandNumber 品牌编号
     */
    @Override
    public void queryCarbyBrand(int brandNumber) {
        mCarDao.getCarbyBrand(brandNumber);
    }

    /**
     * 用户通过类型编号查询车
     * @param categoryNumber 类型编号
     * @return
     */
    @Override
    public void queryCarbyCategory(int categoryNumber) {
        mCarDao.getCarbyCategory(categoryNumber);
    }

    /**
     * 用户查看汽车，根据参数值返回不同的查询结果
     * @param type 0 代表降序，1 代表升序
     */
    @Override
    public void queryCarbyUser(int type){
            mCarDao.getCar(type);
    }

    /**
     * 管理员查看汽车，根据参数值不同返回不同的查询结果
     * @param type 0 代表降序，1 代表升序
     */
    @Override
    public void queryCarbyAdmin(int type) {
            mCarDao.getCarbyAdmin(type);
    }

    /**
     * 管理员添加车
     */
    @Override
    public void addCar() {
        try {
            mCarDao.addCarbyAdmin();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户查询租车记录，只显示当前用户的租车记录
     * @param userName 根据用户编号请求sql数据
     */
    @Override
    public void queryRecordbyUser(String userName){
        try {
            mCarDao.getRecordbyUser(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员查询租车记录
     */
    @Override
    public void queryRecordbyAdmin(){
        try {
            mCarDao.getRecordbyAdmin();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户根据参数进行租车
     * @param userName 用户名
     * @param carNumber 汽车编号
     */
    @Override
    public void rentCarbyUser(String userName,int carNumber) {
        try {
            mCarDao.rentCar(userName,carNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户根据参数进行还车
     * @param userName 用户名
     * @param carNumber 汽车编号
     */
    @Override
    public void returnCarbyUser(String userName,int carNumber) {
        try {
            mCarDao.borrowCar(userName,carNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员修改汽车信息
     * @param carNumber 汽车编号
     */
    @Override
    public void chargeCar(int carNumber) {
        try {
            mCarDao.chargeCarbyAdmin(carNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
