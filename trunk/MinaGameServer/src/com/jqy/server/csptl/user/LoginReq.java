package com.jqy.server.csptl.user;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsReqProtocol;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.user.User;
import com.jqy.server.service.IOnlineService;
import com.jqy.server.service.IUserService;

/**
 * 登陆 请求协议
 * 
 * 此类协议都要使用原型模式
 * 
 * @author Simple
 * @date 2013-9-27 上午11:01:24
 * @Description TODO
 */
@Component
@Scope("prototype")
public class LoginReq extends AbsReqProtocol {

  private Logger log=Logger.getLogger(this.getClass());

  private static final byte TYPE=Constant.REQ;

  private static final short ID=0x0003;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  @Resource
  private IUserService userService;

  @Resource
  private IOnlineService onlineService;

  private String username;

  private String password;

  @Override
  public void decode(MyBuffer buf) {
    username=buf.getPrefixedString();
    password=buf.getPrefixedString();
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    log.debug(String.format("login execute,username=%s,password=%s", username, password));
    User user=userService.login(username, password);
    if(null != user) {
      onlineService.setConnectedUser(session, user);
    }
    return new LoginResp(null != user ? Constant.SUCCESS : Constant.FAILD);
  }
}
