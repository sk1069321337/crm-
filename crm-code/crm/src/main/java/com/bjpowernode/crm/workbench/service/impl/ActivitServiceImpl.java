package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.Utils.SqlSessionUtil;
import com.bjpowernode.crm.vo.PageListVo;
import com.bjpowernode.crm.workbench.dao.ActivitDao;
import com.bjpowernode.crm.workbench.dao.ActivitRemarkDao;
import com.bjpowernode.crm.workbench.domain.Activit;
import com.bjpowernode.crm.workbench.service.ActivitService;

import java.util.List;
import java.util.Map;

public class ActivitServiceImpl implements ActivitService{
    private ActivitDao activitDao = SqlSessionUtil.getSqlSession().getMapper(ActivitDao.class);
    private ActivitRemarkDao activitRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivitRemarkDao.class);
    @Override
    public Boolean save(Activit activit) {
        Boolean flag = true;
        int i = activitDao.save(activit);
        if(i!=1){
            flag = false;
        }
        return flag;
    }

    @Override
    public PageListVo<Activit> pageList(Map<String, Object> map) {
        //取total
        int total = activitDao.getTotalByCondiition(map);
        //取dataList
        List<Activit> dataList = activitDao.getDataListByCondiition(map);
        //将total和dataList封装到vo;
        PageListVo<Activit> vo = new PageListVo<>();
        vo.setTotal(total);
        vo.setDataList(dataList);
        return vo;
    }

    @Override
    public boolean delete(String[] ids) {
        boolean flag = true;
        //查询出需要删除的市场备注总条数
        int a = activitRemarkDao.selectcount(ids);
        //查询出删除的市场备注总条数
        int b = activitRemarkDao.deletecount(ids);
        if(a != b){
            flag = false;
        }
        //删除市场活动
        int c = activitDao.delect(ids);
        if(ids.length != c ){
            flag = false;
        }
        return flag;
    }
}
