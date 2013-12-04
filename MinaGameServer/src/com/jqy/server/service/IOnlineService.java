package com.jqy.server.service;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.jqy.server.entity.user.User;

public interface IOnlineService {

  /**
   * 获取在线用户列表
   * 
   * @return
   */
  public Map<String, User> getOnlineUsers();

  /**
   * 获取已连接用户列表
   * 
   * @return
   */
  public Map<String, IoSession> getConnecteds();

  /***
   * 添加已连接用户
   * 
   * @param username
   * @param session
   * @return
   */
  public boolean setConnected(String username, IoSession session);

  /**
   * 添加在线用户
   * 
   * @param username
   * @param user
   * @return
   */
  public boolean setOnline(String username, User user);

  /**
   * 移除连接用户
   * @param username
   */
  public void removeConnected(String username);

  /**
   * 移除在线用户
   * @param username
   */
  public void removeOnline(String username);
}
