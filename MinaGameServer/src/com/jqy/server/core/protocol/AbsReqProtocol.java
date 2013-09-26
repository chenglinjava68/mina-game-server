package com.jqy.server.core.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public abstract class AbsReqProtocol implements IProtocol {

  public short getProtocolId() {
    // TODO Auto-generated method stub
    return 0;
  }

  public byte getProtocolType() {
    // TODO Auto-generated method stub
    return 0;
  }

  public abstract void decode(IoBuffer buf);

  public abstract AbsRespProtocol execute(IoSession session, AbsReqProtocol req);
}
