package com.jqy.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jqy.server.dao.IUserDao;
import com.jqy.server.entity.user.User;
import com.jqy.server.service.IUserService;

@Service
public class IUserServiceImpl implements IUserService {

  @Resource
  private IUserDao userDao;

  @Override
  public boolean login(String username, String password) {
    User user=userDao.login(username, password);
    return user != null ? true : false;
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
