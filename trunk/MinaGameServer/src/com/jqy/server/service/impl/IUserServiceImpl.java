package com.jqy.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jqy.server.dao.impl.IUserDaoImpl;
import com.jqy.server.entity.user.User;
import com.jqy.server.service.IUserService;

@Service
public class IUserServiceImpl implements IUserService {

  @Resource
  private IUserDaoImpl userDao;

  public boolean login(String username, String password) {
    User user=userDao.login(username, password);
    return user != null ? true : false;
  }

  public boolean register(User user) {
    int status=userDao.create("Mapper_User.add", user);
    return status > 0 ? true : false;
  }
}
