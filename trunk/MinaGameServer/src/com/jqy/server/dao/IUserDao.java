package com.jqy.server.dao;

import com.jqy.server.entity.user.User;

/**
 * UserDao
 * 
 * @author Simple
 * @date 2013-11-5 下午04:58:31
 * @Description TODO
 */
public interface IUserDao {

  /**
   * 登陆
   * 
   * @param username
   * @param password
   * @return
   */
  public User login(String username, String password);

  public User selectByUsername(String username);

  public int create(User user);
  
  public void update(User user);

  public User selectById(int id);
}
