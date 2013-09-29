package com.jqy.server.dao;

import java.util.List;

public interface ICommonDao {

  public int add(String mapperName, Object obj);

  public void delete(String mapperName, int id);

  public void update(String mapperName, Object obj);

  public List<Object> selectAll(String mapperName);
}
