package com.jqy.server.service.impl;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import com.jqy.server.core.MyBuffer;
import com.jqy.server.csptl.chat.ChatResp;
import com.jqy.server.csptl.chat.PlayerEnterResp;
import com.jqy.server.entity.player.Player;
import com.jqy.server.service.IChatService;
import com.jqy.server.service.IOnlineService;
import com.jqy.util.DateUtil;

@Service
public class IChatServiceImpl implements IChatService {

  private Logger log=Logger.getLogger(this.getClass());

  @Resource
  private IOnlineService onlineService;

  private void send2onlinePlayer(MyBuffer buf) {
    Set<IoSession> players=onlineService.getOnlinePlayers().keySet();
    buf.mark();
    for(IoSession p: players) {
      buf.reset();
      p.write(buf);
    }
  }

  private void send2onlinePlayerExceptSelf(Player me, MyBuffer buf) {
    Iterator<Entry<IoSession, Player>> it=onlineService.getOnlinePlayers().entrySet().iterator();
    buf.mark();
    while(it.hasNext()) {
      buf.reset();
      Entry<IoSession, Player> e=it.next();
      if(me != e.getValue()) {
        e.getKey().write(buf);
      }
    }
  }

  @Override
  public void sendMessage(String msg) {
    log.debug(String.format("send to common"));
    send2onlinePlayer(buf_sendMessage(msg));
  }

  private MyBuffer buf_sendMessage(String msg) {
    ChatResp cr=new ChatResp();
    short ptlId=cr.getProtocolId();
    byte type=cr.getProtocolType();
    // int length=msg.getBytes().length;
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(type);
    buf.putShort(ptlId);
    // buf.putInt(length);
    buf.putPrefixedString(DateUtil.getCurrentDate());
    buf.putPrefixedString(msg);
    buf.flip();
    return buf;
  }

  @Override
  public void playerEnter(Player p) {
    log.debug(String.format("player enter"));
    send2onlinePlayerExceptSelf(p, buf_playerEnter(p));
  }

  private MyBuffer buf_playerEnter(Player p) {
    PlayerEnterResp cr=new PlayerEnterResp();
    short ptlId=cr.getProtocolId();
    byte type=cr.getProtocolType();
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(type);
    buf.putShort(ptlId);
    buf.putInt(p.getId());
    buf.putInt(p.getLevel());
    buf.putPrefixedString(p.getNickName());
    buf.flip();
    return buf;
  }
}
