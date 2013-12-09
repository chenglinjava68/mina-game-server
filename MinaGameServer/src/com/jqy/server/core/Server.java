package com.jqy.server.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.jqy.server.core.codec.ServerProtocolCodecFactory;
import com.jqy.server.core.handler.ServerHandler;

/**
 * 服务器
 * 
 * @author Simple
 * @date 2013-9-23 下午02:16:46
 * @Description TODO
 */
@Service
public class Server implements InitializingBean {

  private Logger log=Logger.getLogger(this.getClass());

  /**
   * 端口：9999
   */
  private static final int PORT=9999;

  /**
   * 读冲区大小
   */
  private static final int READ_BUFFER_SIZE=2048;

  /**
   * 接收冲区大小
   */
  private static final int RECEIVE_BUFFER_SIZE=2048;

  // /**
  // * 空闲时间：30s
  // */
  // private static final int IDEL_TIMEOUT=60;
  private NioSocketAcceptor acceptor;

  @Resource
  private ServerHandler serverHandler;

  private void start() {
    acceptor=new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
    // 配置
    SocketSessionConfig ssc=acceptor.getSessionConfig();
    // 地址复用
    ssc.setReuseAddress(true);
    // 缓冲区大小
    ssc.setReadBufferSize(READ_BUFFER_SIZE);
    ssc.setReceiveBufferSize(RECEIVE_BUFFER_SIZE);
    // // 空闲时间(IDEL_TIMEOUT秒内不发生读写操作将置为空闲)
    // ssc.setIdleTime(IdleStatus.BOTH_IDLE, IDEL_TIMEOUT);
    // 过滤器
    DefaultIoFilterChainBuilder filterChain=acceptor.getFilterChain();
    filterChain.addLast("codecFilter", new ProtocolCodecFilter(new ServerProtocolCodecFactory(Charset.forName("utf-8"))));
    acceptor.setHandler(serverHandler);
    try {
      acceptor.bind(new InetSocketAddress(PORT));
      log.debug("server started,port=" + PORT);
    } catch(UnknownHostException e) {
      e.printStackTrace();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public void afterPropertiesSet() throws Exception {
    start();
  }
}
