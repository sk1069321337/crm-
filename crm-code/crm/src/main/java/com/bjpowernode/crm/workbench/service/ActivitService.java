package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.vo.PageListVo;
import com.bjpowernode.crm.workbench.domain.Activit;

import java.util.Map;

public interface ActivitService {
    Boolean save(Activit activit);

    PageListVo<Activit> pageList(Map<String, Object> map);

    boolean delete(String[] ids);
}
