package com.daoimpl;

import com.Test.Tools;
import com.dao.CarDao;
import com.service.DbHelp;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CarDaoImpl implements CarDao {
    private DbHelp mDbHelp;
    private PreparedStatement mPreparedStatement;
    private Connection mConnection;
    private ResultSet mResultSet;
    ArrayList<String> list;
    int length = 0;
    private static Scanner scan;

    public CarDaoImpl() {
        mDbHelp = new DbHelp();
        mConnection = mDbHelp.getmConnection();
        if (mConnection == null) {
            System.out.println("打开数据库失败！");
            return;
        }
    }
    public static Scanner getScanner() {
        if (scan == null) {
            scan = new Scanner(System.in);
        }
        return scan;
    }
    /**
     * 获取Car表
     * 用户查车
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

    /**
     * 管理员查车
     * @param type 0 代表降序，1 代表升序
     * @throws SQLException
     */
    @Override
    public void getCarbyAdmin(int type){
        String sql = null;
        list = new ArrayList<>();
        if (type == 1) {
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status,t_car.useable "
                    + "FROM t_car,t_brand,t_category WHERE t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        } else if (type == 0) {
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status,t_car.useable "
                    + "FROM t_car,t_brand,t_category WHERE t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id DESC";
        }
        try {
            mPreparedStatement = mConnection.prepareStatement(sql);
            mResultSet = mPreparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        selectCarbyAdmin();
    }

    public void selectCarbyAdmin(){
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过参数查询租车记录
     * @param userName 用户名
     */
    @Override
    public void getRecordbyUser(String userName) throws SQLException {
        String sql = null;
        list = new ArrayList<>();
        sql = "SELECT t_record.id,t_car.id,t_car.model,t_record.payment,t_car.t_comments,t_brand.name,t_category.name,t_record.start_date,t_record.return_date "
                + "FROM t_record,t_car,t_brand,t_category,t_user WHERE t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id AND t_record.user_id = t_user.id"
                + " AND t_user.username = ? AND t_car.id = t_record.car_id ORDER BY t_record.id ASC";
        mPreparedStatement = mConnection.prepareStatement(sql);
        mPreparedStatement.setString(1,userName);
        mResultSet = mPreparedStatement.executeQuery();
        for (int i = 0; mResultSet.next(); i++) {
            for (int j = 1; j <= 9; j++) {
                list.add(mResultSet.getString(j));
            }
        }
        System.out.println("编号  汽车编号  汽车名称  租金总额   备注           品牌        类型        借车时间                    还车时间");
        int length = list.size();
        for (int i = 1; i <= length; i++) {
            System.out.print(list.remove(0) + "\t\t");
            if (i % 9 == 0) {
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
     * 查询所有租车记录
     */
    @Override
    public void getRecordbyAdmin() throws SQLException {
        String sql = null;
        list = new ArrayList<>();
        sql = "SELECT t_record.id,t_car.id,t_car.model,t_record.payment,t_car.t_comments,t_brand.name,t_category.name,t_record.start_date,t_record.return_date "
                + "FROM t_record,t_car,t_brand,t_category WHERE t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id AND t_car.id = t_record.car_id ORDER BY t_record.id ASC";
        mPreparedStatement = mConnection.prepareStatement(sql);
        mResultSet = mPreparedStatement.executeQuery();
        for (int i = 0; mResultSet.next(); i++) {
            for (int j = 1; j <= 9; j++) {
                list.add(mResultSet.getString(j));
            }
        }
        System.out.println("编号  汽车编号  汽车名称  租金总额   备注           品牌        类型        借车时间                    还车时间");
        int length = list.size();
        for (int i = 1; i <= length; i++) {
            System.out.print(list.remove(0) + "\t\t");
            if (i % 9 == 0) {
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
     * 按类型编号查询车
     * @param categoryNumber 类型编号
     */
    @Override
    public void getCarbyCategory(int categoryNumber) {
        String sql = null;
        list = new ArrayList<>();
        if (categoryNumber == 1) {
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status "
                    + "FROM t_car,t_brand,t_category WHERE t_category.id = 1 AND t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        }else if (categoryNumber == 2){
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status "
                    + "FROM t_car,t_brand,t_category WHERE t_category.id = 2 AND t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        }else if (categoryNumber == 3){
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status "
                + "FROM t_car,t_brand,t_category WHERE t_category.id = 3 AND t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        }else if (categoryNumber == 4){
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status "
                    + "FROM t_car,t_brand,t_category WHERE t_category.id = 4 AND t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        }
        getCarbyUser(sql);
    }

    /**
     * 按品牌编号查询车
     * @param brandNumber 品牌编号
     */
    @Override
    public void getCarbyBrand(int brandNumber) {
        String sql = null;
        list = new ArrayList<>();
        if (brandNumber == 1) {
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status "
                    + "FROM t_car,t_brand,t_category WHERE t_brand.id = 1 AND t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        }else if (brandNumber == 2){
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status "
                    + "FROM t_car,t_brand,t_category WHERE t_brand.id = 2 AND t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        }else if (brandNumber == 3){
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status "
                    + "FROM t_car,t_brand,t_category WHERE t_brand.id = 3 AND t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        }else if (brandNumber == 4){
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status "
                    + "FROM t_car,t_brand,t_category WHERE t_brand.id = 4 AND t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        }else if (brandNumber == 5){
            sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status "
                    + "FROM t_car,t_brand,t_category WHERE t_brand.id = 5 AND t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC";
        }
        getCarbyUser(sql);
    }

    /**
     * 管理员按汽车编号查询汽车
     * @param carNumber 汽车编号
     */
    @Override
    public void getCarbyId(int carNumber) {
        String sql = null;
        list = new ArrayList<>();
        sql = "SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/天',t_car.status,t_car.useable "
                + "FROM t_car,t_brand,t_category WHERE t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id AND t_car.id = ?";
        try {
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setInt(1,carNumber);
            mResultSet = mPreparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        selectCarbyAdmin();
    }

    /**
     * 管理员添加汽车
     * @return 0 添加成功，1 添加失败
     */
    @Override
    public void addCarbyAdmin() throws SQLException {
        System.out.println("分别输入以下信息：");
        System.out.println("================================");
        System.out.println("品牌如下:");
        System.out.println("品牌编号 品牌名\n1\t\t标致\n2\t\t大众\n3\t\t奥迪\n4\t\t奔驰\n5\t\t宝马");
        System.out.print("请选择品牌编号:");
        int brandId = getScanner().nextInt();
        System.out.println("----------------------------");
        System.out.println("类型如下:");
        System.out.println("品牌编号 品牌名\n1\t\t紧凑型\n2\t\t舒适型\n3\t\tSUV(+)\n4\t\t精英型");
        System.out.print("请选择类型编号:");
        int categoryId = getScanner().nextInt();
        System.out.println("----------------------------");
        System.out.print("型号:");
        String carName = getScanner().next();
        System.out.println("----------------------------");
        System.out.print("车牌号:");
        String carNumber = getScanner().next();
        System.out.println("----------------------------");
        System.out.print("概要:");
        String carComments = getScanner().next();
        System.out.println("----------------------------");
        System.out.print("颜色:");
        String carColor = getScanner().next();
        System.out.println("----------------------------");
        System.out.print("汽车价格:");
        int carPrice = getScanner().nextInt();
        System.out.println("----------------------------");
        System.out.print("每日租金:");
        int carRent = getScanner().nextInt();
        System.out.println("----------------------------");
        System.out.print("是否可租（0 可租，1 不可租）:");
        int carStatus = getScanner().nextInt();
        System.out.println("----------------------------");
        System.out.print("是否上架（0 上架，1 下架）:");
        int carUseable = getScanner().nextInt();
        System.out.println("----------------------------");
        int seq = getCarId();
        String sql = "insert into t_car values(?,?,?,?,?,?,?,?,?,?,?)";
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setInt(1, seq);
            mPreparedStatement.setString(2, carNumber);
            mPreparedStatement.setInt(3, brandId);
            mPreparedStatement.setString(4, carName);
            mPreparedStatement.setString(5, carColor);
            mPreparedStatement.setInt(6, categoryId);
            mPreparedStatement.setString(7, carComments);
            mPreparedStatement.setInt(8, carPrice);
            mPreparedStatement.setInt(9, carRent);
            mPreparedStatement.setInt(10, carStatus);
            mPreparedStatement.setInt(11, carUseable);
            mPreparedStatement.executeUpdate();
            System.out.println("汽车添加成功!");
    }

    public void chargeCarbyAdmin(int carNumber) throws SQLException {
        String sql = null;
        getCarbyId(carNumber);
        System.out.println("请输入要修改的内容编号：\n1.租赁价格\t2.上架下架");
            String s = getScanner().next();
            if (s.equals("1")){
                System.out.println("请输入新的租赁价格：");
                int price = getScanner().nextInt();
                sql = "update t_car set rent = ? where id = ?";
                System.out.println(sql);
                mPreparedStatement = mConnection.prepareStatement(sql);
                mPreparedStatement.setInt(1, price);
                mPreparedStatement.setInt(2, carNumber);
                System.out.println("更新成功!");
            }else if (s.equals("2")){
                System.out.println("请选择状态：0.上架\t1.下架");
                int status = getScanner().nextInt();
                sql = "update t_car set status = ? where id = ?";
                mPreparedStatement = mConnection.prepareStatement(sql);
                mPreparedStatement.setInt(1, status);
                mPreparedStatement.setInt(2, carNumber);
                mPreparedStatement.executeUpdate();
                System.out.println("更新成功!");
            }else {
                System.out.println("请按要求输入！");
                chargeCarbyAdmin(carNumber);
            }
    }

    /**
     * 用户还车
     */
    @Override
    public void borrowCar(String userName,int carNumber) throws SQLException {
        String sql = null;
        int userid = 0;
        int useable = 0;
        int status = 0;
        int carid = 0;
        int recordid = 0;
        String starttime = null;
        String returntime = null;
        double price = 0;
        double payment = 0;

        sql = "SELECT id FROM t_user WHERE username = ?";
        mPreparedStatement = mConnection.prepareStatement(sql);
        mPreparedStatement.setString(1, userName);
        mResultSet = mPreparedStatement.executeQuery();
        if (mResultSet.next()) {
            userid = mResultSet.getInt("id");
        }
        sql = "select status,useable from t_car where id = ?";
        mPreparedStatement = mConnection.prepareStatement(sql);
        mPreparedStatement.setInt(1,carNumber);
        mResultSet = mPreparedStatement.executeQuery();
        if (mResultSet.next()) {
            useable = mResultSet.getInt("useable");
            status = mResultSet.getInt("status");
        }
        if (useable == 0 && status == 1){
            sql = "select car_id from t_record where user_id = ?";
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setInt(1,userid);
            mResultSet = mPreparedStatement.executeQuery();
            list = new ArrayList<>();
            for (int i = 0; mResultSet.next(); i++) {
                    list.add(mResultSet.getString("car_id"));
            }
            length = list.size();
            for (int i = 1;i <= length;i++){
                carid = Integer.parseInt(list.get(0));
                list.remove(0);
                if (carid == carNumber){
                    sql = "select t_record.id,t_car.rent,t_record.start_date from t_record,t_car where t_record.user_id = ? "
                            + "AND t_record.car_id = t_car.id AND t_car.status = 1 AND t_record.car_id = ? AND rownum = 1 order by t_record.id desc";
                    mPreparedStatement = mConnection.prepareStatement(sql);
                    mPreparedStatement.setInt(1,userid);
                    mPreparedStatement.setInt(2,carid);
                    mResultSet = mPreparedStatement.executeQuery();
                    list = new ArrayList<>();
                    for (i = 0; mResultSet.next(); i++) {
                        for (int j = 1; j <= 3; j++) {
                            list.add(mResultSet.getString(j));
                        }
                    }
                    recordid = Integer.parseInt(list.get(0)) ;
                    price = Integer.parseInt(list.get(1));
                    starttime = list.get(2);
                    returntime = Tools.Date();
                    payment = Tools.ment(starttime,returntime) * price;

                    sql = "update t_record set return_date = to_date(?,'yyyy-MM-dd HH24:mi:ss'),payment = ? where id = ?";
                    mPreparedStatement = mConnection.prepareStatement(sql);
                    mPreparedStatement.setString(1, Tools.Date());
                    mPreparedStatement.setDouble(2, payment);
                    mPreparedStatement.setInt(3, recordid);
                    mResultSet = mPreparedStatement.executeQuery();

                    sql = "update t_car set status = 0 where id = ?";
                    mPreparedStatement = mConnection.prepareStatement(sql);
                    mPreparedStatement.setInt(1,carid);
                    mResultSet = mPreparedStatement.executeQuery();
                    System.out.println("还车成功,还车信息如下:");
                    System.out.println("=============================================================");
                    System.out.println("订单号 汽车名称\t\t每日租金 总租金\t备注\t\t\t品牌\t类型\t借车时间\t\t\t\t\t还车时间");
                    sql = "select t_record.id,t_car.model,t_car.rent,t_record.payment,t_car.t_comments,t_brand.name,t_category.name,t_record.start_date,t_record.return_date "
                            + "FROM t_record,t_car,t_brand,t_category WHERE t_record.id = ? and t_record.car_id = ? and t_car.id = t_record.user_id and t_brand.id = t_car.id and t_category.id = t_car.id";
                    mPreparedStatement = mConnection.prepareStatement(sql);
                    mPreparedStatement.setInt(1,recordid);
                    mPreparedStatement.setInt(2,carid);
                    mResultSet = mPreparedStatement.executeQuery();
                    list = new ArrayList<>();
                    for (i = 0; mResultSet.next(); i++) {
                        for (int j = 1; j <= 9; j++) {
                            list.add(mResultSet.getString(j));
                        }
                    }
                    length = list.size();
                    for (i = 1;i <= length;i++){
                        System.out.print(list.remove(0) + "\t\t");
                        if (i % 9 == 0){
                            System.out.println();
                        }
                    }
                }else {
                    System.out.println("你没有租此车！");
                }
            }
        }else {
            System.out.println("该车未出租或你没有租此车！");
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
     * 用户租车
     */
    @Override
    public void rentCar(String userName,int carNumber) throws SQLException {
        String sql;
        int userid = 0;
        int useable = 0;
        int status = 0;
        int seq = getRecordId();
        sql = "select status,useable from t_car where id = ?";
        mPreparedStatement = mConnection.prepareStatement(sql);
        mPreparedStatement.setInt(1, carNumber);
        mResultSet = mPreparedStatement.executeQuery();
        if (mResultSet.next()) {
            useable = mResultSet.getInt("useable");
            status = mResultSet.getInt("status");
        }
        if (useable == 0 && status == 0){
            sql = "select id from t_user where username = ?";
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setString(1, userName);
            mResultSet = mPreparedStatement.executeQuery();
            if (mResultSet.next()) {
                userid = mResultSet.getInt("id");
            }

            sql = "insert into t_record (id,user_id,car_id,start_date) values(?,?,?,to_date(?,'yyyy-MM-dd HH24:mi:ss'))";
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setInt(1,seq);
            mPreparedStatement.setInt(2,userid);
            mPreparedStatement.setInt(3,carNumber);
            mPreparedStatement.setString(4,Tools.Date());
            try{
                mResultSet = mPreparedStatement.executeQuery();
                sql = "update t_car set status = 1 where id = ?";
                mPreparedStatement = mConnection.prepareStatement(sql);
                mPreparedStatement.setInt(1,carNumber);
                mPreparedStatement.executeUpdate();

                System.out.println("租车成功,租车信息如下:");
                System.out.println("=============================================================");
                System.out.println("订单号" + "汽车名称" + "\t" + "每日租金" + "\t" + "备注" + "\t" + "品牌" + "\t" + "类型"
                        + "\t" + "借车时间");
            }
            catch (SQLIntegrityConstraintViolationException e){
                System.out.println("此车未上架！");
            }
            list = new ArrayList<>();
            sql = "select t_record.id,t_car.model,t_car.rent,t_car.t_comments,t_brand.name,t_category.name,t_record.start_date from t_record,t_car,t_category,t_brand "
            + "where t_record.id = ? AND t_car.id = ? AND t_category.id = t_car.id AND t_brand.id = t_car.id";
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setInt(1,seq);
            mPreparedStatement.setInt(2,carNumber);
            mResultSet = mPreparedStatement.executeQuery();
            for (int i = 0; mResultSet.next(); i++) {
                for (int j = 1; j <= 7; j++) {
                    list.add(mResultSet.getString(j));
                }
            }
            int length = list.size();
            for (int i = 1;i <= length;i++){
                System.out.print(list.remove(0) + "\t\t");
                if (i % 7 == 0){
                    System.out.println();
                }
            }
        }else {
            System.out.println("该车已出租或未上架");
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
     * 获取记录表的序列值
     * @return 序列值
     * @throws SQLException
     */
    public int getRecordId() throws SQLException {
        String sql = "select t_record_id_seq.nextval from dual";
        mPreparedStatement = mConnection.prepareStatement(sql);
        mResultSet = mPreparedStatement.executeQuery();
        int seq = 0;
        if (mResultSet.next()){
            seq = mResultSet.getInt(1);
        }
        if (mResultSet != null && !mResultSet.isClosed()){
            mResultSet.close();
            mResultSet = null;
        }
        if (mPreparedStatement != null && !mPreparedStatement.isClosed()){
            mPreparedStatement.close();
            mPreparedStatement = null;
        }
        return seq;
    }

    /**
     * 获取汽车表序列
     * @return 下一个值
     * @throws SQLException
     */
    public int getCarId() throws SQLException {
        String sql = "select t_car_id_seq.nextval from dual";
        mPreparedStatement = mConnection.prepareStatement(sql);
        mResultSet = mPreparedStatement.executeQuery();
        int seq = 0;
        if (mResultSet.next()){
            seq = mResultSet.getInt(1);
        }
        if (mResultSet != null && !mResultSet.isClosed()){
            mResultSet.close();
            mResultSet = null;
        }
        if (mPreparedStatement != null && !mPreparedStatement.isClosed()){
            mPreparedStatement.close();
            mPreparedStatement = null;
        }
        return seq;
    }
}
