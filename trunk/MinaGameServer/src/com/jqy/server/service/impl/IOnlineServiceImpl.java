package com.jqy.server.service.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

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
  private static Map<User, IoSession> connecteds_user_session=new ConcurrentHashMap<User, IoSession>();

  /**
   * 在线玩家
   */
  private static Queue<Player> onlinePlayers=new ConcurrentLinkedQueue<Player>();

  /**
   * 在线玩家(IoSession-Player)
   */
  private static Map<IoSession, Player> onlinePlayers_session_player=new ConcurrentHashMap<IoSession, Player>();

  @Override
  public Map<User, IoSession> getConnectedUsers() {
    return connecteds_user_session;
  }

  @Override
  public IoSession getIoSessionByUser(User user) {
    if(connecteds_user_session.containsKey(user))
      return connecteds_user_session.get(user);
    return null;
  }

  @Override
  public Queue<Player> getOnlinePlayers() {
    return onlinePlayers;
  }

  @Override
  public Player getPlayerByIoSession(IoSession session) {
    if(onlinePlayers_session_player.containsKey(session))
      return onlinePlayers_session_player.get(session);
    return null;
  }

  @Override
  public void removeConnectedUser(User user) {
    if(connecteds_user_session.containsKey(user))
      connecteds_user_session.remove(user);
  }

  @Override
  public void removeOnlinePlayer(Player player) {
    if(onlinePlayers.contains(player))
      onlinePlayers.remove(player);
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
  public void setConnectedUser(User user, IoSession session) {
    if(!connecteds_user_session.containsKey(user)) {
      connecteds_user_session.put(user, session);
    }
  }

  @Override
  public void setOnlinePlayer(IoSession session, Player player) {
    if(!onlinePlayers_session_player.containsKey(session)) {
      onlinePlayers_session_player.put(session, player);
    }
    if(!onlinePlayers.contains(player)) {
      onlinePlayers.add(player);
    }
  }
}
