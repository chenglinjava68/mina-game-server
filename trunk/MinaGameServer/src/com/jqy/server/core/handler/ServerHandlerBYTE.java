package com.jqy.server.core.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsReqProtocol;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.player.Player;
import com.jqy.server.entity.user.User;
import com.jqy.server.service.IOnlineService;
import com.jqy.util.spring.BeanUtil;

@Component
public class ServerHandlerBYTE extends IoHandlerAdapter implements InitializingBean {

  private Logger log=Logger.getLogger(this.getClass());

  @Resource
  private IOnlineService onlineService;

  @Resource
  private List<AbsReqProtocol> reqProtocols;

  private Map<Short, String> reqProtocolsMap=new HashMap<Short, String>();

  @Override
  public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    // TODO Auto-generated method stub
    super.exceptionCaught(session, cause);
  }

  @Override
  public void messageReceived(IoSession session, Object message) throws Exception {
    log.debug("messageReceived");
    // 解析协议
    MyBuffer myBuf=(MyBuffer)message;
    byte ptlType=myBuf.get();
    short ptlId=myBuf.getShort();
    int bodyLength=myBuf.getInt();
    log.debug(String.format("TYPE=%s,ID=%s,BODY_LENGTH=%s", ptlType, ptlId, bodyLength));
    // 判断协议
    if(ptlType == Constant.REQ) {
      if(reqProtocolsMap.containsKey(ptlId)) {
        String protocolName=reqProtocolsMap.get(ptlId);
        // 获取相应协议处理类的bean
        AbsReqProtocol req=BeanUtil.makeNewReqProtocolHandler(protocolName);
        if(req != null) {
          setUserAndPlayer(session, req);
          // 真实数据
          MyBuffer bodyBuf=MyBuffer.allocate(bodyLength);
          byte[] bodyData=new byte[bodyLength];
          myBuf.get(bodyData);
          bodyBuf.put(bodyData);
          bodyBuf.flip();
          // 解码真实数据
          req.decode(bodyBuf);
          // 执行业务逻辑
          AbsRespProtocol resp=req.execute(session, req);
          if(resp != null) {
            // 响应给客户端的数据
            MyBuffer respBuf=MyBuffer.allocate(1024);
            respBuf.put(resp.getProtocolType());
            respBuf.putShort(resp.getProtocolId());
            resp.encode(respBuf);
            respBuf.flip();
            session.write(respBuf);
          }
        } else {
          log.debug(String.format("NAME=%s的协议不存在", protocolName));
        }
      } else {
        log.debug(String.format("ID=%s的协议不存在", ptlId));
      }
    } else {
      log.debug("非请求类型协议");
    }
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
    log.debug("messageSent" + message.toString());
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
        log.debug(className + "类的ProtocolId已存在!");
      } else {
        reqProtocolsMap.put(reqPtl.getProtocolId(), className);
        log.debug(String.format("注册协议[%s] \t 编号[%s]", className, reqPtl.getProtocolId()));
      }
    }
  }
}
