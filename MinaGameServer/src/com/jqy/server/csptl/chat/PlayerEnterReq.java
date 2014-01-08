package com.jqy.server.csptl.chat;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsReqProtocol;
import com.jqy.server.core.protocol.AbsRespProtocol;

/**
 * 玩家登入 请求协议
 * 
 * 此类协议都要使用原型模式
 * 
 * @author Simple
 * @date 2013-9-27 上午11:01:24
 * @Description TODO
 */
@Component
@Scope("prototype")
public class PlayerEnterReq extends AbsReqProtocol {

  private Logger log=Logger.getLogger(this.getClass());

  private static final byte TYPE=Constant.REQ;

  private static final short ID=0x0017;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  @Override
  public void decode(MyBuffer buf) {
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    log.debug(String.format("player enter execute"));
    // ...
    // 执行业务逻辑
    // ...
    return null;
  }
}
