package com.jqy.server.dao;

import java.util.List;

import com.jqy.server.entity.job.Job;
import com.jqy.server.entity.job.JobEnum;

/**
 * UserDao
 * 
 * @author Simple
 * @date 2013-11-5 下午04:58:31
 * @Description TODO
 */
public interface IJobDao {

  public int register(Job job);
  
  public List<Job> selectAll();

  public Job selectByType(JobEnum jobenum);
}
