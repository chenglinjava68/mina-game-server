package com.jqy.server.csptl;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jqy.server.common.Common;
import com.jqy.server.core.protocol.AbsReqProtocol;
import com.jqy.server.core.protocol.AbsRespProtocol;

@Component
@Scope("prototype")
public class LoginReq extends AbsReqProtocol {

  private static final byte TYPE=Common.REQ;

  private static final short ID=0x0001;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  @SuppressWarnings("unused")
  private String username;

  @SuppressWarnings("unused")
  private String password;

  @Override
  public void decode(IoBuffer buf) {
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    return null;
  }
}
