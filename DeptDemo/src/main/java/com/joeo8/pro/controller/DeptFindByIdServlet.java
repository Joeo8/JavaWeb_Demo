package com.joeo8.pro.controller;

import com.joeo8.pro.dao.DeptDao;
import com.joeo8.pro.entity.dept;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeptFindByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //请求 -- 处理 -- 响应
        String d_id = null;
        DeptDao dao = new DeptDao();
        dept dept = null;
        PrintWriter out = null;
        //1. 调用请求对象读取请求头参数得到部门编号
        d_id = request.getParameter("d_id");
        //2. 调用DAO对象推送SQL命令
        dept = dao.findById(d_id);
        //3. 调用响应对象将部门详细信息结合table标签写入到响应体
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
         out.println("<center>");
         out.println("<form action='/myWeb/dept/update'>");
        out.println("<table border = '2'>");
        out.println("<tr>");
        out.println("<td>部门编号</td>");
        out.println("<td><input type = 'text' name = 'd_id' readOnly value='"+dept.getD_id()+"'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>部门名称</td>");
        out.println("<td><input type = 'text' name = 'd_name' value='"+dept.getD_name()+"'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>部门位置</td>");
        out.println("<td><input type = 'text' name = 'd_loc' value='"+dept.getD_loc()+"'></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td><input type='submit' value='更新部门'></td>");
        out.println("<td><input type = 'reset' value='重置内容'></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</form>");
        out.println("</center>");
    }
}
