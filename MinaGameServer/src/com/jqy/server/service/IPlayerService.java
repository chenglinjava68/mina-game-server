package com.jqy.server.service;

import com.jqy.server.entity.player.Player;

public interface IPlayerService {

  /**
   * 注册
   * 
   * @param player
   * @return
   */
  public boolean register(Player player);
}
