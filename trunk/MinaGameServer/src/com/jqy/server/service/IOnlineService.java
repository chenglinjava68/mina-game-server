package com.jqy.server.service;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.jqy.server.entity.player.Player;
import com.jqy.server.entity.user.User;

public interface IOnlineService {

  public Map<IoSession, Player> getOnlinePlayers();

  public void setOnlinePlayer(IoSession session, Player player);

  public void removeOnlinePlayer(Player player);

  public Player getPlayerByIoSession(IoSession session);

  public Map<IoSession, User> getConnectedUsers();

  public void setConnectedUser(IoSession session, User user);

  public void removeConnectedUser(User user);

  public User getUserByIoSession(IoSession session);
}
