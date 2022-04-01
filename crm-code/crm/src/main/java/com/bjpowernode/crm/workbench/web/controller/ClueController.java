package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.Utils.DateTimeUtil;
import com.bjpowernode.crm.Utils.PrintJson;
import com.bjpowernode.crm.Utils.ServiceFactory;
import com.bjpowernode.crm.Utils.UUIDUtil;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.vo.PageListVo;
import com.bjpowernode.crm.workbench.domain.Activit;
import com.bjpowernode.crm.workbench.domain.ActivitRemark;
import com.bjpowernode.crm.workbench.service.ActivitService;
import com.bjpowernode.crm.workbench.service.impl.ActivitServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClueController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if("/workbench/activity/getUserList.do".equals(path)){
            //getUserList(request,response);
        }if("/workbench/activity/save.do".equals(path)){
            //save(request,response);
        }
    }

}
