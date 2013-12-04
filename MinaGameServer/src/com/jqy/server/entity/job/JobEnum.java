package com.jqy.server.entity.job;

/**
 * 职业列表
 * 
 * @author Simple
 * @date 2013-11-29 下午05:06:29
 * @Description TODO
 */
public enum JobEnum {
  // 战士，医生，法师，盗贼
    JOB_WARRIOR(0, "战士"), JOB_DOCTOR(1, "医生"), JOB_MAGICIAN(2, "法师"), JOB_ROBBER(3, "盗贼");

  final int code;

  final String name;

  private JobEnum(int code, String name) {
    this.code=code;
    this.name=name;
  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public boolean isA(JobEnum obj) {
    return getCode() == (obj.getCode());
  }

  public static JobEnum getByCode(int id) {
    for(JobEnum job: JobEnum.values()) {
      if(job.getCode() == (id)) {
        return job;
      }
    }
    return JobEnum.JOB_WARRIOR;
  }
}