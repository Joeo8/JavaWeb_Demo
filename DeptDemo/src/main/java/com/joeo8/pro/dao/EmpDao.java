package com.joeo8.pro.dao;

import com.joeo8.pro.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpDao {
    JdbcUtil util = new JdbcUtil();
    //login
    public int login (String username,String password){
        String sql = "Select count(*) from emp where username = ? and password = ?";
        PreparedStatement psmt = util.getPsmt(sql);
        ResultSet rs = null ;
        int flag = 0;
        try {
            psmt.setString(1,username);
            psmt.setString(2,password);
            rs= psmt.executeQuery();
            rs.next();
            flag =   rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return flag;
    }
}
