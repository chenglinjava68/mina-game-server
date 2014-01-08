package com.jqy.server.csptl.chat;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsRespProtocol;

public class PlayerEnterResp extends AbsRespProtocol {

  private static final byte TYPE=Constant.RESP;

  private static final short ID=0x0018;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  public PlayerEnterResp() {
  }

  public PlayerEnterResp(byte result) {
  }

  @Override
  public void encode(MyBuffer buf) {
  }
}
