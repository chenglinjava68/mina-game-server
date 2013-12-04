package com.jqy.server.entity.job;

import java.util.Date;

/**
 * 职业
 * 
 * @author Simple
 * @date 2013-11-29 下午05:06:22
 * @Description TODO
 */
public class Job {

  private int id;

  private JobEnum type=JobEnum.JOB_MAGICIAN;// 职业类型

  private int hp;// 生命值

  private int mp;// 魔法值

  private int atk;// 攻击力

  private int def;// 防御力

  private int ms;// 移动速度

  private Date regDate;// 创建日期

  private Date modDate;// 修改日期

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id=id;
  }

  public int getType() {
    return type.getCode();
  }

  public void setType(int code) {
    this.type=JobEnum.getByCode(code);
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp=hp;
  }

  public int getMp() {
    return mp;
  }

  public void setMp(int mp) {
    this.mp=mp;
  }

  public int getAtk() {
    return atk;
  }

  public void setAtk(int atk) {
    this.atk=atk;
  }

  public int getDef() {
    return def;
  }

  public void setDef(int def) {
    this.def=def;
  }

  public int getMs() {
    return ms;
  }

  public void setMs(int ms) {
    this.ms=ms;
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
}
