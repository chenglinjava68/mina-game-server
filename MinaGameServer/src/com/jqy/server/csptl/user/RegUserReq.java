package com.jqy.server.csptl.user;

import java.util.Date;

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
import com.jqy.server.service.IUserService;

/**
 * 注册用户 请求协议
 * 
 * 此类协议都要使用原型模式
 * 
 * @author Simple
 * @date 2013-9-27 上午11:01:24
 * @Description TODO
 */
@Component
@Scope("prototype")
public class RegUserReq extends AbsReqProtocol {

  private Logger log=Logger.getLogger(this.getClass());

  private static final byte TYPE=Constant.REQ;

  private static final short ID=0x0001;

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

  private String email;

  @Override
  public void decode(MyBuffer buf) {
    username=buf.getPrefixedString();
    password=buf.getPrefixedString();
    email=buf.getPrefixedString();
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    log.debug(String.format("regUser execute,username=%s,password=%s,email=%s", username, password, email));
    User user=userService.selectByUsername(username);
    if(null == user) {
      user=new User();
      user.setRegDate(new Date());
      user.setUsername(username);
      user.setPassword(password);
      user.setEmail(email);
      userService.create(user);
      return new RegUserResp(Constant.SUCCESS);
    } else {
      return new RegUserResp(Constant.FAILD);
    }
  }
}
