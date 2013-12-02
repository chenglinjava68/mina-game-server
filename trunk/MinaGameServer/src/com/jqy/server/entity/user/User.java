package com.jqy.server.entity.user;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.jqy.server.common.Constant;
import com.jqy.server.entity.player.Player;

/**
 * 用户
 * 
 * @author Simple
 * @date 2013-9-23 下午01:53:10
 * @Description TODO
 */
public class User {

  private int id;

  private String username;// 用户名

  private String password;// 密码

  private String email;// 邮箱

  private boolean valid=Constant.USER_VALID;// 状态

  private Date regDate;// 注册日期

  private Date modDate;// 修改日期

  private List<Player> players=new LinkedList<Player>();// 玩家列表

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id=id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password=password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email=email;
  }

  public Date getRegDate() {
    return regDate;
  }

  public void setRegDate(Date regDate) {
    this.regDate=regDate;
  }

  public Date getModDate() {
    return modDate;
  }

  public void setModDate(Date modDate) {
    this.modDate=modDate;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players=players;
  }

  public void setValid(boolean valid) {
    this.valid=valid;
  }

  public boolean isValid() {
    return valid;
  }
}
