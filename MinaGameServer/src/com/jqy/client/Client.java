package com.jqy.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.jqy.server.common.Constant;

public class Client implements Runnable {

  private Logger log=Logger.getLogger(this.getClass());

  private NioSocketConnector connector;

  public Client() {
  }

  public Client(int i) {
    this.setI(i);
  }

  private int i=0;

  public ConnectFuture getCF() {
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
    cf.awaitUninterruptibly();
    return cf;
  }

  public void sendData(ConnectFuture cf, JSONObject jsonObject) {
    cf.getSession().write(jsonObject);
  }

  public void end(ConnectFuture cf) {
    cf.getSession().getCloseFuture().awaitUninterruptibly();
    connector.dispose();
  }

  public JSONObject regUser(int ptlId, String username, String password, String email) {
    JSONObject jsonObject=getReqJson(ptlId);
    JSONObject bodyData=new JSONObject();
    bodyData.put("username", username);
    bodyData.put("password", password);
    bodyData.put("email", email);
    jsonObject.put("data", bodyData);
    log.debug(String.format("客户端请求数据=%s", jsonObject.toString()));
    return jsonObject;
  }

  public JSONObject login(int ptlId, String username, String password) {
    JSONObject jsonObject=getReqJson(ptlId);
    JSONObject bodyData=new JSONObject();
    bodyData.put("username", username);
    bodyData.put("password", password);
    jsonObject.put("data", bodyData);
    log.debug(String.format("客户端请求数据=%s", jsonObject.toString()));
    return jsonObject;
  }

  public JSONObject regPlayer(int ptlId, String nickName, boolean sex, int jobId) {
    JSONObject jsonObject=getReqJson(ptlId);
    JSONObject bodyData=new JSONObject();
    bodyData.put("nickName", nickName);
    bodyData.put("sex", sex);
    bodyData.put("jobId", jobId);
    jsonObject.put("data", bodyData);
    log.debug(String.format("客户端请求数据=%s", jsonObject.toString()));
    return jsonObject;
  }

  public JSONObject startGame(int ptlId, int roleIndex) {
    JSONObject jsonObject=getReqJson(ptlId);
    JSONObject bodyData=new JSONObject();
    bodyData.put("roleIndex", roleIndex);
    jsonObject.put("data", bodyData);
    log.debug(String.format("客户端请求数据=%s", jsonObject.toString()));
    return jsonObject;
  }

  public JSONObject getAllPlayer(int ptlId) {
    JSONObject jsonObject=getReqJson(ptlId);
    JSONObject bodyData=new JSONObject();
    jsonObject.put("data", bodyData);
    log.debug(String.format("客户端请求数据=%s", jsonObject.toString()));
    return jsonObject;
  }

  @Override
  public void run() {
  }

  private JSONObject getReqJson(int ptlId) {
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("type", Constant.REQ);
    jsonObject.put("id", ptlId);
    return jsonObject;
  }

  public void setI(int i) {
    this.i=i;
  }

  public int getI() {
    return i;
  }
}
