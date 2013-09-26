package com.jqy.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {

  public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {
    // TODO Auto-generated method stub
  }

  public void messageReceived(IoSession arg0, Object arg1) throws Exception {
    // TODO Auto-generated method stub
  }

  public void messageSent(IoSession arg0, Object arg1) throws Exception {
    // TODO Auto-generated method stub
  }

  public void sessionClosed(IoSession arg0) throws Exception {
    // TODO Auto-generated method stub
  }

  public void sessionCreated(IoSession arg0) throws Exception {
    // TODO Auto-generated method stub
  }

  public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
    // TODO Auto-generated method stub
  }

  public void sessionOpened(IoSession arg0) throws Exception {
    // TODO Auto-generated method stub
  }
}
