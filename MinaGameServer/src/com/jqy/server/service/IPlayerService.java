package com.jqy.server.service;

import java.util.List;

import com.jqy.server.entity.player.Player;

public interface IPlayerService {

  public boolean create(Player player);

  public Player selectByNickName(String nickName);

  public List<Player> selectAll();

  public Player selectById(int id);
}
