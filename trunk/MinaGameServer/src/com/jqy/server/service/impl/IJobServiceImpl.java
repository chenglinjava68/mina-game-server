package com.jqy.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.jqy.server.dao.IJobDao;
import com.jqy.server.entity.job.Job;
import com.jqy.server.entity.job.JobEnum;
import com.jqy.server.service.IJobService;

@Service
public class IJobServiceImpl implements IJobService, InitializingBean {

  private Logger log=Logger.getLogger(this.getClass());

  @Resource
  private IJobDao jobDao;

  @Override
  public boolean create(Job job) {
    int status=jobDao.register(job);
    return status > 0 ? true : false;
  }

  @Override
  public Job selectByType(JobEnum jobenum) {
    return jobDao.selectByType(jobenum);
  }

  /**
   * 初始化4种职业
   */
  private void initJobs() {
    List<Job> jobs=jobDao.selectAll();
    if(jobs.size() == 0) {
      Job job1=new Job();
      job1.setAtk(48);
      job1.setDef(2);
      job1.setHp(780);
      job1.setMp(200);
      job1.setMs(190);
      job1.setType(JobEnum.JOB_DOCTOR.getCode());
      job1.setRegDate(new Date());
      jobDao.register(job1);
      Job job2=new Job();
      job2.setAtk(60);
      job2.setDef(1);
      job2.setHp(800);
      job2.setMp(150);
      job2.setMs(200);
      job2.setType(JobEnum.JOB_MAGICIAN.getCode());
      job2.setRegDate(new Date());
      jobDao.register(job2);
      Job job3=new Job();
      job3.setAtk(50);
      job3.setDef(4);
      job3.setHp(600);
      job3.setMp(300);
      job3.setMs(210);
      job3.setType(JobEnum.JOB_ROBBER.getCode());
      job3.setRegDate(new Date());
      jobDao.register(job3);
      Job job4=new Job();
      job4.setAtk(90);
      job4.setDef(1);
      job4.setHp(500);
      job4.setMp(150);
      job4.setMs(220);
      job4.setType(JobEnum.JOB_WARRIOR.getCode());
      job4.setRegDate(new Date());
      jobDao.register(job4);
      log.debug(String.format("init jobs success!"));
    } else {
      log.debug(String.format("init jobs is ok"));
    }
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    initJobs();
  }
}
