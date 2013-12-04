package com.jqy.server.service;

import com.jqy.server.entity.user.User;

public interface IUserService {

  /**
   * 登陆
   * 
   * @param username
   * @param password
   * @return
   */
  public User login(String username, String password);

  /**
   * 注册
   * 
   * @param user
   * @return
   */
  public boolean register(User user);

  /**
   * 通过用户名查询用户
   * 
   * @param username
   * @return
   */
  public User selectByUsername(String username);
}
