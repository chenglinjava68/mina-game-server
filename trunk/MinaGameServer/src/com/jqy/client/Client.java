package com.jqy.client;

import java.net.InetSocketAddress;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.jqy.server.common.Constant;
import com.jqy.server.entity.job.JobEnum;

public class Client implements Runnable {

  private Logger log=Logger.getLogger(this.getClass());

  private NioSocketConnector connector;

  private ConnectFuture getCF() {
    connector=new NioSocketConnector();
    connector.setHandler(new ClientHandler());
    connector.setConnectTimeoutMillis(3000);
     ConnectFuture cf=connector.connect(new InetSocketAddress("localhost", 9999));
//    ConnectFuture cf=connector.connect(new InetSocketAddress("172.19.0.176", 9999));
    cf.awaitUninterruptibly();
    return cf;
  }

  private void sendData(ConnectFuture cf, JSONObject jsonObject) {
    // 封装bodyData
    byte[] body=jsonObject.toString().getBytes();
    IoBuffer buffer=IoBuffer.allocate(2 + 2 + body.length);
    buffer.putShort((short)body.length);
    buffer.putShort((short)body.length);
    buffer.put(body);
    buffer.flip();
    cf.getSession().write(buffer);
  }

  public static void main(String[] args) {
    for(int i=0; i < 1; i++) {
      Client c=new Client();
      Thread t=new Thread(c);
      t.start();
    }
    // System.out.println(new Client().test());
  }

  public String test() {
    String s="{'type':1,'id':7,'data':{}}";
    IoBuffer buf=IoBuffer.allocate(2 + s.getBytes().length);
    buf.putShort((short)s.getBytes().length);
    buf.put(s.getBytes());
    buf.flip();
    byte[] bytes=buf.array();
    StringBuffer sb=new StringBuffer();
    for(int i=0; i < bytes.length; i++) {
      int v=bytes[i] & 0xFF;
      String a=Integer.toHexString(v);
      sb.append(a);
    }
    return sb.toString();
  }

  public void clientStart() {
    ConnectFuture cf=getCF();
    // // 注册用户
    // JSONObject regUserJson=regUser();
    // sendData(cf, regUserJson);
    // // 登陆
    // JSONObject loginJson=login();
    // sendData(cf, loginJson);
    // // 创建玩家
    // JSONObject regPlayerJson=regPlayer();
    // sendData(cf, regPlayerJson);
    // 获取用户列表
    JSONObject allPlayerJson=getAllPlayer();
    sendData(cf, allPlayerJson);
    //
    cf.getSession().getCloseFuture().awaitUninterruptibly();
    connector.dispose();
  }

  public JSONObject regUser() {
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("type", Constant.REQ);
    jsonObject.put("id", 0x0001);
    JSONObject bodyData=new JSONObject();
    bodyData.put("username", "jiangqy1");
    bodyData.put("password", "jiangqy1");
    bodyData.put("email", "jiangqy1@qq.com");
    jsonObject.put("data", bodyData);
    log.debug("Client Request Data=" + jsonObject.toString());
    return jsonObject;
  }

  public JSONObject login() {
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("type", Constant.REQ);
    jsonObject.put("id", 0x0003);
    JSONObject bodyData=new JSONObject();
    bodyData.put("username", "jiangqy1");
    bodyData.put("password", "jiangqy1");
    jsonObject.put("data", bodyData);
    log.debug("Client Request Data=" + jsonObject.toString());
    return jsonObject;
  }

  public JSONObject regPlayer() {
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("type", Constant.REQ);
    jsonObject.put("id", 0x0005);
    JSONObject bodyData=new JSONObject();
    bodyData.put("nickName", "simple3");
    bodyData.put("sex", false);
    bodyData.put("jobId", JobEnum.JOB_ROBBER.getCode());
    jsonObject.put("data", bodyData);
    log.debug("Client Request Data=" + jsonObject.toString());
    return jsonObject;
  }

  public JSONObject getAllPlayer() {
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("type", Constant.REQ);
    jsonObject.put("id", 0x0007);
    JSONObject bodyData=new JSONObject();
    jsonObject.put("data", bodyData);
    log.debug("Client Request Data=" + jsonObject.toString());
    return jsonObject;
  }

  @Override
  public void run() {
    clientStart();
  }
}
