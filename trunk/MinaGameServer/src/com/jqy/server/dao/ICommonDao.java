package com.jqy.server.dao;

import java.util.List;

/**
 * 公共接口
 * @author Simple
 * @date 2013-11-5 下午04:56:58
 * @Description TODO
 */
public interface ICommonDao {

  /**
   * 增加
   * @param mapperName
   * @param obj
   * @return
   */
  public int add(String mapperName, Object obj);

  /**
   * 删除
   * @param mapperName
   * @param id
   */
  public void delete(String mapperName, int id);

  /**
   * 更新
   * @param mapperName
   * @param obj
   */
  public void update(String mapperName, Object obj);

  /**
   * 获得所有
   * @param mapperName
   * @return
   */
  public List<Object> selectAll(String mapperName);
}
