package com.jqy.server.core.protocol;

import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public abstract class AbsReqProtocol implements IProtocol {

  public short getProtocolId() {
    return 0;
  }

  public byte getProtocolType() {
    return 0;
  }

  public abstract void decode(JSONObject data);

  public abstract AbsRespProtocol execute(IoSession session, AbsReqProtocol req);
}
