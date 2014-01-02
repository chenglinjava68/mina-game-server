package com.jqy.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.jqy.server.core.MyBuffer;

public class MyClient {

  private Logger log=Logger.getLogger(this.getClass());

  private NioSocketConnector connector;

  private ConnectFuture cf;

  private IoSession session;

  private MyUser user=new MyUser();

  private MyPlayer player=new MyPlayer();

  /**
   * 连接
   * 
   * @return
   */
  public boolean connection() {
    connector=new NioSocketConnector();
    setSessionConfig();
    setFilter();
    setHandler();
    connector.setConnectTimeoutMillis(3000);
    cf=connector.connect(new InetSocketAddress("localhost", 9999));
    cf.awaitUninterruptibly();// 等待异步执行结果的返回，属于阻塞
    if(cf.isConnected()) {
      session=cf.getSession();
      log.debug(String.format("connect to server success,SESSION=%s", session.toString()));
      return true;
    } else {
      log.warn(String.format("connect to server faild!"));
      return false;
    }
  }

  /**
   * sessionConfig 配置
   */
  private void setSessionConfig() {
    SocketSessionConfig ssc=connector.getSessionConfig();
    ssc.setUseReadOperation(true);
  }

  /**
   * 过滤链
   */
  private void setFilter() {
    DefaultIoFilterChainBuilder filterChain=connector.getFilterChain();
    filterChain.addLast("clientCodecFilter", new ProtocolCodecFilter(new ClientProtocolCodecFactory(Charset.forName("utf-8"))));
  }

  /**
   * Handler
   */
  private void setHandler() {
    connector.setHandler(new ClientHandler());
  }

  public boolean sendMessage(MyBuffer buf) {
    WriteFuture wf=session.write(buf);// 发送数据操作
    wf.awaitUninterruptibly();// 等待发送数据操作完成
    if(wf.getException() != null) {
      try {
        throw new MyException(wf.getException().getMessage());
      } catch(MyException e) {
        e.printStackTrace();
      }
    }
    if(wf.isWritten()) {
      return true;
    }
    return false;
  }

  public Object readMessage() {
    ReadFuture rf=session.read();
    rf.awaitUninterruptibly();
    if(rf.getException() != null) {
      try {
        throw new MyException(rf.getException().getMessage());
      } catch(MyException e) {
        e.printStackTrace();
      }
    }
    return rf.getMessage();
  }

  public void setConnector(NioSocketConnector connector) {
    this.connector=connector;
  }

  public NioSocketConnector getConnector() {
    return connector;
  }

  public void setCf(ConnectFuture cf) {
    this.cf=cf;
  }

  public ConnectFuture getCf() {
    return cf;
  }

  public void setSession(IoSession session) {
    this.session=session;
  }

  public IoSession getSession() {
    return session;
  }

  public void setUser(MyUser user) {
    this.user = user;
  }

  public MyUser getUser() {
    return user;
  }

  public void setPlayer(MyPlayer player) {
    this.player = player;
  }

  public MyPlayer getPlayer() {
    return player;
  }
}
