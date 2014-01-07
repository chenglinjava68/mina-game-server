package com.jqy.server.manager;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jqy.server.entity.player.Player;
import com.jqy.server.service.IOnlineService;
import com.jqy.server.service.IUserService;

/**
 * 心跳管理
 * 
 * @author Simple
 * @date 2013-12-9 下午03:54:32
 * @Description TODO
 */
@Component
public class HeartbeatManager implements Runnable {

  private Logger log=Logger.getLogger(this.getClass());

  @Resource
  private IOnlineService onlineService;

  @Resource
  private IUserService userService;

  @Override
  public void run() {
    Collection<Player> list=onlineService.getOnlinePlayers().values();
    for(Player p: list) {
      p.heartbeat();// 心跳
      checkPlayerIsAlive(p);
    }
  }

  /**
   * 检测用户是否活着
   * 
   * @param p
   */
  private void checkPlayerIsAlive(Player p) {
    if(p.get_idleTime() > 1200) {// 空闲
      onlineService.removeOnlinePlayer(p);// 超出空闲时间，从在线列表移除玩家
      log.debug(String.format("玩家{%s}超出空闲时间,从在线列表移除!", p));
      userService.save(p.getUser());
      log.debug("保存用户信息");
    }
  }
}
