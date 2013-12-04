package com.jqy.server.csptl.user;

import net.sf.json.JSONObject;

import com.jqy.server.common.Constant;
import com.jqy.server.core.protocol.AbsRespProtocol;

public class RegUserResp extends AbsRespProtocol {

  private static final byte TYPE=Constant.RESP;

  private static final short ID=0x0002;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  private int result;

  public RegUserResp(int result) {
    this.result=result;
  }

  @Override
  public void encode(JSONObject data) {
    data.put("result", result);
  }
}
