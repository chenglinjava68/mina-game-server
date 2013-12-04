package com.jqy.server.csptl.user;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jqy.server.common.Constant;
import com.jqy.server.core.protocol.AbsReqProtocol;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.user.User;
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

  private String username;

  private String password;

  @Override
  public void decode(JSONObject data) {
    username=data.getString("username");
    password=data.getString("password");
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    log.debug("username=" + username + ",password=" + password);
    User user=userService.login(username, password);
    if(null != user) {
      session.setAttribute("user", user);
    }
    return new LoginResp(null != user ? Constant.SUCCESS : Constant.FAILD);
  }
}
