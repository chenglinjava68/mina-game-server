package com.jqy.server.csptl;

import net.sf.json.JSONObject;

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

  private int result;

  public LoginResp(int result) {
    this.result=result;
  }

  @Override
  public void encode(JSONObject data) {
    data.put("result", result);
  }
}
