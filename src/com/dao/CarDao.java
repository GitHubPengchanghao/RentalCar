package com.dao;

import com.bean.Car;

import java.sql.SQLException;

/**
 * 车接口，提供数据操作
 * @author Administrator
 */
public interface CarDao {
    /**
     * 用户查询整个Car表数据
     * @param carNumber 0 代表降序，1 代表升序
     */
    void getCar(int carNumber) throws SQLException;

    /**
     * 按类型编号查询车
     * @param categoryNumber 类型编号
     */
    void getCarbyCategory(int categoryNumber);

    /**
     * 按品牌编号查询车
     * @param brandNumber 品牌编号
     */
    void getCarbyBrand(int brandNumber);

    /**
     * 管理员按汽车编号查询汽车
     * @param carNumber 汽车编号
     */
    void getCarbyId(int carNumber) throws SQLException;

    /**
     * 管理员添加汽车
     * @return 0 添加成功，1 添加失败
     */
    void addCarbyAdmin() throws SQLException;

    /**
     * 用户还车
     */
    void borrowCar(String userName,int carNumber) throws SQLException;

    /**
     * 用户借车
     */
    void rentCar(String userName,int carNumber) throws SQLException;

    /**
     * 管理员查询整个Car表数据
     * @param type 0 代表降序，1 代表升序
     */
    void getCarbyAdmin(int type) throws SQLException;

    /**
     * 用户查询租车记录
     * @param userName 用户名
     */
    void getRecordbyUser(String userName) throws SQLException;

    /**
     * 管理员查询所有租车记录
     */
    void getRecordbyAdmin() throws SQLException;
}
