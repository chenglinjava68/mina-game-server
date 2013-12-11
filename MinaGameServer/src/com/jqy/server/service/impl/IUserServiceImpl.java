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
  public User login(String username, String password) {
    return userDao.login(username, password);
  }

  @Override
  public boolean create(User user) {
    int status=userDao.create(user);
    return status > 0 ? true : false;
  }

  @Override
  public User selectByUsername(String username) {
    User user=userDao.selectByUsername(username);
    return user;
  }

  @Override
  public void save(User user) {
    userDao.update(user);
  }

  @Override
  public User selectById(int id) {
    // TODO Auto-generated method stub
    return userDao.selectById(id);
  }
}
