package com.jqy.server.csptl.player;

import java.util.List;

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
import com.jqy.server.service.IPlayerService;

/**
 * 玩家列表 请求协议
 * 
 * 此类协议都要使用原型模式
 * 
 * @author Simple
 * @date 2013-9-27 上午11:01:24
 * @Description TODO
 */
@Component
@Scope("prototype")
public class PlayerListReq extends AbsReqProtocol {

  private Logger log=Logger.getLogger(this.getClass());

  private static final byte TYPE=Constant.REQ;

  private static final short ID=0x0007;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  @Resource
  private IPlayerService playerService;

  // @Resource
  // private IUserService userService;
  @Override
  public void decode(MyBuffer buf) {
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    log.debug(String.format("player list execute"));
    // User u=userService.selectById(1);
    // Player p=playerService.selectById(3);
    List<Player> players=playerService.selectAll();
    return new PlayerListResp(players.size() > 0 ? Constant.SUCCESS : Constant.FAILD, players);
  }

  public static void main(String[] args) {
    int day=60 * 60 * 24;
    for(int i=1; i < 10; i++) {
      System.out.println(i + "=" + day * (i * 7));
    }
  }
}
