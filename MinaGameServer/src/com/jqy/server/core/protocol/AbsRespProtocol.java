package com.jqy.server.core.protocol;

import org.apache.mina.core.buffer.IoBuffer;

public abstract class AbsRespProtocol implements IProtocol {

  public short getProtocolId() {
    return 0;
  }

  public byte getProtocolType() {
    return 0;
  }

  public abstract void encode(IoBuffer buf);
}
