package com.bjpowernode.crm.webFilter;

import com.bjpowernode.crm.settings.domain.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String path1 = request.getContextPath();
        if("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)){
            //如果是以上两个路径则放行
            chain.doFilter(request,response);
        }else{
            if (user != null){
                chain.doFilter(request,response);
            }else{
                response.sendRedirect(path1+"/login.jsp");
                }
            }
        }
    }

