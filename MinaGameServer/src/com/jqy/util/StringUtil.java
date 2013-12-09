package com.jqy.util;

public class StringUtil {

  /**
   * string is Null
   * 
   * @param s
   * @return
   */
  public boolean isNull(String s) {
    if(null == s || "null".equals(s) || "".equals(s))
      return true;
    return false;
  }

  /**
   * string 转 16进制
   * 
   * @param str
   * @return
   */
  public String string2Hex(String str) {
    StringBuffer sb=new StringBuffer();
    byte[] b=str.getBytes();
    for(int i=0; i < b.length; i++) {
      int v=b[i] & 0xFF;
      String hex=Integer.toHexString(v).toUpperCase();
      sb.append(hex);
    }
    return sb.toString();
  }

  /**
   * string 转 2进制
   * 
   * @param str
   * @return
   */
  public String string2Binary(String str) {
    StringBuffer sb=new StringBuffer();
    byte[] b=str.getBytes();
    for(int i=0; i < b.length; i++) {
      int v=b[i] & 0xFF;
      String hex=Integer.toBinaryString(v);
      sb.append(hex);
    }
    return sb.toString();
  }

  /**
   * string 转 8进制
   * 
   * @param str
   * @return
   */
  public String string2Octal(String str) {
    StringBuffer sb=new StringBuffer();
    byte[] b=str.getBytes();
    for(int i=0; i < b.length; i++) {
      int v=b[i] & 0xFF;
      String hex=Integer.toOctalString(v);
      sb.append(hex);
    }
    return sb.toString();
  }

  /**
   * 16进制 转 string
   * 
   * @param s
   * @return
   */
  public static String hex2String(String s) {
    byte[] keys=new byte[s.length() / 2];
    for(int i=0; i < keys.length; i++) {
      try {
        keys[i]=(byte)(0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
    try {
      s=new String(keys, "utf-8");// UTF-16le:Not
    } catch(Exception e1) {
      e1.printStackTrace();
    }
    return s;
  }

  public static void main(String[] args) {
    StringUtil su=new StringUtil();
    String str="fuck man!";
    System.out.println(str);
    // 2
    String binary=su.string2Binary(str);
    System.out.println("2进制:" + binary);
    // 8
    String octal=su.string2Octal(str);
    System.out.println("8进制:" + octal);
    // 16
    String hex=su.string2Hex(str);
    System.out.println("16进制:" + hex);
  }
}
