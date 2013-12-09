package com.jqy.server.csptl.player;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jqy.server.common.Constant;
import com.jqy.server.core.protocol.AbsReqProtocol;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.player.Player;
import com.jqy.server.entity.user.User;
import com.jqy.server.service.IOnlineService;

/**
 * 开始游戏 请求协议
 * 
 * 此类协议都要使用原型模式
 * 
 * @author Simple
 * @date 2013-9-27 上午11:01:24
 * @Description TODO
 */
@Component
@Scope("prototype")
public class StartReq extends AbsReqProtocol {

  private Logger log=Logger.getLogger(this.getClass());

  private static final byte TYPE=Constant.REQ;

  private static final short ID=0x0011;

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

  private int roleIndex;

  @Override
  public void decode(JSONObject data) {
    roleIndex=data.getInt("roleIndex");
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    log.debug(String.format("start execute"));
    User u=(User)session.getAttribute(Constant.USER);
    List<Player> players=u.getPlayers();
    if(players.size() > 10) {
      Player player=players.get(roleIndex);
      if(null != player) {
        session.setAttribute(Constant.PLAYER, player);
        onlineService.setOnlinePlayer(session, player);
        return new StartResp(Constant.SUCCESS);
      }
    }
    return new StartResp(Constant.FAILD);
  }
}
