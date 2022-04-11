package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.Utils.SqlSessionUtil;
import com.bjpowernode.crm.workbench.dao.CustomerDao;
import com.bjpowernode.crm.workbench.dao.CustomerRemarkDao;
import com.bjpowernode.crm.workbench.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    private CustomerRemarkDao customerRemarkDao = SqlSessionUtil.getSqlSession().getMapper(CustomerRemarkDao.class);

    @Override
    public List<String> getCustomerName(String name) {
        List<String> stringList = customerDao.getCustomerName(name);
        return stringList;
    }
}
