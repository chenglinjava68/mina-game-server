package com.jqy.server.csptl.chat;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsReqProtocol;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.player.Player;
import com.jqy.server.service.IOnlineService;

/**
 * 在线玩家 请求协议
 * 
 * 此类协议都要使用原型模式
 * 
 * @author Simple
 * @date 2013-9-27 上午11:01:24
 * @Description TODO
 */
@Component
@Scope("prototype")
public class OnlinePlayerListReq extends AbsReqProtocol {

  private Logger log=Logger.getLogger(this.getClass());

  private static final byte TYPE=Constant.REQ;

  private static final short ID=0x0019;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  @Resource
  private IOnlineService onlineService;

  @Override
  public void decode(MyBuffer buf) {
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    log.debug(String.format("onlinePlayerList execute"));
    Map<IoSession, Player> players=onlineService.getOnlinePlayers();
    if(players.size() > 0) {
      return new OnlinePlayerListResp(Constant.SUCCESS, players);
    } else {
      return new OnlinePlayerListResp(Constant.FAILD, null);
    }
  }
}
