package com.jqy.server.core.codec;

import java.nio.charset.Charset;

import net.sf.json.JSONObject;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 编码器
 * 
 * @author Simple
 * @date 2013-9-26 上午11:26:25
 * @Description TODO
 */
public class ServerProtocolEncoder extends ProtocolEncoderAdapter {

  private Charset charset;

  public ServerProtocolEncoder(Charset charset) {
    this.setCharset(charset);
  }

  public void encode(IoSession session, Object obj, ProtocolEncoderOutput out) throws Exception {
    JSONObject jsonObject=(JSONObject)obj;
    byte[] data=jsonObject.toString().getBytes();
    IoBuffer buf=IoBuffer.allocate(2 + data.length);
    buf.putShort((short)data.length);
    buf.put(data);
    buf.flip();
    out.write(buf);
  }

  public void setCharset(Charset charset) {
    this.charset = charset;
  }

  public Charset getCharset() {
    return charset;
  }
}
