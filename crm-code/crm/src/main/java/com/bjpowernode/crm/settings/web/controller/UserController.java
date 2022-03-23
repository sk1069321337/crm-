package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.Utils.MD5Util;
import com.bjpowernode.crm.Utils.PrintJson;
import com.bjpowernode.crm.Utils.ServiceFactory;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if("/settings/user/login.do".equals(path)){
            login(request,response);
        }
    }
    private void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        pwd = MD5Util.getMD5(pwd);
        String ip = request.getRemoteAddr();
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        try{
            User user = us.login(username,pwd,ip);
            request.getSession().setAttribute("user",user);
            PrintJson.printJsonFlag(response,true);
        }catch (Exception e){
            String msg = e.getMessage();
            HashMap<String, Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }

    }
}
