package com.jqy.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jqy.server.dao.IPlayerDao;
import com.jqy.server.entity.player.Player;
import com.jqy.server.service.IPlayerService;

@Service
public class IPlayerServiceImpl implements IPlayerService {

  @Resource
  private IPlayerDao playerDao;

  @Override
  public boolean register(Player player) {
    int status=playerDao.register(player);
    return status > 0 ? true : false;
  }
}
