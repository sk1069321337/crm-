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
import com.bjpowernode.crm.workbench.service.ActivitService;
import com.bjpowernode.crm.workbench.service.impl.ActivitServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if("/workbench/activity/getUserList.do".equals(path)){
            getUserList(request,response);
        }if("/workbench/activity/save.do".equals(path)){
            save(request,response);
        }if("/workbench/activity/pageList.do".equals(path)){
            pageList(request,response);
        }if("/workbench/activity/delete.do".equals(path)){
            delete(request,response);
        }if("/workbench/activity/editGetData.do".equals(path)){
            getData(request,response);
        }if("/workbench/activity/updata.do".equals(path)){
            updata(request,response);
        }
    }

    private void updata(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动修改");
        ActivitService activitService = (ActivitService) ServiceFactory.getService(new ActivitServiceImpl());
        Activit activit = new Activit();
        activit.setId(request.getParameter("id"));
        activit.setOwner(request.getParameter("owner"));
        activit.setName(request.getParameter("name"));
        activit.setStartDate(request.getParameter("startDate"));
        activit.setEndDate(request.getParameter("endDate"));
        activit.setCost(request.getParameter("cost"));
        activit.setDescription(request.getParameter("description"));
        activit.setEditTime(DateTimeUtil.getSysTime());
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        activit.setEditBy(editBy);
        Boolean success = activitService.updata(activit);
        PrintJson.printJsonFlag(response,success);

    }

    private void getData(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("修改调取数据进入成功");
        String id = request.getParameter("id");
        ActivitService as = (ActivitService) ServiceFactory.getService(new ActivitServiceImpl());
        Map<String,Object> data = as.getData(id);
        PrintJson.printJsonObj(response,data);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("删除数据进入成功");
        String[] ids = request.getParameterValues("id");
        ActivitService as = (ActivitService) ServiceFactory.getService(new ActivitServiceImpl());
        boolean b =  as.delete(ids);
        PrintJson.printJsonFlag(response,b);
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("分页查询进入成功");
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNostr = request.getParameter("pageNo");
        String pageSizestr = request.getParameter("pageSize");
        int pageNo = Integer.valueOf(pageNostr);
        int pageSize = Integer.valueOf(pageSizestr);
        int count = (pageNo-1)*pageSize;
        Map<String, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);
        map.put("count",count);
        ActivitService activitService = (ActivitService) ServiceFactory.getService(new ActivitServiceImpl());
        PageListVo<Activit> vo = activitService.pageList(map);
        PrintJson.printJsonObj(response,vo);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        ActivitService activitService = (ActivitService) ServiceFactory.getService(new ActivitServiceImpl());
        Activit activit = new Activit();
        activit.setId(UUIDUtil.getUUID());
        activit.setOwner(request.getParameter("owner"));
        activit.setName(request.getParameter("name"));
        activit.setStartDate(request.getParameter("startDate"));
        activit.setEndDate(request.getParameter("endDate"));
        activit.setCost(request.getParameter("cost"));
        activit.setDescription(request.getParameter("description"));
        activit.setCreateTime(DateTimeUtil.getSysTime());
        String CreateBy = ((User)request.getSession().getAttribute("user")).getName();
        activit.setCreateBy(CreateBy);
        Boolean success = activitService.save(activit);
        PrintJson.printJsonFlag(response,success);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = us.getUserList();
        PrintJson.printJsonObj(response,userList);
    }
}
