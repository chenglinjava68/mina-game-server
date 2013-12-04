package com.jqy.server.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import com.jqy.server.entity.user.User;
import com.jqy.server.service.IOnlineService;

/**
 * 在线用户/连接 管理
 * @author Simple
 * @date 2013-12-4 下午03:28:10
 * @Description TODO
 */
@Service
public class IOnlineServiceImpl implements IOnlineService {

  /**
   * 已连接用户
   */
  private static Map<String, IoSession> connecteds=new ConcurrentHashMap<String, IoSession>();

  /**
   * 在线用户
   */
  private static Map<String, User> onlineUsers=new ConcurrentHashMap<String, User>();

  @Override
  public Map<String, IoSession> getConnecteds() {
    return connecteds;
  }

  @Override
  public Map<String, User> getOnlineUsers() {
    return onlineUsers;
  }

  @Override
  public boolean setOnline(String username, User user) {
    if(!onlineUsers.containsKey(username)) {
      onlineUsers.put(username, user);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean setConnected(String username, IoSession session) {
    if(!connecteds.containsKey(username)) {
      connecteds.put(username, session);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void removeConnected(String username) {
    if(connecteds.containsKey(username)) {
      connecteds.remove(username);
    }
  }

  @Override
  public void removeOnline(String username) {
    if(onlineUsers.containsKey(username)) {
      onlineUsers.remove(username);
    }
  }
}
