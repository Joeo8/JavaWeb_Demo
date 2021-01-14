package com.joeo8.pro.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SafeLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //通过拦截对象，向Tomcat索要当前用户的Session
        HttpServletRequest request = (HttpServletRequest) servletRequest;
       HttpSession session = null ;
       String uri = null;
       //1. 通过拦截的请求对象读取请求包中的请求行中的uri（/网站名/资源文件名）
        uri = request.getRequestURI();
        //2.如果本次访问的资源文件与登录验证相关资源文件，应无条件放行
        if (uri.indexOf("login")!=-1){
            filterChain.doFilter(request, servletResponse);
            return ;
        }
        //对于其他资源文件访问，需要用户提供令牌
        session = request.getSession(false);
        if (session == null){
            request.getRequestDispatcher("/login_error.html").forward(servletRequest, servletResponse);
        }else{
             filterChain.doFilter(request, servletResponse);
        }
    }
}
