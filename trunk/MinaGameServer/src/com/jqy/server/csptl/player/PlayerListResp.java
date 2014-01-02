package com.jqy.server.csptl.player;

import java.util.List;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.player.Player;

public class PlayerListResp extends AbsRespProtocol {

  private static final byte TYPE=Constant.RESP;

  private static final short ID=0x0008;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  private byte result;

  private List<Player> players;

  public PlayerListResp(byte result, List<Player> players) {
    this.result=result;
    this.players=players;
  }

  @Override
  public void encode(MyBuffer buf) {
    buf.put(result);
    if(result == Constant.SUCCESS) {
      buf.putInt(players.size());
      for(Player p: players) {
        buf.putString(p.getNickName());
        buf.putInt(p.getLevel());
      }
    }
  }
}
