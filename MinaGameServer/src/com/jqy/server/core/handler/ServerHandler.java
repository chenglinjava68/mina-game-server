package com.jqy.server.core.handler;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoHandlerAdapter {

  private Logger log=Logger.getLogger(this.getClass());

  @Override
  public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    log.debug("exceptionCaught");
  }

  @Override
  public void messageReceived(IoSession session, Object message) throws Exception {
    log.debug("messageReceived");
    IoBuffer buffer=(IoBuffer)message;
    byte[] data=buffer.array();
    String s=new String(data, "utf-8");
    log.debug(s);
    log.debug("JSON解析数据");
    JSONObject jsonObject=JSONObject.fromObject(s);
    int id=jsonObject.getInt("id");
    int type=jsonObject.getInt("type");
    log.debug("id=" + id + ",type=" + type);
    // JSONObject jsonObject2=jsonObject.getJSONObject("data");
  }

  @Override
  public void messageSent(IoSession session, Object message) throws Exception {
    log.debug("messageSent");
  }

  @Override
  public void sessionClosed(IoSession session) throws Exception {
    log.debug("sessionClosed");
  }

  @Override
  public void sessionCreated(IoSession session) throws Exception {
    log.debug("sessionCreated");
  }

  @Override
  public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    log.debug("sessionIdle");
  }

  @Override
  public void sessionOpened(IoSession session) throws Exception {
    log.debug("sessionOpened");
  }
}
