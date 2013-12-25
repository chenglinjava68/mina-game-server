package com.jqy.server.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jqy.server.entity.player.Player;
import com.jqy.server.entity.user.User;
import com.jqy.server.service.IOnlineService;

@Controller
@RequestMapping(value="/manager")
public class ManagerController {

  @Resource
  private IOnlineService onlineService;

  @RequestMapping(value="/getServerStatus", method=RequestMethod.GET)
  public String getServerStatus(HttpServletRequest req) {
    Map<IoSession, User> connections=onlineService.getConnectedUsers();
    Map<IoSession, Player> onlinePlayers=onlineService.getOnlinePlayers();
    req.setAttribute("onlinePlayers", onlinePlayers);
    req.setAttribute("connections", connections);
    return "serverStatus";
  }
}
