package com.jqy.client;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 解码器
 * 
 * @author Simple
 * @date 2013-9-26 上午11:26:18
 * @Description TODO
 */
public class ClientProtocolDecoder extends ProtocolDecoderAdapter {

  // private Logger log=Logger.getLogger(this.getClass());
  private Charset charset;

  public ClientProtocolDecoder(Charset charset) {
    this.setCharset(charset);
  }

  public void decode(IoSession session, IoBuffer buffer, ProtocolDecoderOutput out) throws Exception {
    if(buffer.remaining() > 2) {// 有报头
      buffer.mark();
      short bodyLength=buffer.getShort();// 取报头
      if(buffer.remaining() >= bodyLength) {// 有报体
      // log.debug("报体完整,准备解析");
        // 取出报体数据
        byte[] bodyData=new byte[bodyLength];
        buffer.get(bodyData);
        // 封装报体数据
        IoBuffer bodyBuffer=IoBuffer.allocate(bodyLength);
        bodyBuffer.put(bodyData);
        bodyBuffer.flip();
        // 发送至Handler处理
        out.write(bodyBuffer);
      } else {
        buffer.reset();
        // log.debug("报体不完整,继续接收");
      }
    } else {
      // log.debug("协议数据不完整");
    }
  }

  public void setCharset(Charset charset) {
    this.charset=charset;
  }

  public Charset getCharset() {
    return charset;
  }
}
