package com.joeo8.pro.listener;

import com.joeo8.pro.util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PoolListener implements ServletContextListener {
    //在Tomcat启动时，预先创建一定数量的Connection
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JdbcUtil util = new JdbcUtil();
        Map map = new ConcurrentHashMap();
        for (int i = 0; i < 20; i++) {
            Connection con = util.getCon();
//            System.out.println("启动时创建Connection：" + con);
            map.put(con, true); //true表示连接通道Connection空闲状态
        }
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("pool", map);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        Map map = (ConcurrentHashMap) servletContext.getAttribute("pool");
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Connection con = (Connection) iterator.next();
            if (con != null) {
                System.out.println("Connection " + con + " 被销毁");
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}
