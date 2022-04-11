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
import com.bjpowernode.crm.workbench.domain.Clue;
import com.bjpowernode.crm.workbench.domain.Tran;
import com.bjpowernode.crm.workbench.service.ActivitService;
import com.bjpowernode.crm.workbench.service.ClueService;
import com.bjpowernode.crm.workbench.service.impl.ActivitServiceImpl;
import com.bjpowernode.crm.workbench.service.impl.ClueServiceImpl;
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
        if("/workbench/clue/getUserList.do".equals(path)){
            getUserList(request,response);
        }if("/workbench/clue/save.do".equals(path)){
            save(request,response);
        }if("/workbench/clue/detail.do".equals(path)){
            detail(request,response);
        }if("/workbench/clue/getActivityListByClueId.do".equals(path)){
            getActivityListByClueId(request,response);
        }if("/workbench/clue/unbund.do".equals(path)){
            unbund(request,response);
        }if("/workbench/clue/getUserListbyClue.do".equals(path)){
            getUserListbyClue(request,response);
        }if("/workbench/clue/bund.do".equals(path)){
            bund(request,response);
        }if("/workbench/clue/getAlistByAname.do".equals(path)){
            getAlistByAname(request,response);
        }if("/workbench/clue/convert.do".equals(path)){
            convert(request,response);
        }
    }

    private void convert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("执行转换线索转换");
        String clueId = request.getParameter("clueId");
        System.out.println("这里是线索id"+clueId);
        String flag = request.getParameter("flag");
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        Tran t = null;
        if("a".equals(flag)){
            System.out.println("创建交易");
            //把表单中创建的数据封装到t中；
            t = new Tran();
            t.setId(UUIDUtil.getUUID());
            t.setName(request.getParameter("name"));
            t.setMoney(request.getParameter("money"));
            t.setExpectedDate(request.getParameter("expectedDate"));
            t.setStage(request.getParameter("stage"));
            t.setActivityId(request.getParameter("activityId"));
            t.setCreateTime(DateTimeUtil.getSysTime());
            t.setCreateBy(createBy);
        }
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag1 = clueService.convert(clueId,t,createBy);
        if(flag1){
            response.sendRedirect(request.getContextPath()+"/workbench/clue/index.jsp");
        }

    }

    private void getAlistByAname(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("线索转换里面查询市场活动");
        String aname = request.getParameter("aname");
        ActivitService as = (ActivitService) ServiceFactory.getService(new ActivitServiceImpl());
        List<Activit> activitList = as.getAlistByAname(aname);
        PrintJson.printJsonObj(response,activitList);
    }

    private void bund(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("关联市场活动");
        String cid = request.getParameter("cid");
        String[] aids = request.getParameterValues("aid");
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = cs.bund(cid,aids);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserListbyClue(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("根据线索关联市场活动");
        String aname = request.getParameter("aname");
        String clueId = request.getParameter("clueId");
        HashMap<String, String> map = new HashMap<>();
        map.put("aname",aname);
        map.put("clueId",clueId);
        //获取市场活动列表
        ActivitService as = (ActivitService) ServiceFactory.getService(new ActivitServiceImpl());
        List<Activit> activitList = as.getUserListbyClue(map);
        PrintJson.printJsonObj(response,activitList);
    }

    private void unbund(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动解除关联");
        String id = request.getParameter("id");
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = cs.unbund(id);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getActivityListByClueId(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("根据线索id查关联的市场活动");
        String clueId = request.getParameter("clueId");
        ActivitService as = (ActivitService) ServiceFactory.getService(new ActivitServiceImpl());
        List<Activit> activitList = as.getActivityListByClueId(clueId);
        PrintJson.printJsonObj(response,activitList);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("加载线索详细详细信息页面");
        String id = request.getParameter("id");
        ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        Clue clue = clueService.detail(id);
        request.setAttribute("c",clue);
        request.getRequestDispatcher("/workbench/clue/detail.jsp").forward(request,response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行线索保存");
        //把前端传过来的数据封装到clue，然后保存到数据库；
        Clue clue = new Clue();
        clue.setAddress(request.getParameter("address"));
        clue.setAppellation(request.getParameter("appellation"));
        clue.setCompany(request.getParameter("company"));
        clue.setContactSummary(request.getParameter("contactSummary"));
        clue.setDescription(request.getParameter("description"));
        clue.setCreateBy(((User)request.getSession().getAttribute("user")).getName());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        clue.setEmail(request.getParameter("email"));
        clue.setFullname(request.getParameter("fullname"));
        clue.setId(UUIDUtil.getUUID());
        clue.setJob(request.getParameter("job"));
        clue.setMphone(request.getParameter("mphone"));
        clue.setNextContactTime(request.getParameter("nextContactTime"));
        clue.setOwner(request.getParameter("owner"));
        clue.setPhone(request.getParameter("phone"));
        clue.setSource(request.getParameter("source"));
        clue.setState(request.getParameter("state"));
        clue.setWebsite(request.getParameter("website"));
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean success = cs.save(clue);
        PrintJson.printJsonFlag(response,success);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行线索创建");
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = us.getUserList();
        PrintJson.printJsonObj(response,userList);
    }

}
