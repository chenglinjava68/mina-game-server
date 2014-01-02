package com.jqy.client.ui;

import org.apache.log4j.Logger;

import com.jqy.server.core.MyBuffer;

public class DataUtil {

  private static Logger log=Logger.getLogger(DataUtil.class);

  public static MyBuffer getBodyData(Object o) {
    MyBuffer myBuf=(MyBuffer)o;
    myBuf.flip();
    byte type=myBuf.get();
    short id=myBuf.getShort();
    int length=myBuf.getInt();
    byte[] data=new byte[length];
    myBuf.get(data);
    MyBuffer bodyData=MyBuffer.allocate(length);
    bodyData.put(data);
    bodyData.flip();
    log.debug(String.format("TYPE=%s,ID=%s,LENGTH=%s", type, id, length));
    return bodyData;
  }
}
