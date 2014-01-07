package com.jqy.client;

public class MyPlayer {
  
  private int id;// id

  private String nickName;// 昵称

  private boolean sex;// 性别

  private int level=1;// 等级

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName=nickName;
  }

  public boolean isSex() {
    return sex;
  }

  public void setSex(boolean sex) {
    this.sex=sex;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level=level;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
