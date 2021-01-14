package com.joeo8.pro.controller;

import com.joeo8.pro.dao.DeptDao;
import com.joeo8.pro.entity.dept;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DeptFindServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeptDao dao = new DeptDao();
        PrintWriter out = null;   //注意：在一个方法中用到的局部变量一定要在方法开始中声明
       //0.询问来访者身份
        HttpSession session = request.getSession(false);
        if (session == null){
            request.getRequestDispatcher("/login_error.html").forward(request,response);
            return ;
        }
        //1. 调用DAO对象推送查询命令得到部门信息集合(List<Dept>)
        List<dept> deptList = dao.findAll();
        //2.调用响应对象将部门信息结合table标签写入到响应体中
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        out.println("<script type='text/javascript' src='/myWeb/tableJs.js'> </script>");
        out.println("<center>");
        out.println("<table border = '2'>");
        out.println("<tr>");
        out.println("<td><input type = 'checkbox' onclick='fun1();'/>全选或全不选</td>");
        out.println("<td>部门编号</td>");
        out.println("<td>部门名称</td>");
        out.println("<td>部门位置</td>");
        out.println("<td colspan = '2'>操作</td>");
        out.println("</tr>");
        for (dept dept : deptList) {
            out.println("<tr>");
            out.println("<td><input type = 'checkbox'/></td>");
            out.println("<td>" + dept.getD_id() + "</td>");
            out.println("<td>" + dept.getD_name() + "</td>");
            out.println("<td>" + dept.getD_loc() + "</td>");
            out.println("<td><a href = '/myWeb/dept/delete?d_id="+dept.getD_id()+"'>部门删除</a></td>");
            out.println("<td><a href = '/myWeb/dept/findById?d_id="+dept.getD_id()+"'>部门详情</a></td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</center>");
    }
}
