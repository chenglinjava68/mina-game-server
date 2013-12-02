package com.jqy.server.entity.job;

/**
 * 职业列表
 * @author Simple
 * @date 2013-11-29 下午05:06:29
 * @Description TODO
 */
public enum JobEnum {
  // 战士，医生，法师，盗贼
  JOB_WARRIOR(0), JOB_DOCTOR(1), JOB_MAGICIAN(2), JOB_ROBBER(3);

  final byte jobId;

  private JobEnum(int id) {
    jobId=(byte)id;
  }

  public byte getId() {
    return jobId;
  }

  public boolean isA(JobEnum obj) {
    return getId() == (obj.getId());
  }

  public static JobEnum getById(int id) {
    for(JobEnum job: JobEnum.values()) {
      if(job.getId() == (id)) {
        return job;
      }
    }
    return JobEnum.JOB_WARRIOR;
  }
}