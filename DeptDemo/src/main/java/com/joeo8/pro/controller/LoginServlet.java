package com.joeo8.pro.controller;

import com.joeo8.pro.dao.EmpDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读参--处理--请求
        String username = null, password = null, ck = null;
        EmpDao dao = new EmpDao();
        int flag = 0;
        RequestDispatcher report = null;
        //1.读取请求体参数，得到用户信息
        request.setCharacterEncoding("utf-8");
        username = request.getParameter("username");
        password = request.getParameter("password");
        ck = request.getParameter("ck");              //用于cookie十天免登录
        //2.调用DAO对象，查询信息的真实性

        //当用户注册过会员卡再次登录时，直接进入主页.无需登录操作
        //新用户初次登陆，正常显示login
        if (username == null && password == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String keyName = cookie.getName();
                    if ("joeo8".equals(keyName)) {
                        request.getSession();                           //为用户申请令牌
                        request.getRequestDispatcher("/index.html").forward(request, response);
                        return;
                    }
                }
            }
            request.getRequestDispatcher("login.html").forward(request, response);
            return;
        }


        //情况一： 用户主动提供信息
        if (!"".equals(username) && !"".equals(password)) {
            //调用DAO核实用户身份信息
            flag = dao.login(username, password);
            if (flag == 1) {
                //询问是否享受十天免登录
                if (ck != null) {
                    Cookie cookie = new Cookie("joeo8", username);
                    cookie.setMaxAge(60 * 60 * 24 * 10);
                    response.addCookie(cookie);
                }
                request.getSession();                           //为用户申请令牌
                request.getRequestDispatcher("/index.html").forward(request, response);
                return;
            }
        }
        //情况二：用户主动提供了十天免登录会员卡
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String keyName = cookie.getName();
                if ("joeo8".equals(keyName)) {
                    request.getRequestDispatcher("/index.html").forward(request, response);
                    return;
                }
            }
        }

        //情况三：来访用户没有提供合法信息，也没有提供十天免登录会员卡,应拒绝进入
        request.getRequestDispatcher("/login_error.html").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
