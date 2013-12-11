package com.jqy.server.csptl.player;

import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jqy.server.common.Constant;
import com.jqy.server.core.protocol.AbsReqProtocol;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.job.Job;
import com.jqy.server.entity.job.JobEnum;
import com.jqy.server.entity.player.Player;
import com.jqy.server.entity.user.User;
import com.jqy.server.service.IJobService;
import com.jqy.server.service.IPlayerService;
import com.jqy.server.service.IUserService;

/**
 * 注册玩家 请求协议
 * 
 * 此类协议都要使用原型模式
 * 
 * @author Simple
 * @date 2013-9-27 上午11:01:24
 * @Description TODO
 */
@Component
@Scope("prototype")
public class RegPlayerReq extends AbsReqProtocol {

  private Logger log=Logger.getLogger(this.getClass());

  private static final byte TYPE=Constant.REQ;

  private static final short ID=0x0005;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  @Resource
  private IPlayerService playerService;

  @SuppressWarnings("unused")
  @Resource
  private IUserService userService;

  @Resource
  private IJobService jobService;

  private String nickName;

  private boolean sex;

  private int jobId;

  @Override
  public void decode(JSONObject data) {
    nickName=data.getString("nickName");
    sex=data.getBoolean("sex");
    jobId=data.getInt("jobId");
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    log.debug(String.format("regPlayer execute,nickName=%s,sex=%s,jobId=%s", nickName, sex, jobId));
    Player player=playerService.selectByNickName(nickName);
    if(null != player) {
      log.debug(String.format("nickName=%s exist!", nickName));
      return new RegPlayerResp(2);// 用户名已存在
    } else {
      player=new Player();
      player.setRegDate(new Date());
      player.setNickName(nickName);
      player.setSex(sex);
      // user
      User user=getUser();
      if(null != user) {
        player.setUser(user);
      } else {
        log.error(String.format("严重错误"));
        return new RegPlayerResp(9999);// 严重错误
      }
      // job
      Job job=jobService.selectByType(JobEnum.getByCode(jobId));
      if(null != job) {
        player.setJob(job);
      } else {
        log.debug(String.format("Job inited faild!can't found jobId=%s", jobId));
        return new RegPlayerResp(3);// 职业不存在
      }
      if(playerService.create(player)) {
        user.getPlayers().add(player);
        // user.getPlayers().add(player);
        // userService.save(user);
        return new RegPlayerResp(1);// 注册成功
      }
    }
    return new RegPlayerResp(0);// 失败
  }
}
