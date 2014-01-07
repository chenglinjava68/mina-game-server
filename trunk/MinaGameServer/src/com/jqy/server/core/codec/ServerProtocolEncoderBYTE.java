package com.jqy.server.core.codec;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.ServerConfig;

/**
 * 编码器 BYTE模式
 * 
 * @author Simple
 * @date 2013-9-26 上午11:26:25
 * @Description TODO
 */
public class ServerProtocolEncoderBYTE extends ProtocolEncoderAdapter {

  private Logger log=Logger.getLogger(this.getClass());

  private Charset charset;

  public ServerProtocolEncoderBYTE(Charset charset) {
    this.setCharset(charset);
  }

  public void encode(IoSession session, Object obj, ProtocolEncoderOutput out) throws Exception {
    MyBuffer myBuf=(MyBuffer)obj;
    byte type=myBuf.get();
    short id=myBuf.getShort();
    int bodyLength;
    if(myBuf.remaining() > 0) {// 有数据
      bodyLength=myBuf.limit() - 1 - 2;
    } else {
      log.debug(String.format("TYPE=%s,ID=%s的协议无响应数据", type, id));
      bodyLength=0;
    }
    IoBuffer resp=IoBuffer.allocate(ServerConfig.HEAD_LENGTH + bodyLength);
    resp.put(type);
    resp.putShort(id);
    resp.putInt(bodyLength);
    if(bodyLength != 0) {
      byte[] data=new byte[bodyLength];
      myBuf.get(data);
      resp.put(data);
    }
    resp.flip();
    out.write(resp);
  }

  public void setCharset(Charset charset) {
    this.charset=charset;
  }

  public Charset getCharset() {
    return charset;
  }
}
