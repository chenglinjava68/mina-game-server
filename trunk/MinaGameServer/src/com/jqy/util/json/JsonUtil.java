package com.jqy.util.json;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * Json 工具类
 * 
 * @author Simple
 * @date 2013-7-26 下午12:20:02
 * @Description TODO
 */
public class JsonUtil {

  public static JsonConfig dateFormat() {
    JsonConfig jcf=new JsonConfig();
    jcf.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
    return jcf;
  }

  public static JsonConfig ignoreCycle() {
    JsonConfig jcf=new JsonConfig();
    jcf.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
    return jcf;
  }

  public static JsonConfig dateFormatAndIgnoreCycleConfig() {
    JsonConfig jcf=new JsonConfig();
    jcf.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
    jcf.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
    return jcf;
  }
}
