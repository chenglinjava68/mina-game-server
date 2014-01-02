package com.jqy.server.csptl.user;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsRespProtocol;

public class LoginResp extends AbsRespProtocol {

  private static final byte TYPE=Constant.RESP;

  private static final short ID=0x0004;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  private byte result;

  public LoginResp(byte result) {
    this.result=result;
  }

  @Override
  public void encode(MyBuffer buf) {
    buf.put(result);
  }
}
