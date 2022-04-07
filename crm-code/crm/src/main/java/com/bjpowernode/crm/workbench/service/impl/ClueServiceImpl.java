package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.Utils.SqlSessionUtil;
import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.vo.PageListVo;
import com.bjpowernode.crm.workbench.dao.ActivitDao;
import com.bjpowernode.crm.workbench.dao.ActivitRemarkDao;
import com.bjpowernode.crm.workbench.dao.ClueActivityRelationDao;
import com.bjpowernode.crm.workbench.dao.ClueDao;
import com.bjpowernode.crm.workbench.domain.Activit;
import com.bjpowernode.crm.workbench.domain.ActivitRemark;
import com.bjpowernode.crm.workbench.domain.Clue;
import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;
import com.bjpowernode.crm.workbench.service.ActivitService;
import com.bjpowernode.crm.workbench.service.ClueService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClueServiceImpl implements ClueService {
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private ClueActivityRelationDao clueActivityRelation = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);
    @Override
    public boolean save(Clue clue) {
        boolean flag = true;
        int i = clueDao.save(clue);
        if(i != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public Clue detail(String id) {
        Clue clue = clueDao.detail(id);
        return clue;
    }

    @Override
    public boolean unbund(String id) {
        int i = clueActivityRelation.unbund(id);
        boolean flag = true;
        if(i != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean bund(String aid, String[] cids) {

        for(String s:cids){

        }
        return false;
    }
}

