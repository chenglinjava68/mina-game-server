package com.jqy.client;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {

  private Logger log=Logger.getLogger(this.getClass());

  @Override
  public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    // TODO Auto-generated method stub
    super.exceptionCaught(session, cause);
  }

  @Override
  public void messageReceived(IoSession session, Object message) throws Exception {
    IoBuffer buffer=(IoBuffer)message;
    byte[] data=buffer.array();
    String jsonString=new String(data, "utf-8");
    log.debug("服务器返回数据=" + jsonString);
  }

  @Override
  public void messageSent(IoSession session, Object message) throws Exception {
    // TODO Auto-generated method stub
    super.messageSent(session, message);
  }

  @Override
  public void sessionClosed(IoSession session) throws Exception {
    // TODO Auto-generated method stub
    super.sessionClosed(session);
  }

  @Override
  public void sessionCreated(IoSession session) throws Exception {
    // TODO Auto-generated method stub
    super.sessionCreated(session);
  }

  @Override
  public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    // TODO Auto-generated method stub
    super.sessionIdle(session, status);
  }

  @Override
  public void sessionOpened(IoSession session) throws Exception {
    // TODO Auto-generated method stub
    super.sessionOpened(session);
  }
}
