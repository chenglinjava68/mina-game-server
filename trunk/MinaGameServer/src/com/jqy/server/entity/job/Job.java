package com.jqy.server.entity.job;

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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id=id;
  }

  public JobEnum getType() {
    return type;
  }

  public void setType(JobEnum type) {
    this.type=type;
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
}
