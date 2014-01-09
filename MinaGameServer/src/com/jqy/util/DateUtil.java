package com.jqy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

  public static String getCurrentDate() {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
    return sdf.format(new Date());
  }
}
