package com.jqy.server.core;

import java.nio.charset.Charset;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.jqy.server.core.codec.ServerProtocolCodecFactory;

/**
 * 服务器
 * 
 * @author Simple
 * @date 2013-9-23 下午02:16:46
 * @Description TODO
 */
public class Server {

  private NioSocketAcceptor acceptor;

  public void start() {
    acceptor=new NioSocketAcceptor();
    DefaultIoFilterChainBuilder filterChain=acceptor.getFilterChain();
    filterChain.addLast("codecFilter", new ProtocolCodecFilter(new ServerProtocolCodecFactory(Charset.forName("utf-8"))));
  }
}
