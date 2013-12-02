package com.jqy.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.jqy.server.dao.IPlayerDao;
import com.jqy.server.dao.base.BaseDao;
import com.jqy.server.entity.player.Player;

@Repository
public class IPlayerDaoImpl extends BaseDao implements IPlayerDao {

  @Override
  public int register(Player player) {
    return create("Mapper.Player", player);
  }
}
