package com.jqy.server.core.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 编码器
 * @author Simple
 * @date 2013-9-26 上午11:26:25
 * @Description TODO
 */
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
