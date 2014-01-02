package com.jqy.server.core;

public class ServerConfig {

  /**
   * 端口号
   */
  public static final int PORT=9999;
  
  /**
   * 报头长度
   */
  public static final int HEAD_LENGTH=7;

  /**
   * 读缓冲区大小
   */
  public static final int READ_BUFFER_SIZE=2048;

  /**
   * 接收缓冲区大小
   */
  public static final int RECEIVE_BUFFER_SIZE=2048;
  
  /**
   * 空闲时间:s
   */
  public static final int IDLE=60;
}
