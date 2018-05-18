package com.service;

import com.bean.DbConfig;
import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置
 * @author Administrator
 */
public class ReadConfig {
    /**
     * 读取数据库配置文件
     * @return 数据库实体对象
     */
    public DbConfig readDbConfig(){
        Properties mPro = new Properties();
        try {
            mPro.load(ReadConfig.class.getClassLoader().getResourceAsStream("config/db_config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DbConfig mDbConfig = new DbConfig();
        mDbConfig.setDriver(mPro.getProperty("driver"));
        mDbConfig.setUrl(mPro.getProperty("url"));
        mDbConfig.setUser(mPro.getProperty("user"));
        mDbConfig.setPwd(mPro.getProperty("pwd"));
        return mDbConfig;
    }
}
