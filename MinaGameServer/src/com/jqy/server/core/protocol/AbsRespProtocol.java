package com.jqy.server.core.protocol;

import net.sf.json.JSONObject;

public abstract class AbsRespProtocol implements IProtocol {

  public short getProtocolId() {
    return 0;
  }

  public byte getProtocolType() {
    return 0;
  }

  public abstract void encode(JSONObject data);
}
