package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Customer;

import java.util.List;

public interface CustomerDao {

    Customer getbyCompany(String company);

    int save(Customer customer1);

    List<String> getCustomerName(String name);
}
