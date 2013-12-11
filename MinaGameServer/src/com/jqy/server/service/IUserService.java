package com.jqy.server.service;

import com.jqy.server.entity.user.User;

public interface IUserService {

  public User login(String username, String password);

  public boolean create(User user);

  public User selectByUsername(String username);

  public void save(User user);

  public User selectById(int id);
}
