package com.jqy.server.csptl;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsReqProtocol;
import com.jqy.server.core.protocol.AbsRespProtocol;

/**
 * XX 请求协议，供Copy使用
 * 
 * 此类协议都要使用原型模式
 * 
 * @author Simple
 * @date 2013-9-27 上午11:01:24
 * @Description TODO
 */
@Component
@Scope("prototype")
public class DemoReq extends AbsReqProtocol {

  private Logger log=Logger.getLogger(this.getClass());

  private static final byte TYPE=Constant.REQ;

  private static final short ID=0x0098;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  @SuppressWarnings("unused")
  private byte param1;

  @SuppressWarnings("unused")
  private short param2;

  @SuppressWarnings("unused")
  private int param3;

  @SuppressWarnings("unused")
  private long param4;

  @SuppressWarnings("unused")
  private float param5;

  @SuppressWarnings("unused")
  private double param6;

  @SuppressWarnings("unused")
  private String param7;

  @Override
  public void decode(MyBuffer buf) {
    // 数据解码
    param1=buf.get();
    param2=buf.getShort();
    param3=buf.getInt();
    param4=buf.getLong();
    param5=buf.getFloat();
    param6=buf.getDouble();
    param7=buf.getPrefixedString();
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    log.debug(String.format("demo execute %s", "1"));
    // ...
    // 执行业务逻辑
    // ...
    return new DemoResp();
  }
}
