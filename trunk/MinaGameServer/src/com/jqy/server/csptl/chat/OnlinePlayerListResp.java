package com.jqy.server.csptl.chat;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.player.Player;

public class OnlinePlayerListResp extends AbsRespProtocol {

  private static final byte TYPE=Constant.RESP;

  private static final short ID=0x0020;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  private byte result;
  
  private Map<IoSession,Player> players;

  public OnlinePlayerListResp() {
  }

  public OnlinePlayerListResp(byte result,Map<IoSession,Player> players) {
    this.result=result;
    this.players=players;
  }

  @Override
  public void encode(MyBuffer buf) {
    buf.put(result);
    if(result==Constant.SUCCESS){
      buf.putInt(players.size());
      for(Player p:players.values()){
        buf.putInt(p.getId());
        buf.putInt(p.getLevel());
        buf.putPrefixedString(p.getNickName());
      }
    }
  }
}
