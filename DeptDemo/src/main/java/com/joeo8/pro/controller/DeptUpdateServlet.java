package com.joeo8.pro.controller;

import com.joeo8.pro.dao.DeptDao;
import com.joeo8.pro.entity.dept;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeptUpdateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 请求 -- 处理  -- 响应
        String d_id, d_name, d_loc;
        DeptDao dao = new DeptDao();
        dept dept = null;
        int flag = 0;
        PrintWriter out = null;
        //1. 调用请求对象读取  请求头参数信息，得到需要更新的部门信息
        d_id = request.getParameter("d_id");
        d_name = request.getParameter("d_name");
        d_loc = request.getParameter("d_loc");
        dept = new dept(Integer.valueOf(d_id),d_name,d_loc);
        //2. 调用DAO对象推送SQL命令
        flag = dao.update(dept);
        //3. 调用响应对象将处理结果写入到响应体中
        response.setContentType("text/html; charset=utf-8");
        out = response.getWriter();
         if (flag == 1 ){
            out.print("<center><font style='color:green; font-size:32px'>部门更新成功！</font></center>");
            RequestDispatcher report = request.getRequestDispatcher("/dept/find");
             report.forward(request,response);
         }else{
            out.print("<center><font style='color:red ; font-size:32px'>部门更新失败！</font></center>");
        }

    }
}
