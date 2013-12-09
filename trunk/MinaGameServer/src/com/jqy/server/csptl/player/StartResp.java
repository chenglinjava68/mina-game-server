package com.jqy.server.csptl.player;

import net.sf.json.JSONObject;

import com.jqy.server.common.Constant;
import com.jqy.server.core.protocol.AbsRespProtocol;

public class StartResp extends AbsRespProtocol {

  private static final byte TYPE=Constant.RESP;

  private static final short ID=0x0012;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  private int result;

  public StartResp() {
  }

  public StartResp(int result) {
    this.result=result;
  }

  @Override
  public void encode(JSONObject data) {
    data.put("result", result);
  }
}
