package com.jqy.server.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jqy.server.entity.User;

@Repository
public class UserDao extends BaseDao {

  public User login(String username, String password) {
    SqlSession session=getSessionFactory().openSession();
    try {
      Map<String, String> params=new HashMap<String, String>();
      params.put("username", username);
      params.put("password", password);
      return session.selectOne("Mapper_User.login", params);
    } finally {
      session.close();
    }
  }
}
