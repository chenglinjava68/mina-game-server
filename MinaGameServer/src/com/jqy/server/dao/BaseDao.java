package com.jqy.server.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

/**
 * 基础DAO
 * 
 * @author Simple
 * @date 2013-7-23 下午02:17:14
 * @Description TODO
 */
@Repository
public abstract class BaseDao implements ICommonDao {

  @Resource(name="sqlSessionFactory")
  protected SqlSessionFactory sessionFactory;

  public SqlSessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SqlSessionFactory sessionFactory) {
    this.sessionFactory=sessionFactory;
  }

  public int add(String mapperName, Object obj) {
    SqlSession session=getSessionFactory().openSession();
    try {
      return session.insert(mapperName, obj);
    } finally {
      session.close();
    }
  }

  public void delete(String mapperName, int id) {
    SqlSession session=getSessionFactory().openSession();
    try {
      session.delete(mapperName, id);
    } finally {
      session.close();
    }
  }

  public List<Object> selectAll(String mapperName) {
    SqlSession session=getSessionFactory().openSession();
    try {
      return session.selectList(mapperName);
    } finally {
      session.close();
    }
  }

  public void update(String mapperName, Object obj) {
    SqlSession session=getSessionFactory().openSession();
    try {
      session.update(mapperName, obj);
    } finally {
      session.close();
    }
  }
}