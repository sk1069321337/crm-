package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.Utils.SqlSessionUtil;
import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.vo.PageListVo;
import com.bjpowernode.crm.workbench.dao.ActivitDao;
import com.bjpowernode.crm.workbench.dao.ActivitRemarkDao;
import com.bjpowernode.crm.workbench.dao.ClueDao;
import com.bjpowernode.crm.workbench.domain.Activit;
import com.bjpowernode.crm.workbench.domain.ActivitRemark;
import com.bjpowernode.crm.workbench.service.ActivitService;
import com.bjpowernode.crm.workbench.service.ClueService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClueServiceImpl implements ClueService {
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);

}
