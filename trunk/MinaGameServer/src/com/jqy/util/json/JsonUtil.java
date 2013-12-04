package com.jqy.util.json;

import java.util.Date;

import net.sf.json.JsonConfig;

/**
 * Json 工具类
 * 
 * @author Simple
 * @date 2013-7-26 下午12:20:02
 * @Description TODO
 */
public class JsonUtil {

  public static JsonConfig configJson() {
    JsonConfig jcf=new JsonConfig();
    jcf.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
    return jcf;
  }
}
