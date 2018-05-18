package com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取数据库连接
 * @author Administrator
 */
public class DbHelp {
    private String driver;
    private String url;
    private String user;
    private String pwd;
    private Connection mConnection;

    /**
     * 初始化配置信息
     * @throws IOException
     */
    public void intoConfigInfo() throws IOException {
        Properties mProperties = new Properties();
        mProperties.load(DbHelp.class.getClassLoader().getResourceAsStream("config/db_config.properties"));
        driver = mProperties.getProperty("driver");
        url = mProperties.getProperty("url");
        user = mProperties.getProperty("user");
        pwd = mProperties.getProperty("pwd");
    }

    /**
     * 获取数据库连接对象 java.sql.Connection
     * @return 数据库连接对象
     */
    public Connection getmConnection(){
        if (mConnection == null){
            try {
                intoConfigInfo();
                mConnection = DriverManager.getConnection(url,user,pwd);
            } catch (SQLException e) {
                System.out.println("获取数据库连接失败！");
            } catch (IOException e) {
                System.out.println("读取配置文件失败！");
            }
        }
        return mConnection;
    }

    /**
     * 关闭数据库连接，关闭成功isClose会返回true
     * @param connection
     */
    public void closeConnection(Connection connection){
        try {
            if (connection != null && !connection.isClosed()){
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
