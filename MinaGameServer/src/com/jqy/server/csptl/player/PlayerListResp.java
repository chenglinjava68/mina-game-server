package com.jqy.server.csptl.player;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jqy.server.common.Constant;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.player.Player;
import com.jqy.util.json.JsonUtil;

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

  private int result;

  private List<Player> players;

  public PlayerListResp(int result, List<Player> players) {
    this.result=result;
    this.players=players;
  }

  @Override
  public void encode(JSONObject data) {
    data.put("result", result);
    if(result == Constant.SUCCESS) {
      data.put("size", players.size());
      JSONArray jsonArray=new JSONArray();
      for(Player p: players) {
        jsonArray.add(JSONObject.fromObject(p, JsonUtil.dateFormatAndIgnoreCycleConfig()));
      }
      data.put("list", jsonArray);
    }
  }
}
