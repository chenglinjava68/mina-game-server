package com.jqy.server.core;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.firewall.BlacklistFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.jqy.server.core.codec.ServerProtocolCodecFactory;
import com.jqy.server.core.handler.ServerHandlerBYTE;

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

  private NioSocketAcceptor acceptor;// 监听者

  @Resource
  private ServerHandlerBYTE serverHandler;// handler

  /**
   * 启动
   */
  private void start() {
    acceptor=new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);// IoProcessor线程池个数=cpu个数+1
    setSocketSessionConfig();
    setFilter();
    acceptor.setHandler(serverHandler);
    try {
      acceptor.bind(new InetSocketAddress(ServerConfig.PORT));
      log.debug("server started,port=" + ServerConfig.PORT);
    } catch(UnknownHostException e) {
      e.printStackTrace();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 设置SocketSessionConfig
   */
  private void setSocketSessionConfig() {
    SocketSessionConfig ssc=acceptor.getSessionConfig();
    // 地址复用
    ssc.setReuseAddress(true);
    // 缓冲区大小
    ssc.setReadBufferSize(ServerConfig.READ_BUFFER_SIZE);
    ssc.setReceiveBufferSize(ServerConfig.RECEIVE_BUFFER_SIZE);
    // // 空闲时间(IDEL_TIMEOUT秒内不发生读写操作将置为空闲)
    // ssc.setIdleTime(IdleStatus.BOTH_IDLE, IDEL_TIMEOUT);
  }
  
  /**
   * 设置过滤器链
   */
  private void setFilter() {
    DefaultIoFilterChainBuilder filterChain=acceptor.getFilterChain();
    addBlackListFilter(filterChain);
    // addLoggingFilter(filterChain);
    filterChain.addLast("codecFilter", new ProtocolCodecFilter(new ServerProtocolCodecFactory(Charset.forName("utf-8"))));
  }
  
  /**
   * 黑名单过滤器
   * 
   * @param filterChain
   */
  private void addBlackListFilter(DefaultIoFilterChainBuilder filterChain) {
    BlacklistFilter blacklistFilter=new BlacklistFilter();
    InetAddress[] addresses=new InetAddress[1];
    try {
      addresses[0]=InetAddress.getByName("1.1.1.1");
      blacklistFilter.setBlacklist(addresses);
      filterChain.addFirst("blacklistFilter", blacklistFilter);
    } catch(UnknownHostException e) {
      e.printStackTrace();
    }
  }

  /**
   * 日志过滤器
   * @param filterChain
   */
  @SuppressWarnings("unused")
  private void addLoggingFilter(DefaultIoFilterChainBuilder filterChain) {
    filterChain.addLast("loggerFilter", new LoggingFilter());
  }

  public void afterPropertiesSet() throws Exception {
    start();
  }
}
