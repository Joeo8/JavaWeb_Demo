package com.joeo8.pro.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//工具类，实现对方法实现的封装
public class JdbcUtil {
    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    ServletContext application = null;

    //------------------------------------方法重载-----------------------------------------
    public Connection getCon(HttpServletRequest request) {
        application = request.getServletContext();
        Map map = (ConcurrentHashMap) application.getAttribute("pool");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
           conn = (Connection) it.next();
            System.out.println(conn);
            boolean flag = (boolean) map.get(conn);
            if (flag == true) {
                map.put(conn, false);
                return conn;
            }
        }
        return  null;
    }

    public PreparedStatement getPsmt(String sql,HttpServletRequest request) {
        try {
            psmt = getCon(request).prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
        }
        return  psmt;
    }

     public void close(HttpServletRequest request) {
        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //将使用过的Conn重新设置为空闲状态
             application = request.getServletContext();
            Map map = (ConcurrentHashMap) application.getAttribute("pool");
            map.put(conn,true);
        }
    }

    //---------------------------------------方法重载------------------------------------

    //简化获取通道连接的功能
    public Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    //手动实现注册 -- 反射机制
            conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/joeo8?serverTimezone=GMT", "root", "123456");
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    //简化建造通信方式的功能
    public PreparedStatement getPsmt(String sql) {
        try {
            psmt = getCon().prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return psmt;
    }

    //简化销毁通道的功能
    public void close() {
        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        close();
    }
}
