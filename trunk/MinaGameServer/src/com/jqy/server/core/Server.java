package com.jqy.server.core;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
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

  private static final int PORT=9999;

  private NioSocketAcceptor acceptor;

  private void start() {
    acceptor=new NioSocketAcceptor();
    DefaultIoFilterChainBuilder filterChain=acceptor.getFilterChain();
    filterChain.addLast("codecFilter", new ProtocolCodecFilter(new ServerProtocolCodecFactory(Charset.forName("utf-8"))));
    acceptor.setHandler(new ServerHandler());
    try {
      acceptor.bind(new InetSocketAddress(InetAddress.getLocalHost(), PORT));
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
