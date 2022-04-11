package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.Utils.DateTimeUtil;
import com.bjpowernode.crm.Utils.PrintJson;
import com.bjpowernode.crm.Utils.ServiceFactory;
import com.bjpowernode.crm.Utils.UUIDUtil;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.workbench.domain.Tran;
import com.bjpowernode.crm.workbench.service.ClueService;
import com.bjpowernode.crm.workbench.service.CustomerService;
import com.bjpowernode.crm.workbench.service.impl.ClueServiceImpl;
import com.bjpowernode.crm.workbench.service.impl.CustomerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class TransController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到交易控制器");
        String path = request.getServletPath();
        if("/workbench/transaction/save.do".equals(path)){
            save(request,response);
        }if("/workbench/transaction/getCustomerName.do".equals(path)){
            getCustomerName(request,response);
        }
    }

    private void getCustomerName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("查询客户名称 根据传入的数据模糊查询");
        String name = request.getParameter("name");
        CustomerService cs = (CustomerService) ServiceFactory.getService(new CustomerServiceImpl());
        List<String> stringList = cs.getCustomerName(name);
        PrintJson.printJsonObj(response,stringList);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //走后台查所有者
        System.out.println("查询所有者");
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList =  us.getUserList();
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("/workbench/transaction/save.jsp").forward(request,response);
    }

}
