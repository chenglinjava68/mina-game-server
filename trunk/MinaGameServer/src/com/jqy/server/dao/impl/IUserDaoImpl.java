package com.jqy.server.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jqy.server.dao.IUserDao;
import com.jqy.server.dao.base.BaseDao;
import com.jqy.server.entity.user.User;

@Repository
public class IUserDaoImpl extends BaseDao implements IUserDao {

  @Override
  public User login(String username, String password) {
    SqlSession session=getSessionFactory().openSession();
    try {
      Map<String, String> params=new HashMap<String, String>();
      params.put("username", username);
      params.put("password", password);
      return session.selectOne("Mapper.User.login", params);
    } finally {
      session.close();
    }
  }

  @Override
  public User selectByUsername(String username) {
    SqlSession session=getSessionFactory().openSession();
    try {
      return session.selectOne("Mapper.User.selectByUsername", username);
    } finally {
      session.close();
    }
  }

  @Override
  public int create(User user) {
    return create("Mapper.User.create", user);
  }

  @Override
  public void update(User user) {
    update("Mapper.User.update", user);
  }
}
