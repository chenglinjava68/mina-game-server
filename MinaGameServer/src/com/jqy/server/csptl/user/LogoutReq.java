package com.jqy.server.csptl.user;

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
 * 退出 请求协议
 * 
 * 此类协议都要使用原型模式
 * 
 * @author Simple
 * @date 2013-9-27 上午11:01:24
 * @Description TODO
 */
@Component
@Scope("prototype")
public class LogoutReq extends AbsReqProtocol {

  private Logger log=Logger.getLogger(this.getClass());

  private static final byte TYPE=Constant.REQ;

  private static final short ID=0x0009;

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
  public void decode(JSONObject data) {
    // 数据解码
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    User user=(User)session.getAttribute(Constant.USER);
    String username=user.getUsername();
    log.debug(String.format("%s logout!", username));
    onlineService.removeConnectedUser(user);
    Player p=onlineService.getPlayerByIoSession(session);
    if(null != p) {
      onlineService.removeOnlinePlayer(p);
    }
    return new LogoutResp(Constant.SUCCESS);
  }
}
