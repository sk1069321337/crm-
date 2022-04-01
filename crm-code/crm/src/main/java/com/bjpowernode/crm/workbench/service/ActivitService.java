package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.vo.PageListVo;
import com.bjpowernode.crm.workbench.domain.Activit;
import com.bjpowernode.crm.workbench.domain.ActivitRemark;

import java.util.List;
import java.util.Map;

public interface ActivitService {
    Boolean save(Activit activit);

    PageListVo<Activit> pageList(Map<String, Object> map);

    boolean delete(String[] ids);

    Map<String, Object> getData(String id);

    Boolean updata(Activit activit);

    Activit detail(String id);

    List<ActivitRemark> showRemarkList(String id);

    boolean deleteRemark(String id);

    boolean saveRemark(ActivitRemark activitRemark);

    boolean updateRemark(ActivitRemark ar);
}
