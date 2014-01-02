package com.jqy.server.core.protocol;

import com.jqy.server.core.MyBuffer;

import net.sf.json.JSONObject;

public abstract class AbsRespProtocol implements IProtocol {

  public short getProtocolId() {
    return 0;// 每个继承类重写该方法
  }

  public byte getProtocolType() {
    return 0;// 每个继承类重写该方法
  }

  // 编码
  public void encode(JSONObject data){
    // 使用JSON模式的话，此方法改为abstract
  }
  
  public abstract void encode(MyBuffer buf);
}
