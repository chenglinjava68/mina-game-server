package com.jqy.server.csptl;

import org.apache.mina.core.buffer.IoBuffer;

import com.jqy.server.common.Common;
import com.jqy.server.core.protocol.AbsRespProtocol;

public class LoginResp extends AbsRespProtocol {

  private static final byte TYPE=Common.RESP;

  private static final short ID=0x0001;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  public LoginResp() {
  }

  @Override
  public void encode(IoBuffer buf) {
  }
}
