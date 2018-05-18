package com.bean;

public class DbConfig {
    private String driver;
    private String url;
    private String user;
    private String pwd;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public DbConfig() {
    }

    public DbConfig(String driver, String url, String user, String pwd) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "DbConfig{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
