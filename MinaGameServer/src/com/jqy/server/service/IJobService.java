package com.jqy.server.service;

import com.jqy.server.entity.job.Job;
import com.jqy.server.entity.job.JobEnum;

public interface IJobService {

  public boolean create(Job job);

  public Job selectByType(JobEnum jobenum);
}
