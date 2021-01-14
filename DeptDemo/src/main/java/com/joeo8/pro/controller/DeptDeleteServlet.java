package com.joeo8.pro.controller;

import com.joeo8.pro.dao.DeptDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeptDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String d_id = null;
        DeptDao dao = new DeptDao();
        int flag = 0;
        PrintWriter out = null;
        //1.调用请求对象读取请求头参数得到的部门编号
        d_id = request.getParameter("d_id");
        //2.调用DAO对象推送delete命令到数据库服务器执行并待会处理结果
        flag = dao.delete(d_id);
        //3.调用响应对象将处理结果写入到响应体中
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        if (flag == 1) {
            out.print("<center><font style='color:green; font-size:32px'>部门删除成功！</font></center>");
            RequestDispatcher report = request.getRequestDispatcher("/dept/find");
             report.forward(request,response);
        } else {
            out.print("<center><font style='color:red ;    font-size:32px'>部门删除失败！</font></center>");
        }
    }
}
