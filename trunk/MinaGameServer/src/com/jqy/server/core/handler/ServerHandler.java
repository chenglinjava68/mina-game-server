package com.jqy.server.core.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.jqy.server.common.Constant;
import com.jqy.server.core.protocol.AbsReqProtocol;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.player.Player;
import com.jqy.server.entity.user.User;
import com.jqy.server.service.IOnlineService;
import com.jqy.util.spring.BeanUtil;

@Component
public class ServerHandler extends IoHandlerAdapter implements InitializingBean {

  private Logger log=Logger.getLogger(this.getClass());

  @Resource
  private IOnlineService onlineService;

  @Resource
  private List<AbsReqProtocol> reqProtocols;

  private Map<Short, String> reqProtocolsMap=new HashMap<Short, String>();

  @Override
  public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    log.debug("exceptionCaught=" + cause);
  }

  @Override
  public void messageReceived(IoSession session, Object message) throws Exception {
    log.debug("messageReceived");
    IoBuffer buffer=(IoBuffer)message;
    byte[] data=buffer.array();
    String jsonString=new String(data, "utf-8");
    log.debug("JSON解析数据:" + jsonString);
    JSONObject jsonObject=JSONObject.fromObject(jsonString);
    int id=jsonObject.getInt("id");
    int type=jsonObject.getInt("type");
    log.debug("ID=" + id + ",TYPE=" + type);
    if(type == Constant.REQ) {
      if(reqProtocolsMap.containsKey((short)id)) {
        String protocolName=reqProtocolsMap.get((short)id);
        // 获取相应协议处理类的bean
        AbsReqProtocol req=BeanUtil.makeNewReqProtocolHandler(protocolName);
        if(req != null) {
          setUserAndPlayer(session, req);
          JSONObject reqData=jsonObject.getJSONObject("data");
          req.decode(reqData);
          AbsRespProtocol resp=req.execute(session, req);
          JSONObject respData=new JSONObject();
          respData.put("type", resp.getProtocolType());
          respData.put("id", resp.getProtocolId());
          resp.encode(respData);
          session.write(respData);
        } else {
          log.debug("NAME=" + protocolName + "的协议不存在");
        }
      } else {
        log.debug("ID=" + id + "的协议不存在");
      }
    } else {
      log.debug("非请求类型协议");
    }
    // JSONObject jsonObject2=jsonObject.getJSONObject("data");
  }

  private void setUserAndPlayer(IoSession session, AbsReqProtocol req) {
    User user=onlineService.getUserByIoSession(session);
    if(null != user) {
      req.setUser(user);
      Player player=onlineService.getPlayerByIoSession(session);
      if(null != player) {
        req.setPlayer(player);
      } else {
        log.error(String.format("you can't online"));
      }
    } else {
      log.error(String.format("you can't login"));
    }
  }

  @Override
  public void messageSent(IoSession session, Object message) throws Exception {
    log.debug("messageSent");
  }

  @Override
  public void sessionClosed(IoSession session) throws Exception {
    log.debug("sessionClosed");
  }

  @Override
  public void sessionCreated(IoSession session) throws Exception {
    log.debug("sessionCreated");
  }

  @Override
  public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    log.debug("sessionIdle");
  }

  @Override
  public void sessionOpened(IoSession session) throws Exception {
    log.debug("sessionOpened");
  }

  public void afterPropertiesSet() throws Exception {
    registerProtocols();
  }

  /**
   * 注册协议
   */
  private void registerProtocols() {
    for(AbsReqProtocol reqPtl: reqProtocols) {
      // 获取协议处理类的类名
      char[] name=reqPtl.getClass().getSimpleName().toCharArray();
      name[0]=Character.toLowerCase(name[0]);
      String className=String.valueOf(name);
      // 把协议号+类名放入map中
      if(reqProtocolsMap.containsKey(reqPtl.getProtocolId())) {
        log.debug(name.toString() + "类的ProtocolId已存在!");
      } else {
        reqProtocolsMap.put(reqPtl.getProtocolId(), className);
        log.debug(String.format("注册协议[%s] \t 编号[%s]", className, reqPtl.getProtocolId()));
      }
    }
  }
}
