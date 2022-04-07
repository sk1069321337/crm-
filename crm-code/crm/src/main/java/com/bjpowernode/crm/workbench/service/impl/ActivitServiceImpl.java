package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.Utils.SqlSessionUtil;
import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.vo.PageListVo;
import com.bjpowernode.crm.workbench.dao.ActivitDao;
import com.bjpowernode.crm.workbench.dao.ActivitRemarkDao;
import com.bjpowernode.crm.workbench.domain.Activit;
import com.bjpowernode.crm.workbench.domain.ActivitRemark;
import com.bjpowernode.crm.workbench.service.ActivitService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivitServiceImpl implements ActivitService{
    private ActivitDao activitDao = SqlSessionUtil.getSqlSession().getMapper(ActivitDao.class);
    private ActivitRemarkDao activitRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivitRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
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

    @Override
    public Map<String, Object> getData(String id) {
        //获取uList
        List<User> uList = userDao.getUserList();
        //获取a
        Activit a = activitDao.getDataById(id);
        //封装到map
        HashMap<String, Object> map = new HashMap<>();
        map.put("uList",uList);
        map.put("a",a);
        //返回map
        return map;
    }

    @Override
    public Boolean updata(Activit activit) {
        boolean flag = true;
        //执行更新sql语句返回int
        int count = activitDao.updata(activit);
        //判断是否成功返回true,或者false；
        if(count != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public Activit detail(String id) {
        Activit a = activitDao.detail(id);
        return a;
    }

    @Override
    public List<ActivitRemark> showRemarkList(String id) {
        List<ActivitRemark> a = activitRemarkDao.showRemarkList(id);
        return a;
    }

    @Override
    public boolean deleteRemark(String id) {
        boolean success = true;
        int i = activitRemarkDao.deleteRemark(id);
        System.out.println(i);
        if(i!=1){
            success = false;
        }
        return success;
    }

    @Override
    public boolean saveRemark(ActivitRemark activitRemark) {
        boolean flag = true;
        int i = activitRemarkDao.saveRemark(activitRemark);
        if(i!=1){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean updateRemark(ActivitRemark ar) {
        boolean flag = true;
        int i = activitRemarkDao.updateRemark(ar);
        if (i!=1){
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Activit> getActivityListByClueId(String clueId) {
        List<Activit> activitList = activitDao.getActivityListByClueId(clueId);
        return activitList;
    }

    @Override
    public List<Activit> getUserListbyClue(HashMap<String, String> map) {
        List<Activit> activitList = activitDao.getUserListbyClue(map);
        return activitList;
    }
}
