package com.jqy.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;

public class Client implements Runnable {

  @SuppressWarnings("unused")
  private Logger log=Logger.getLogger(this.getClass());

  private NioSocketConnector connector;

  public Client() {
  }

  public Client(int i) {
    this.setI(i);
  }

  private int i=0;

  public ConnectFuture connection() {
    connector=new NioSocketConnector();
    // SocketSessionConfig ssc=connector.getSessionConfig();
    // 空闲
    // ssc.setIdleTime(IdleStatus.BOTH_IDLE, 120);
    // connector.getFilterChain().addLast("code", new ProtocolCodecFilter(new
    // ServerProtocolCodecFactory(Charset.forName("utf-8"))));
    DefaultIoFilterChainBuilder filterChain=connector.getFilterChain();
    filterChain.addLast("clientCodecFilter", new ProtocolCodecFilter(new ClientProtocolCodecFactory(Charset.forName("utf-8"))));
    connector.setHandler(new ClientHandler());
    connector.setConnectTimeoutMillis(3000);
    ConnectFuture cf=connector.connect(new InetSocketAddress("localhost", 9999));
    // ConnectFuture cf=connector.connect(new InetSocketAddress("172.19.0.176", 9999));
     cf.awaitUninterruptibly();// 等待异步执行结果的返回，属于阻塞
//    cf.addListener(new IoFutureListener<ConnectFuture>() {// 等待异步执行结果的返回，不会阻塞
//
//        @Override
//        public void operationComplete(ConnectFuture future) {
//          try {
//            Thread.sleep(1000);
//          } catch(InterruptedException e) {
//            e.printStackTrace();
//          }
//          if(future.isConnected()) {
//            IoSession session=future.getSession();
//            log.debug(String.format("连接成功,得到SESSION:%s", session.toString()));
//          } else {
//            log.debug("连接不存在");
//          }
//        }
//      });
//    log.debug("等待异步结果ing,继续往下执行");
    return cf;
  }

  public void sendData(ConnectFuture cf, MyBuffer buf) {
    cf.getSession().write(buf);
  }

  public void end(ConnectFuture cf) {
    cf.getSession().getCloseFuture().awaitUninterruptibly();
    connector.dispose();
  }

  public MyBuffer regUser(short ptlId, String username, String password, String email) {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort(ptlId);
    buf.putPrefixedString(username);
    buf.putPrefixedString(password);
    buf.putPrefixedString(email);
    buf.flip();
    return buf;
  }

  public MyBuffer login(short ptlId, String username, String password) {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort(ptlId);
    buf.putPrefixedString(username);
    buf.putPrefixedString(password);
    buf.flip();
    return buf;
  }

  public MyBuffer regPlayer(short ptlId, String nickName, boolean sex, int jobId) {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort(ptlId);
    buf.putPrefixedString(nickName);
    buf.put((byte)(sex == true ? 1 : 0));
    buf.putInt(jobId);
    buf.flip();
    return buf;
  }

  public MyBuffer startGame(short ptlId, int roleIndex) {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort(ptlId);
    buf.putInt(roleIndex);
    buf.flip();
    return buf;
  }

  public MyBuffer getAllPlayer(short ptlId) {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort(ptlId);
    buf.flip();
    return buf;
  }

  public MyBuffer hearbeat(short ptlId) {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort(ptlId);
    buf.flip();
    return buf;
  }

  @Override
  public void run() {
  }

  public void setI(int i) {
    this.i=i;
  }

  public int getI() {
    return i;
  }
  
  public void login(){
    
  }

  public void setConnector(NioSocketConnector connector) {
    this.connector = connector;
  }

  public NioSocketConnector getConnector() {
    return connector;
  }
}
