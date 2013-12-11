package com.jqy.server.service.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import com.jqy.server.entity.player.Player;
import com.jqy.server.entity.user.User;
import com.jqy.server.service.IOnlineService;

/**
 * 在线管理
 * 
 * @author Simple
 * @date 2013-12-4 下午03:28:10
 * @Description TODO
 */
@Service
public class IOnlineServiceImpl implements IOnlineService {

  /**
   * 已连接用户
   */
  private static Map<IoSession, User> connecteds_session_user=new ConcurrentHashMap<IoSession, User>();

  /**
   * 在线玩家(IoSession-Player)
   */
  private static Map<IoSession, Player> onlinePlayers_session_player=new ConcurrentHashMap<IoSession, Player>();

  @Override
  public Map<IoSession, User> getConnectedUsers() {
    return connecteds_session_user;
  }

  @Override
  public Map<IoSession, Player> getOnlinePlayers() {
    return onlinePlayers_session_player;
  }

  @Override
  public Player getPlayerByIoSession(IoSession session) {
    if(onlinePlayers_session_player.containsKey(session))
      return onlinePlayers_session_player.get(session);
    return null;
  }

  @Override
  public User getUserByIoSession(IoSession session) {
    if(connecteds_session_user.containsKey(session))
      return connecteds_session_user.get(session);
    return null;
  }

  @Override
  public void removeConnectedUser(User user) {
    if(connecteds_session_user.containsValue(user)) {
      Iterator<Entry<IoSession, User>> it=connecteds_session_user.entrySet().iterator();
      while(it.hasNext()) {
        Entry<IoSession, User> e=it.next();
        if(e.getValue() == user) {
          it.remove();
          e.getKey().close(true);// 关闭session
        }
      }
    }
  }

  @Override
  public void removeOnlinePlayer(Player player) {
    if(onlinePlayers_session_player.containsValue(player)) {
      Iterator<Entry<IoSession, Player>> it=onlinePlayers_session_player.entrySet().iterator();
      while(it.hasNext()) {
        Entry<IoSession, Player> e=it.next();
        if(e.getValue() == player) {
          it.remove();
          e.getKey().close(true);// 关闭session
        }
      }
    }
  }

  @Override
  public void setConnectedUser(IoSession session, User user) {
    if(!connecteds_session_user.containsKey(session)) {
      connecteds_session_user.put(session, user);
    }
  }

  @Override
  public void setOnlinePlayer(IoSession session, Player player) {
    if(!onlinePlayers_session_player.containsKey(session)) {
      onlinePlayers_session_player.put(session, player);
    }
  }
}
