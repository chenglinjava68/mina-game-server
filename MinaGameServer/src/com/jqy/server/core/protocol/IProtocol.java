package com.jqy.server.core.protocol;

/**
 * 协议接口
 * 
 * @author Simple
 * @date 2013-9-26 上午11:44:50
 * @Description TODO
 */
public interface IProtocol {

  /**
   * 获取协议类型
   * @return
   */
  public byte getProtocolType();

  /**
   * 获取协议编号
   * @return
   */
  public short getProtocolId();
}
