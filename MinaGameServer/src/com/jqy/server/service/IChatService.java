package com.jqy.server.service;

import com.jqy.server.entity.player.Player;

public interface IChatService {

  public void sendMessage(String msg);

  public void playerEnter(Player p);
}
