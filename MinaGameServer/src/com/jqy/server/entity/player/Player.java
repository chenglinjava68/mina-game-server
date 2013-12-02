package com.jqy.server.entity.player;

import java.util.Date;

import com.jqy.server.entity.job.Job;
import com.jqy.server.entity.user.User;

/**
 * 玩家
 * 
 * @author Simple
 * @date 2013-11-29 下午05:14:42
 * @Description TODO
 */
public class Player {

  private int id;

  private String nickName;// 昵称

  private boolean sex;// 性别

  private Date regDate;// 注册日期

  private Date modDate;// 修改日期
  
  private Job job;// 职业
  
  private User user;// 用户


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id=id;
  }

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

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job=job;
  }

  public void setRegDate(Date regDate) {
    this.regDate = regDate;
  }

  public Date getRegDate() {
    return regDate;
  }

  public void setModDate(Date modDate) {
    this.modDate = modDate;
  }

  public Date getModDate() {
    return modDate;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }
}
