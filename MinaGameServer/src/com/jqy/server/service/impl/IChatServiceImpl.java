package com.jqy.server.service.impl;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import com.jqy.server.core.MyBuffer;
import com.jqy.server.csptl.chat.ChatResp;
import com.jqy.server.entity.player.Player;
import com.jqy.server.service.IChatService;
import com.jqy.server.service.IOnlineService;

@Service
public class IChatServiceImpl implements IChatService {

  private Logger log=Logger.getLogger(this.getClass());

  @Resource
  private IOnlineService onlineService;

  @Override
  public void send2common(String msg) {
    log.debug(String.format("send to common"));
    Map<IoSession, Player> onlinePlayers=onlineService.getOnlinePlayers();
    Set<IoSession> players=onlinePlayers.keySet();
    for(IoSession p: players) {
      MyBuffer buf=getMsg(msg);
      p.write(buf);
    }
  }

  private MyBuffer getMsg(String msg) {
    ChatResp cr=new ChatResp();
    short ptlId=cr.getProtocolId();
    byte type=cr.getProtocolType();
    // int length=msg.getBytes().length;
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(type);
    buf.putShort(ptlId);
    // buf.putInt(length);
    buf.putPrefixedString(msg);
    buf.flip();
    return buf;
  }
}
