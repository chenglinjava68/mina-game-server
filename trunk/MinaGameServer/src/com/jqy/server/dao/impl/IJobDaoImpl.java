package com.jqy.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jqy.server.dao.IJobDao;
import com.jqy.server.dao.base.BaseDao;
import com.jqy.server.entity.job.Job;
import com.jqy.server.entity.job.JobEnum;

@Repository
public class IJobDaoImpl extends BaseDao implements IJobDao {

  @Override
  public int register(Job job) {
    return create("Mapper.Job.create", job);
  }

  @Override
  public Job selectByType(JobEnum jobenum) {
    SqlSession session=getSessionFactory().openSession();
    try {
      int type=jobenum.getCode();
      return session.selectOne("Mapper.Job.selectByType", type);
    } finally {
      session.close();
    }
  }

  @Override
  public List<Job> selectAll() {
    SqlSession session=getSessionFactory().openSession();
    try {
      return session.selectList("Mapper.Job.selectAll");
    } finally {
      session.close();
    }
  }
}
