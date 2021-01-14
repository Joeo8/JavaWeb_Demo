package com.joeo8.pro.dao;

import com.joeo8.pro.entity.dept;
import com.joeo8.pro.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDao {
    JdbcUtil util = new JdbcUtil();
    PreparedStatement psmt = null;
    //JDBC规范Connection创建与销毁最浪费时间

    //------------------------------------方法重载----------------------------------------
       public int insert(dept dept,HttpServletRequest request) {
        String sql = "Insert into dept (d_name,d_loc) values (?,?)";
        psmt = util.getPsmt(sql,request);
        int flag = 0;
        try {
            psmt.setString(1, dept.getD_name());
            psmt.setString(2, dept.getD_loc());
            flag = psmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(request);
        }
        return flag;
    }

    //-------------------------------------方法重载---------------------------------------

    //推送SQL --  insert 命令
    public int insert(dept dept) {
        String sql = "Insert into dept (d_name,d_loc) values (?,?)";
        psmt = util.getPsmt(sql);
        int flag = 0;
        try {
            psmt.setString(1, dept.getD_name());
            psmt.setString(2, dept.getD_loc());
            flag = psmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        return flag;
    }

    //推送SQL --  select 命令
    public List<dept> findAll() {
        String sql = "Select * from dept";
        psmt = util.getPsmt(sql);
        List<dept> list = new ArrayList();
        ResultSet rs = null;
        try {
            rs = psmt.executeQuery();
            while (rs.next()) {
                Integer d_id = rs.getInt("d_id");
                String d_name = rs.getString("d_name");
                String d_loc = rs.getString("d_loc");
                dept dept = new dept(d_id, d_name, d_loc);
                list.add(dept);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return list;
    }

    //推送SQL -- delete命令
    public int delete(String d_id){
        String sql = "DELETE FROM dept where d_id = ? ";
        psmt = util.getPsmt(sql);
        int flag = 0;
        try {
            psmt.setString(1,d_id);
            flag = psmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        return flag;
    }

    //推送SQL -- findById
    public dept findById(String d_id){
        String sql = "Select * from dept where d_id = ?";
        psmt = util.getPsmt(sql);
        ResultSet rs = null;
        dept dept = null ;
        try {
            psmt.setString(1,d_id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                String d_name = rs.getString("d_name");
                String d_loc = rs.getString("d_loc");
                dept = new dept(Integer.valueOf(d_id), d_name, d_loc);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return dept;
    }

    //推送SQL -- update
    public int update(dept dept){
        String sql = "UPDATE dept SET d_name= ?,d_loc=? where d_id = ?";
        psmt = util.getPsmt(sql);
        int flag = 0;
        try {
            psmt.setString(1, dept.getD_name());
            psmt.setString(2, dept.getD_loc());
           psmt.setInt(3,dept.getD_id());
            flag = psmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        return flag;
    }
    }

