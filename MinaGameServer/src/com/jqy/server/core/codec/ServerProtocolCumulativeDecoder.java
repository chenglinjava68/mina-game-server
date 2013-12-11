package com.jqy.server.core.codec;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 服务器协议 累积型解码器
 * 
 * 主要解决 粘包，断包问题
 * 
 * @author Simple
 * @date 2013-12-6 上午10:40:05
 * @Description TODO
 */
public class ServerProtocolCumulativeDecoder extends CumulativeProtocolDecoder {

  private Logger log=Logger.getLogger(this.getClass());

  private Charset charset;

  public ServerProtocolCumulativeDecoder(Charset charset) {
    this.charset=charset;
  }

  /**
   * 这个方法的返回值是重点：
   * 
   * 1、当内容刚好时，返回false，告知父类接收下一批内容
   * 2、内容不够时需要下一批发过来的内容，此时返回false，这样父类CumulativeProtocolDecoder会将内容放进IoSession中，等下次来数据后就自动拼装再交给本类的doDecode
   * 3、当内容多时，返回true，因为需要再将本批数据进行读取，父类会将剩余的数据再次推送本类的doDecode
   */
  @Override
  protected boolean doDecode(IoSession session, IoBuffer buf, ProtocolDecoderOutput out) throws Exception {
    if(buf.remaining() > 2) {// 有数据，先读取报头
      buf.mark();// 做标记，以便数据不完整时恢复
      short bodyLength=buf.getShort();// 取报头(报体长度)
      if(buf.remaining() < bodyLength) {// 不完整的数据包(报体长度不完整,需拼凑完整数据)
        log.debug(String.format("不完整的数据包(报体长度不够),继续接收"));
        buf.reset();// 恢复
        return false;// 接收下一批数据
      } else {// 完整的数据包
        log.debug(String.format("解析完整的数据包"));
        // 取报体内容
        byte[] bodyData=new byte[bodyLength];
        buf.get(bodyData);
        // 封装报体数据
        IoBuffer bodyBuffer=IoBuffer.allocate(bodyLength);
        bodyBuffer.put(bodyData);
        bodyBuffer.flip();
        // 发送至Handler处理
        out.write(bodyBuffer);
        if(buf.remaining() > 0) {// 解析完一个包后发现粘包了，再进行下一次解析
          log.debug("粘包,继续处理");
          return true;
        }
      }
    }
    return false;// 处理成功，接收下个包
  }

  public void setCharset(Charset charset) {
    this.charset=charset;
  }

  public Charset getCharset() {
    return charset;
  }
}
/*
 * return true; 首先判断你是否在 doDecode()方法中从内部的 IoBuffer 缓冲区读取了数据， 如果没有，则会抛出非法的状态异常， 也就是你的 doDecode()方法返回 true 就表示你已经消费了本次数据
 * （相当于聊天室中一个完整的消息已经读取完毕），进一步说，也就是此时你必须已经消费过内部的 IoBuffer 缓冲区的数据（哪怕是消费了一个字节的数据）。
 * 如果验证过通过，那么 CumulativeProtocolDecoder 会检查缓冲区内是否还有数据未读取，如果有就继续调用 doDecode()方法，没有就停止对 doDecode()方法的调用，直到有新的数据被缓冲
 */
/*
 * return false; 会停止对doDecode()的调用，如果还有剩余数据则将数据缓存到session中以便下次提取使用
 */
