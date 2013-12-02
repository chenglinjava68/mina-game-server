package com.jqy.server.common;

/**
 * 常量类
 * 
 * @author Simple
 * @date 2013-11-5 下午05:31:53
 * @Description TODO
 */
public class Constant {

  /**
   * 协议类型-请求
   */
  public static final byte REQ=0x0001;

  /**
   * 协议类型-响应
   */
  public static final byte RESP=0x0002;

  /**
   * 状态-成功
   */
  public static final int SUCCESS=1;

  /**
   * 状态-失败
   */
  public static final int FAILD=0;

  /**
   * 用户状态-有效
   */
  public static final boolean USER_VALID=true;

  /**
   * 用户状态-无效
   */
  public static final boolean USER_INVALID=false;
}
