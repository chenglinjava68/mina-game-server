package com.jqy.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jqy.server.dao.IPlayerDao;
import com.jqy.server.dao.base.BaseDao;
import com.jqy.server.entity.player.Player;

@Repository
public class IPlayerDaoImpl extends BaseDao implements IPlayerDao {

  @Override
  public int register(Player player) {
    return create("Mapper.Player.create", player);
  }

  @Override
  public Player selectByNickName(String nickName) {
    SqlSession session=getSessionFactory().openSession();
    try {
      return session.selectOne("Mapper.Player.selectByNickName", nickName);
    } finally {
      session.close();
    }
  }

  @Override
  public List<Player> selectAll() {
    SqlSession session=getSessionFactory().openSession();
    try {
      return session.selectList("Mapper.Player.selectAll");
    } finally {
      session.close();
    }
  }

  @Override
  public Player selectById(int id) {
    SqlSession session=getSessionFactory().openSession();
    try {
      return session.selectOne("Mapper.Player.selectById", id);
    } finally {
      session.close();
    }
  }
}
