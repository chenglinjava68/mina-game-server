package com.jqy.server.csptl.player;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsRespProtocol;

public class HeartbeatResp extends AbsRespProtocol {

  private static final byte TYPE=Constant.RESP;

  private static final short ID=0x1002;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  private byte result;

  public HeartbeatResp() {
  }

  public HeartbeatResp(byte result) {
    this.result=result;
  }

  @Override
  public void encode(MyBuffer buf) {
    buf.put(result);
  }
}
