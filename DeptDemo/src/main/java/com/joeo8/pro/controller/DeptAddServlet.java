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
import java.util.Date;
import java.util.Enumeration;

public class DeptAddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  1.调用请求对象读取请求头参数，得到新部门信息
     /*   Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String params = parameterNames.nextElement();
            String parameter = request.getParameter(params);
        }*/
        String d_name , d_loc;
        int flag = 0 ;
        dept dept = null;
        DeptDao dao = new DeptDao();
        PrintWriter out = null ;

        d_name = request.getParameter("d_name");
        d_loc = request.getParameter("d_loc");
        // 2.调用DAO对象推送insert命令处理请求

        dept = new dept(null,d_name,d_loc);
        Date start = new Date();
        flag = dao.insert(dept,request);   //此时平均耗时50ms
        Date end = new Date();
        System.out.println("插入方法共耗时 "+(end.getTime()-start.getTime())+" ms");
        // 3.调用响应对象将处理结果写入到响应体
        response.setContentType("text/html;charset=utf-8");
        out  = response.getWriter();
        if (flag == 1 ){
            out.print("<center><font style='color:green; font-size:32px'>部门添加成功！</font></center>");
//            response.sendRedirect("/myWeb/dept/find");
             RequestDispatcher report = request.getRequestDispatcher("/dept/find");
             report.forward(request,response);
        }else{
            out.print("<center><font style='color:red ; font-size:32px'>部门插入失败！</font></center>");
        }
    }
}
