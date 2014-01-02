package com.jqy.server.core.protocol;

import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

import com.jqy.server.core.MyBuffer;
import com.jqy.server.entity.player.Player;
import com.jqy.server.entity.user.User;

public abstract class AbsReqProtocol implements IProtocol {

  public short getProtocolId() {
    return 0;// 每个继承类重写该方法
  }

  public byte getProtocolType() {
    return 0;// 每个继承类重写该方法
  }

  // 解码
  public void decode(JSONObject data) {
    // 使用JSON模式的话，此方法改为abstract
  }

  public abstract void decode(MyBuffer buf);

  // 执行
  public abstract AbsRespProtocol execute(IoSession session, AbsReqProtocol req);

  private User user;

  private Player player;

  public Player getPlayer() {
    return player;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user=user;
  }

  public void setPlayer(Player player) {
    this.player=player;
  }
}
