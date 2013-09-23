package com.jqy.server.entity;

import java.util.Date;

/**
 * 用户
 * 
 * @author Simple
 * @date 2013-9-23 下午01:53:10
 * @Description TODO
 */
public class User {

  private int id;

  private String username;

  private String password;

  private Date createDate;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id=id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password=password;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", password=" + password + ", username=" + username + "]";
  }

  public void setCreateDate(Date createDate) {
    this.createDate=createDate;
  }

  public Date getCreateDate() {
    return createDate;
  }
}
