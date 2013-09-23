package com.jqy.server.core.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class ServerProtocolDecoder extends ProtocolDecoderAdapter {

  @SuppressWarnings("unused")
  private Charset charset;

  public ServerProtocolDecoder(Charset charset) {
    this.charset=charset;
  }

  public void decode(IoSession arg0, IoBuffer arg1, ProtocolDecoderOutput arg2) throws Exception {
  }
}
