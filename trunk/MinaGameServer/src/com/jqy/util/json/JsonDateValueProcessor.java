package com.jqy.util.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * Json在对象格式化的过程中对java.util.Date类型进行自定义格式化
 * 
 * @author Simple
 * @date 2013-7-26 下午12:18:58
 * @Description TODO
 */
public class JsonDateValueProcessor implements JsonValueProcessor {

  private String format="yyyy-MM-dd HH:mm:ss";

  public JsonDateValueProcessor() {
  }

  public Object processArrayValue(Object value, JsonConfig jcf) {
    String[] obj={};
    if(value instanceof Date[]) {
      SimpleDateFormat sdf=new SimpleDateFormat(format);
      Date[] dates=(Date[])value;
      obj=new String[dates.length];
      for(int i=0; i < dates.length; i++) {
        obj[i]=sdf.format(dates[i]).trim();
      }
    }
    return obj;
  }

  public Object processObjectValue(String key, Object value, JsonConfig jcf) {
    if(value instanceof Date) {
      String str=new SimpleDateFormat(format).format((Date)value);
      return str.trim();
    }
    return value == null ? null : value.toString();
  }
}
