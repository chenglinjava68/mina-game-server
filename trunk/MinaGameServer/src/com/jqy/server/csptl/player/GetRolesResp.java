package com.jqy.server.csptl.player;

import java.util.List;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.player.Player;

public class GetRolesResp extends AbsRespProtocol {

  private static final byte TYPE=Constant.RESP;

  private static final short ID=0x0016;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  private byte result;

  private List<Player> list;

  public GetRolesResp() {
  }

  public GetRolesResp(byte result) {
    this.result=result;
  }

  public GetRolesResp(byte result, List<Player> list) {
    this.result=result;
    this.list=list;
  }

  @Override
  public void encode(MyBuffer buf) {
    buf.put(result);
    if(result == Constant.SUCCESS) {
      buf.putInt(list.size());
      for(Player p: list) {
        buf.put((byte)(p.isSex() == true ? 1 : 0));
        buf.putInt(p.getId());
        buf.putInt(p.getLevel());
        buf.putPrefixedString(p.getNickName());
      }
    }
  }
}
