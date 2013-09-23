package com.jqy.server.core.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ServerProtocolEncoder extends ProtocolEncoderAdapter {

  @SuppressWarnings("unused")
  private Charset charset;

  public ServerProtocolEncoder(Charset charset) {
    this.charset=charset;
  }

  public void encode(IoSession arg0, Object arg1, ProtocolEncoderOutput arg2) throws Exception {
    // TODO Auto-generated method stub
  }
}
