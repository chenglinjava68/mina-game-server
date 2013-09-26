package com.jqy.client;

import java.net.InetSocketAddress;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class Client {

  private Logger log=Logger.getLogger(this.getClass());

  private NioSocketConnector connector;

  private void clientStart() {
    connector=new NioSocketConnector();
    connector.setHandler(new ClientHandler());
    ConnectFuture cf=connector.connect(new InetSocketAddress(9999));
    cf.awaitUninterruptibly();
    // send data
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("type", com.jqy.server.common.Common.REQ);
    jsonObject.put("id", 0x0001);
    JSONObject bodyData=new JSONObject();
    bodyData.put("username", "123");
    bodyData.put("password", "asd");
    jsonObject.put("data", bodyData);
    log.debug("Client Request Data=" + jsonObject.toString());
    //
    // 封装bodyData
    byte[] body=jsonObject.toString().getBytes();
    IoBuffer buffer=IoBuffer.allocate(2 + body.length);
    buffer.putShort((short)body.length);
    buffer.put(body);
    buffer.flip();
    cf.getSession().write(buffer);
  }

  public static void main(String[] args) {
    new Client().clientStart();
  }
}
