package com.jqy.client;

import java.net.InetSocketAddress;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.junit.Test;

import com.jqy.server.common.Constant;
import com.jqy.server.entity.job.JobEnum;

public class Client {

  private Logger log=Logger.getLogger(this.getClass());

  private NioSocketConnector connector;

  private ConnectFuture getCF() {
    connector=new NioSocketConnector();
    connector.setHandler(new ClientHandler());
    connector.setConnectTimeoutMillis(3000);
    ConnectFuture cf=connector.connect(new InetSocketAddress(9999));
    cf.awaitUninterruptibly();
    return cf;
  }

  private void sendData(ConnectFuture cf, JSONObject jsonObject) {
    // 封装bodyData
    byte[] body=jsonObject.toString().getBytes();
    IoBuffer buffer=IoBuffer.allocate(2 + body.length);
    buffer.putShort((short)body.length);
    buffer.put(body);
    buffer.flip();
    cf.getSession().write(buffer);
  }

  @Test
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
    JSONObject allPlayerJson=getAllPlayer();
    sendData(cf, allPlayerJson);
    //
    cf.getSession().getCloseFuture().awaitUninterruptibly();
    connector.dispose();
  }

  @SuppressWarnings("unused")
  private JSONObject regUser() {
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

  @SuppressWarnings("unused")
  private JSONObject login() {
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

  @SuppressWarnings("unused")
  private JSONObject regPlayer() {
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

  private JSONObject getAllPlayer() {
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("type", Constant.REQ);
    jsonObject.put("id", 0x0007);
    JSONObject bodyData=new JSONObject();
    jsonObject.put("data", bodyData);
    log.debug("Client Request Data=" + jsonObject.toString());
    return jsonObject;
  }
}
