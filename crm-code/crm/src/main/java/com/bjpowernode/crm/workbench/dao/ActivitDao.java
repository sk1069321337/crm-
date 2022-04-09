package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Activit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ActivitDao {
    int save(Activit activit);

    int getTotalByCondiition(Map<String, Object> map);

    List<Activit> getDataListByCondiition(Map<String, Object> map);

    int delect(String[] ids);

    Activit getDataById(String id);

    int updata(Activit activit);

    Activit detail(String id);

    List<Activit> getActivityListByClueId(String clueId);

    List<Activit> getUserListbyClue(HashMap<String, String> map);

    List<Activit> getAlistByAname(String aname);
}
