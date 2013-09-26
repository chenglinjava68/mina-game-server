package com.jqy.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class UserController {

  @RequestMapping(value="/login", method=RequestMethod.POST)
  @ResponseBody
  public String login(String username, String passwrod) {
    return null;
  }
}
