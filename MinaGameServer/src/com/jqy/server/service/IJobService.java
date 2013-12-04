package com.jqy.server.service;

import com.jqy.server.entity.job.Job;
import com.jqy.server.entity.job.JobEnum;

public interface IJobService {

  public boolean register(Job job);

  public Job selectByType(JobEnum jobenum);
}
