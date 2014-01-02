package com.jqy.client;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.jqy.server.core.MyBuffer;

public class ClientHandler extends IoHandlerAdapter {

  private Logger log=Logger.getLogger(this.getClass());

  @Override
  public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    // TODO Auto-generated method stub
    super.exceptionCaught(session, cause);
  }

  @Override
  public void messageReceived(IoSession session, Object message) throws Exception {
    MyBuffer buf=(MyBuffer)message;
    byte type=buf.get();
    short id=buf.getShort();
    int bodyLength=buf.getInt();
    byte[] data=new byte[bodyLength];
    buf.get(data);
    log.debug(String.format("服务器返回数据[TYPE=%s,ID=%s,BODY_LENGTH=%s,DATA=%s]", type, id, bodyLength, data.toString()));
    MyBuffer bodyBuf=MyBuffer.allocate(bodyLength);
    bodyBuf.put(data);
    bodyBuf.flip();
    switch(id) {
      case 0x0004:
        log.debug(String.format("result=%s", bodyBuf.get()));
    }
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
