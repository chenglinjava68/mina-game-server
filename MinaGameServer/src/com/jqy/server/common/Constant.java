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
  public static final byte REQ=0xa;

  /**
   * 协议类型-响应
   */
  public static final byte RESP=0xb;

  /**
   * 状态-成功
   */
  public static final byte SUCCESS=1;

  /**
   * 状态-失败
   */
  public static final byte FAILD=0;

  /**
   * IoSession用户属性
   */
  public static final String USER="user";

  /**
   * IoSession玩家属性
   */
  public static final String PLAYER="player";

  /**
   * 用户状态-有效
   */
  public static final boolean USER_VALID=true;

  /**
   * 用户状态-无效
   */
  public static final boolean USER_INVALID=false;

  /**
   * 聊天-公共
   */
  public static final byte CHAT_COMMON=1;

  /**
   * 聊天-私聊
   */
  public static final byte CHAT_PRIVATE=2;
}
