package com.jqy.server.core.protocol;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

import com.jqy.server.service.IUserService;

public abstract class AbsReqProtocol implements IProtocol {

  public short getProtocolId() {
    return 0;
  }

  public byte getProtocolType() {
    return 0;
  }

  public abstract void decode(JSONObject data);

  public abstract AbsRespProtocol execute(IoSession session, AbsReqProtocol req);

  @Resource
  protected IUserService userService;
}
