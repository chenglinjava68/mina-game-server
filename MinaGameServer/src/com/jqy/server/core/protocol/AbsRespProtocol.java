package com.jqy.server.core.protocol;

import net.sf.json.JSONObject;

public abstract class AbsRespProtocol implements IProtocol {

  public short getProtocolId() {
    return 0;// 每个继承类重写该方法
  }

  public byte getProtocolType() {
    return 0;// 每个继承类重写该方法
  }

  // 编码
  public abstract void encode(JSONObject data);
}
