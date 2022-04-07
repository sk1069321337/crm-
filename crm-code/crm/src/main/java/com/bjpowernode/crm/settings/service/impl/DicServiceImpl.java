package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.Utils.SqlSessionUtil;
import com.bjpowernode.crm.settings.dao.DicTypeDao;
import com.bjpowernode.crm.settings.dao.DicValueDao;
import com.bjpowernode.crm.settings.domain.DicType;
import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.service.DicService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicServiceImpl implements DicService {
    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

    @Override
    public Map<String, List<DicValue>> getClue() {
        //首先获取所有的dicType的code
        HashMap<String, List<DicValue>> map1 = new HashMap<>();
        List<DicType> codeList = dicTypeDao.getCode();
        for(DicType cl:codeList){
            String code = cl.getCode();
            //根据每次获取的code来获取values;
            List<DicValue> ValueList = dicValueDao.getValueList(code);
            map1.put(code,ValueList);
        }
        return map1;
    }
}
