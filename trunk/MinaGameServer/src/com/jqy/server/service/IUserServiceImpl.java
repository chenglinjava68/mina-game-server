package com.jqy.server.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jqy.server.dao.UserDao;
import com.jqy.server.entity.User;

@Service
public class IUserServiceImpl implements IUserService {

  @Resource
  private UserDao userDao;

  public User login(String username, String password) {
    return null;
  }

  public boolean register(User user) {
    int status=userDao.add("Mapper_User.add", user);
    return status > 0 ? true : false;
  }
}
