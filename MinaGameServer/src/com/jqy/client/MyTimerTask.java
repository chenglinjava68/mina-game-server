package com.jqy.client;

import java.util.TimerTask;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;

/**
 * 定时任务
 * @author Simple
 * @date 2014-1-8 下午05:00:49
 * @Description TODO
 */
public class MyTimerTask extends TimerTask {

  private MyClient client;

  public MyTimerTask(MyClient client) {
    this.client=client;
  }

  @Override
  public void run() {
    MyBuffer buf=heartbeat();
    client.getSession().write(buf);
    System.out.println("hearbeat req");
  }

  private MyBuffer heartbeat() {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort((short)0x1001);
    buf.flip();
    return buf;
  }

  public void setClient(MyClient client) {
    this.client=client;
  }

  public MyClient getClient() {
    return client;
  }
}
