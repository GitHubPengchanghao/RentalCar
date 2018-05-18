package com.daoimpl;

import com.bean.User;
import com.dao.UserDao;
import com.service.DbHelp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private DbHelp mDbHelp;
    private PreparedStatement mPreparedStatement;
    private Connection mConnection;
    private ResultSet mResultSet;

    public UserDaoImpl() {
        mDbHelp = new DbHelp();
        mConnection = mDbHelp.getmConnection();
        if (mConnection == null){
            System.out.println("打开数据库失败！");
            return;
        }
    }

    /**
     * 获取用户列表，返回给登录页进行比较
     * @param username
     * @return User类对象
     * @throws SQLException
     */
    @Override
    public User getUserName(String username) throws SQLException {
        User mUser = null;
        String sql = "select * from t_user where username = ?";
        mPreparedStatement = mConnection.prepareStatement(sql);
        mPreparedStatement.setString(1,username);
        mResultSet = mPreparedStatement.executeQuery();
        while (mResultSet.next()){
            mUser = new User();
            mUser.setUserId(mResultSet.getInt("id"));
            mUser.setUserName(mResultSet.getString("username"));
            mUser.setUserPwd(mResultSet.getString("password"));
            mUser.setUserGender(mResultSet.getInt("sex"));
            mUser.setUserIdNumber(mResultSet.getString("id_number"));
            mUser.setUserTel(mResultSet.getString("tel"));
            mUser.setUserAddress(mResultSet.getString("addr"));
            mUser.setUserType(mResultSet.getInt("type"));
        }
        if (mResultSet != null && !mResultSet.isClosed()){
            mResultSet.close();
            mResultSet = null;
        }
        if (mPreparedStatement != null && !mPreparedStatement.isClosed()){
            mPreparedStatement.close();
            mPreparedStatement = null;
        }
        return mUser;
    }

    /**
     * 添加表参数至sql中的User表
     * @param user
     * @return 添加的表对象
     * @throws SQLException
     */
    @Override
    public int addUser(User user) throws SQLException {
        String sql = "insert into t_user values(?,?,?,?,?,?,?,?)";
        int seq = getUserSeq();
        mPreparedStatement = mConnection.prepareStatement(sql);
        mPreparedStatement.setInt(1,seq);
        mPreparedStatement.setString(2,user.getUserName());
        mPreparedStatement.setString(3,user.getUserPwd());
        mPreparedStatement.setInt(4,user.getUserGender());
        mPreparedStatement.setString(5,user.getUserIdNumber());
        mPreparedStatement.setString(6,user.getUserTel());
        mPreparedStatement.setString(7,user.getUserAddress());
        mPreparedStatement.setInt(8,user.getUserType());
        return mPreparedStatement.executeUpdate();
    }

    /**
     * 获取sql中的序列值
     * @return 序列值
     * @throws SQLException
     */
    private int getUserSeq() throws SQLException {
        String sql = "select t_user_id_seq.nextval from dual";
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
     * 确保身份证唯一性
     * @param userIdNumber
     * @return 查询到User表中的数据
     * @throws SQLException
     */
    public User getUserIdNumber(String userIdNumber) throws SQLException {
        User mUser = null;
        String sql = "select * from t_user where id_number = ?";
        mPreparedStatement =mConnection.prepareStatement(sql);
        mPreparedStatement.setString(1,userIdNumber);
        mResultSet = mPreparedStatement.executeQuery();
        while (mResultSet.next()){
            mUser = new User();
            mUser.setUserId(mResultSet.getInt("id"));
            mUser.setUserName(mResultSet.getString("username"));
            mUser.setUserPwd(mResultSet.getString("password"));
            mUser.setUserGender(mResultSet.getInt("sex"));
            mUser.setUserIdNumber(mResultSet.getString("id_number"));
            mUser.setUserTel(mResultSet.getString("tel"));
            mUser.setUserAddress(mResultSet.getString("addr"));
            mUser.setUserType(mResultSet.getInt("type"));
        }
        if (mResultSet != null && !mResultSet.isClosed()){
            mResultSet.close();
            mResultSet = null;
        }
        if (mPreparedStatement != null && !mPreparedStatement.isClosed()){
            mPreparedStatement.close();
            mPreparedStatement = null;
        }
        return mUser;
    }
}
