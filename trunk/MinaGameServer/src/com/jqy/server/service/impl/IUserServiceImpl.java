package com.jqy.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jqy.server.dao.IUserDao;
import com.jqy.server.entity.user.User;
import com.jqy.server.service.IUserService;

@Service
public class IUserServiceImpl implements IUserService {

  private Map<String, User> onlineUsers=new HashMap<String, User>();

  @Resource
  private IUserDao userDao;

  @Override
  public User login(String username, String password) {
    User user=userDao.login(username, password);
    if(null != user) {
      onlineUsers.put(user.getUsername(), user);
    }
    return user;
  }

  @Override
  public boolean register(User user) {
    int status=userDao.register(user);
    return status > 0 ? true : false;
  }

  @Override
  public User selectByUsername(String username) {
    User user=userDao.selectByUsername(username);
    return user;
  }
}
