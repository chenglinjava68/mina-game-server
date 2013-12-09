package com.jqy.server.service;

import java.util.Map;
import java.util.Queue;

import org.apache.mina.core.session.IoSession;

import com.jqy.server.entity.player.Player;
import com.jqy.server.entity.user.User;

public interface IOnlineService {

  public Queue<Player> getOnlinePlayers();

  public void setOnlinePlayer(IoSession session, Player player);

  public void removeOnlinePlayer(Player player);

  public Map<User, IoSession> getConnectedUsers();

  public void setConnectedUser(User user, IoSession session);

  public void removeConnectedUser(User user);

  public IoSession getIoSessionByUser(User user);

  public Player getPlayerByIoSession(IoSession session);
}
