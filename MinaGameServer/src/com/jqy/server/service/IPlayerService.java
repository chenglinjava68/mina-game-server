package com.jqy.server.service;

import java.util.List;

import com.jqy.server.entity.player.Player;

public interface IPlayerService {

  /**
   * 注册
   * 
   * @param player
   * @return
   */
  public boolean register(Player player);

  public Player selectByNickName(String nickName);

  public List<Player> selectAll();
}
