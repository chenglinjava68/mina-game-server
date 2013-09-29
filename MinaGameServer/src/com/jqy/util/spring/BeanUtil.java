package com.jqy.util.spring;

import org.springframework.context.ApplicationContext;

import com.jqy.server.core.protocol.AbsReqProtocol;

/**
 * 
 * @author Simple
 * @date 2013-3-5 上午11:00:11
 * @Description spring bean 工具
 */
public class BeanUtil {

  public static AbsReqProtocol makeNewReqProtocolHandler(String name) {
    return (AbsReqProtocol)SpringContextUtil.getBean(name, AbsReqProtocol.class);
  }

  public static boolean containsBean(String id) {
    return SpringContextUtil.containsBean(id);
  }

  public static ApplicationContext getContext() {
    return SpringContextUtil.getApplicationContext();
  }
}
