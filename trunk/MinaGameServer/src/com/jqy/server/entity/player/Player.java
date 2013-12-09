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

  private int level;// 等级

  private Date regDate;// 注册日期

  private Date modDate;// 修改日期

  private Job job;// 职业

  private User user;// 用户

  // 临时变量，不入库
  private int _onlineTime;// 在线时长

  private boolean _online;// 在线

  private int _idleTime;// 空闲时长

  // 心跳
  public void heartbeat() {
    System.out.println(nickName + " idleTime+30");
    this._idleTime+=30;// 空闲时间+1
    if(this._online == false) {
      return;
    } else {
      System.out.println(nickName + " onlineTime+30");
      this._onlineTime+=30;// 在线时间+1
    }
  }

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
    this.regDate=regDate;
  }

  public Date getRegDate() {
    return regDate;
  }

  public void setModDate(Date modDate) {
    this.modDate=modDate;
  }

  public Date getModDate() {
    return modDate;
  }

  public void setUser(User user) {
    this.user=user;
  }

  public User getUser() {
    return user;
  }

  public void set_onlineTime(int _onlineTime) {
    this._onlineTime=_onlineTime;
  }

  public int get_onlineTime() {
    return _onlineTime;
  }

  public void set_online(boolean _online) {
    this._online=_online;
  }

  public boolean is_online() {
    return _online;
  }

  public void set_idleTime(int _idleTime) {
    this._idleTime=_idleTime;
  }

  public int get_idleTime() {
    return _idleTime;
  }

  public void setLevel(int level) {
    this.level=level;
  }

  public int getLevel() {
    return level;
  }
}
