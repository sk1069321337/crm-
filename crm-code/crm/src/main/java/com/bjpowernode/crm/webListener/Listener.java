package com.bjpowernode.crm.webListener;

import com.bjpowernode.crm.Utils.ServiceFactory;
import com.bjpowernode.crm.settings.domain.DicType;
import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.service.DicService;
import com.bjpowernode.crm.settings.service.impl.DicServiceImpl;
import com.bjpowernode.crm.workbench.service.ClueService;
import com.bjpowernode.crm.workbench.service.impl.ClueServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("执行数据字典初始化");
        //在监听器中监听到上下文域创建后，调取数据字典服务，保存到content域中，属于缓存机制，这样就不用每次都连接数据库，直接使用；

        ServletContext servletContext = sce.getServletContext();
        DicService dicService = (DicService) ServiceFactory.getService(new DicServiceImpl());
        Map<String, List<DicValue>> map = dicService.getClue();
        Set<String> set = map.keySet();
        for(String key:set){
            servletContext.setAttribute(key,map.get(key));
        }
        System.out.println("数字字典初始化已完成");
    }
}
