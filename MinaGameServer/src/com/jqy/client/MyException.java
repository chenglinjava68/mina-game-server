package com.jqy.client;

public class MyException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID=-7243387802900354013L;

  public MyException() {// 用来创建无参数对象
  }

  public MyException(String message) { // 用来创建指定参数对象
    super(message); // 调用超类构造器
  }
}
