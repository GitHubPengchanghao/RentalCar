package com.daoimpl;

import com.dao.CarDao;
import com.service.DbHelp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDaoImpl implements CarDao {
    private DbHelp mDbHelp;
    private PreparedStatement mPreparedStatement;
    private Connection mConnection;
    private ResultSet mResultSet;
    ArrayList<String> list;

    public CarDaoImpl() {
        mDbHelp = new DbHelp();
        mConnection = mDbHelp.getmConnection();
        if (mConnection == null) {
            System.out.println("打开数据库失败！");
            return;
        }
    }

    /**
     * 获取Car表
     *
     * @param carNumber
     * @return Car对象
     */
    @Override
    public void getCar(int carNumber) {
        String sql = null;
        list = new ArrayList<>();
        if (carNumber == 1) {
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status "
                    + "FROM t_car,t_brand,t_category WHERE t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        } else if (carNumber == 0) {
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status "
                    + "FROM t_car,t_brand,t_category WHERE t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id DESC";
        }
        getCarbyUser(sql);
    }

    public void getCarbyUser(String sql){
        try {
            mPreparedStatement = mConnection.prepareStatement(sql);
            mResultSet = mPreparedStatement.executeQuery();
            for (int i = 0; mResultSet.next(); i++){
                for (int j = 1;j <= 13;j++){
                    list.add(mResultSet.getString(j));
                }
                if (mResultSet.getInt(14) == 0){
                    list.add("可借");
                }else {
                    list.add("不可借");
                }
            }
            System.out.println("编号    汽车名称       备注         品牌         类型            价格      是否可租");
            int length = list.size();
            for (int i = 1; i <= length; i++) {
                System.out.print(list.remove(0));
                if (i == 1 || i == 2 || i == 3 || i == 7 || i == 11 || i == 13){
                    System.out.print("\t\t");
                }else if (i % 14 == 1 || i % 14 == 2 || i % 14 == 3 || i % 14 == 7 || i % 14== 11 || i % 14 == 13){
                    System.out.print("\t\t");
                } else if (i % 14 == 0) {
                    System.out.println();
                }
            }
            if (mResultSet != null && !mResultSet.isClosed()) {
                mResultSet.close();
                mResultSet = null;
            }
            if (mPreparedStatement != null && !mPreparedStatement.isClosed()) {
                mPreparedStatement.close();
                mPreparedStatement = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getCarbyAdmin(int type) throws SQLException {
        String sql = null;
        list = new ArrayList<>();
        if (type == 1) {
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status,t_car.useable "
                    + "FROM t_car,t_brand,t_category WHERE t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        } else if (type == 0) {
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status,t_car.useable "
                    + "FROM t_car,t_brand,t_category WHERE t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id DESC";
        }
        mPreparedStatement = mConnection.prepareStatement(sql);
        mResultSet = mPreparedStatement.executeQuery();
        for (int i = 0; mResultSet.next(); i++){
            for (int j = 1;j <= 13;j++){
                list.add(mResultSet.getString(j));
            }
            if (mResultSet.getInt(14) == 0){
                list.add("可借");
            }else {
                list.add("不可借");
            }
            if (mResultSet.getInt(15) == 0){
                list.add("上架");
            }else {
                list.add("未上架");
            }
        }
        System.out.println("编号    汽车名称       备注         品牌         类型            价格      是否可租      是否上架");
        int length = list.size();
        for (int i = 1; i <= length; i++) {
            System.out.print(list.remove(0));
            if (i == 1 || i == 2 || i == 3 || i == 7 || i == 11 || i == 13 || i == 14){
                System.out.print("\t\t");
            }else if (i % 15 == 1 || i % 15 == 2 || i % 15 == 3 || i % 15 == 7 || i % 15 == 11 || i % 15 == 13 || i % 15 ==14){
                System.out.print("\t\t");
            } else if (i % 15 == 0) {
                System.out.println();
            }
        }
        if (mResultSet != null && !mResultSet.isClosed()) {
            mResultSet.close();
            mResultSet = null;
        }
        if (mPreparedStatement != null && !mPreparedStatement.isClosed()) {
            mPreparedStatement.close();
            mPreparedStatement = null;
        }
    }

    /**
     * 通过参数查询租车记录
     * @param userNumber 用户编号
     */
    @Override
    public void getRecordbyUser(int userNumber) {

    }

    /**
     * 查询所有租车记录
     */
    @Override
    public void getRecordbyAdmin() {

    }

    /**
     * 按类型编号查询车
     * @param categoryNumber 类型编号
     */
    @Override
    public void getCarbyCategory(int categoryNumber) {

    }

    /**
     * 按品牌编号查询车
     * @param brandNumber 品牌编号
     */
    @Override
    public void getCarbyBrand(int brandNumber) {

    }

    /**
     * 管理员按汽车编号查询汽车
     * @param carNumber 汽车编号
     */
    @Override
    public void getCarbyId(int carNumber) {

    }

    /**
     * 管理员添加汽车
     * @return 0 添加成功，1 添加失败
     */
    @Override
    public int addCarbyAdmin() {
        return 0;
    }

    @Override
    public int borrowCar() {
        return 0;
    }

    @Override
    public int rentCar() {
        return 0;
    }
}
