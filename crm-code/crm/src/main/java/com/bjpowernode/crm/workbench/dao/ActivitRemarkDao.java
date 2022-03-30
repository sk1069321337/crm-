package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.ActivitRemark;

import java.util.List;

public interface ActivitRemarkDao {
    int selectcount(String[] ids);
    int deletecount(String[] ids);

    List<ActivitRemark> showRemarkList(String id);
}
