package com.serviceimpl;

import com.bean.Car;
import com.bean.Record;
import com.daoimpl.CarDaoImpl;
import com.service.CarService;
import com.service.DbHelp;
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
        try {
            mCarDao.getCarbyAdmin(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员添加车
     * @return rue注册成功,false注册失败
     */
    @Override
    public void addCar() {
        mCarDao.addCarbyAdmin();
    }

    /**
     * 用户查询租车记录，只显示当前用户的租车记录
     * @param userNumber 根据用户编号请求sql数据
     */
    @Override
    public void queryRecordbyUser(int userNumber){

    }

    /**
     * 管理员查询租车记录
     */
    @Override
    public void queryRecordbyAdmin(){

    }

    /**
     * 用户根据参数进行租车
     * @param carNumber 汽车编号
     * @return 0 租车成功，1 租车失败
     */
    @Override
    public int rentCarbyUser(int carNumber) {
        return 0;
    }

    /**
     * 用户根据参数进行还车
     * @param carNumber 汽车编号
     * @return 0 还车成功，1 还车失败
     */
    @Override
    public int returnCarbyUser(int carNumber) {
        return 0;
    }

    /**
     * 管理员修改汽车信息
     * @param carNumber 汽车编号
     * @return 0 修改成功，1 修改失败
     */
    @Override
    public int chargeCar(int carNumber) {
        return 0;
    }
}
