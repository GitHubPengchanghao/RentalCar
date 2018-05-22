package com.service;

/**
 * 所有车表的操作，管理车，查询车
 * @author Administrator
 */
public interface CarService {
    /**
     * 管理员按车编号查询车
     * @return 0 查询到结果，1 未查询到
     * @param carNumber 车编号
     */
    void queryCarbyId(int carNumber);

    /**
     * 按品牌编号查询车
     * @param brandNumber
     */
    void queryCarbyBrand(int brandNumber);

    /**
     * 按类型编号查询车
     * @param categoryNumber
     */
    void queryCarbyCategory(int categoryNumber);

    /**
     * 用户查询所有车
     * @param type 0 代表降序，1 代表升序
     */
    void queryCarbyUser(int type);

    /**
     * 管理员查询所有车
     * @param type 0 代表降序，1 代表升序
     */
    void queryCarbyAdmin(int type);

    /**
     * 管理员添加车
     */
    void addCar();

    /**
     * 用户查看车租赁记录
     * @param userName 根据用户编号传入Impl进行查询
     */
    void queryRecordbyUser(String userName);

    /**
     * 管理员查看车租赁记录
     */
    void queryRecordbyAdmin();

    /**
     * 用户根据参数进行租车
     * @param userName 用户名
     * @param carNumber 汽车编号
     */
    void rentCarbyUser(String userName,int carNumber);

    /**
     * 用户根据参数进行还车
     * @param userName 用户名
     * @param carNumber 汽车编号
     */
    void returnCarbyUser(String userName,int carNumber);

    /**
     * 管理员修改汽车信息
     * @param carNumber 汽车编号
     * @return 0 修改成功，1 修改失败
     */
    void chargeCar(int carNumber);
}
