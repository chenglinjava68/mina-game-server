package com.jqy.server.service;

import com.jqy.server.entity.User;

public interface IUserService {

  public boolean login(String username, String password);

  public boolean register(User user);
}
