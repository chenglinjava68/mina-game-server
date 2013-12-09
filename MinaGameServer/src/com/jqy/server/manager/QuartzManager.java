package com.jqy.server.manager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时管理
 * 
 * @author Simple
 * @date 2013-4-17 下午10:48:40
 * @Description TODO
 */
@Component
public class QuartzManager {

  private Logger log=Logger.getLogger(this.getClass());

  @Autowired
  private HeartbeatManager heartbeatManager;

  @Scheduled(cron="0/30 * * * * ?")
  public void heartbeat() {
    log.debug("心跳检测");
    heartbeatManager.run();
  }
}
