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
    void getCarbyId(int carNumber);

    /**
     * 管理员添加汽车
     * @return 0 添加成功，1 添加失败
     */
    int addCarbyAdmin();

    /**
     * 用户借车
     * @return 0 借车成功，1 借车失败
     */
    int borrowCar();

    /**
     * 用户还车
     * @return 0 还车成功，1 还车失败
     */
    int rentCar();

    /**
     * 管理员查询整个Car表数据
     * @param type 0 代表降序，1 代表升序
     */
    void getCarbyAdmin(int type) throws SQLException;

    /**
     * 用户查询租车记录
     * @param userNumber 用户编号
     */
    void getRecordbyUser(int userNumber);

    /**
     * 管理员查询所有租车记录
     */
    void getRecordbyAdmin();
}
