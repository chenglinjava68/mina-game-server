package com.jqy.server.service;

import com.jqy.server.entity.User;

public interface IUserService {

  /**
   * 登陆
   * @param username
   * @param password
   * @return
   */
  public boolean login(String username, String password);

  /**
   * 注册
   * @param user
   * @return
   */
  public boolean register(User user);
}
