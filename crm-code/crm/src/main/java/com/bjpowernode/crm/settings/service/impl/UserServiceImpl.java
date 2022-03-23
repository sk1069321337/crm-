package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.Utils.DateTimeUtil;
import com.bjpowernode.crm.Utils.SqlSessionUtil;
import com.bjpowernode.crm.Exception.LoginException;
import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String username, String pwd, String ip) throws LoginException {
        Map<String,String> map = new HashMap<String,String>();
        map.put("username",username);
        map.put("pwd",pwd);
        User user = userDao.login(map);
        if(user == null){
            throw new LoginException("账户密码错误,请重新输入");
        }
        if(!(user.getAllowIps().contains(ip))){
            System.out.println(user.getAllowIps());
            throw new LoginException("IP地址无效，请联系管理员");
        }
        if("0"== user.getLockState()){
            throw new LoginException("账户已锁定，请联系管理员");
        }
        String nowTime = DateTimeUtil.getSysTime();
        String expireTime = user.getExpireTime();
        if(expireTime.compareTo(nowTime) < 0){
            throw new LoginException("权限已经超时，请联系管理员");
        }
        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.getUserList();
        return userList;
    }

}
