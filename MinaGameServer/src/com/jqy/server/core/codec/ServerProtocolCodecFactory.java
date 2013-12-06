package com.jqy.server.core.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 编码解码工厂
 * 
 * @author Simple
 * @date 2013-9-23 下午02:24:23
 * @Description TODO
 */
public class ServerProtocolCodecFactory implements ProtocolCodecFactory {

  private final ServerProtocolCumulativeDecoder serverProtocolDecoder;

  private final ServerProtocolEncoder serverProtocolEncoder;

  public ServerProtocolCodecFactory(Charset charset) {
    this.serverProtocolDecoder=new ServerProtocolCumulativeDecoder(charset);
    this.serverProtocolEncoder=new ServerProtocolEncoder(charset);
  }

  public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
    return serverProtocolDecoder;
  }

  public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
    return serverProtocolEncoder;
  }
}
