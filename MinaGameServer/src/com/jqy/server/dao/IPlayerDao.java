package com.jqy.server.dao;

import java.util.List;

import com.jqy.server.entity.player.Player;

/**
 * UserDao
 * 
 * @author Simple
 * @date 2013-11-5 下午04:58:31
 * @Description TODO
 */
public interface IPlayerDao {

  public int register(Player player);

  public Player selectByNickName(String nickName);

  public List<Player> selectAll();
}
