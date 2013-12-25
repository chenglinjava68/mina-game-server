package com.jqy.server.controller;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jqy.server.entity.user.User;

/**
 * demo
 * 
 * @author Simple
 * @date 2013-12-17 上午11:36:42
 * @Description TODO
 */
@Controller
@RequestMapping(value="/demo")
public class DemoController {

  // @RequestParam
  // value：参数名字，即入参的请求参数名字，如username表示请求的参数区中的名字为username的参数的值将传入；
  // required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报404错误码；
  // defaultValue：默认值，表示如果请求中没有同名参数时的默认值，默认值可以是SpEL表达式，如“#{systemProperties['java.vm.version']}”。
  //
  @RequestMapping(value="/requestparam1", method=RequestMethod.GET)
  @ResponseBody
  public String requestparam1(@RequestParam String str) {// 必须有str参数,否则404
    System.out.println("str=" + str);
    return null;
  }

  @RequestMapping(value="/requestparam2", method=RequestMethod.GET)
  @ResponseBody
  public String requestparam2(@RequestParam("str") String str, @RequestParam("intt") int intt) {// 2个参数必须有,而且类型必须一致,否则404
    System.out.println("str=" + str);
    System.out.println("intt=" + intt);
    return null;
  }

  @RequestMapping(value="/requestparam3", method=RequestMethod.GET)
  @ResponseBody
  public String requestparam3(@RequestParam(value="str", required=true, defaultValue="") String str,
    @RequestParam(value="intt", required=false, defaultValue="0") int intt) {// 2个参数可有可无,如果没有就按照默认值
    System.out.println("str=" + str);
    System.out.println("intt=" + intt);
    return null;
  }

  @RequestMapping(value="/requestparam4", method=RequestMethod.GET)
  @ResponseBody
  public String requestparam4(@RequestParam(value="str") String[] str) {
    System.out.println("str=" + str.toString());
    return null;
  }

  // @PathVariable
  @RequestMapping(value="/requestparam5/{str}", method=RequestMethod.GET)
  @ResponseBody
  public String requestparam5(@PathVariable(value="str") String str) {
    System.out.println("str=" + str.toString());
    return null;
  }

  // @CookieValue用于将请求的Cookie数据映射到功能处理方法的参数上。
  // 如下配置将自动将JSESSIONID值入参到sessionId参数上，defaultValue表示Cookie中没有JSESSIONID时默认为空。
  @RequestMapping(value="/cookie1", method=RequestMethod.GET)
  @ResponseBody
  public String cookie1(@CookieValue(value="JSESSIONID", defaultValue="") String sessionId) {
    System.out.println("sessionId=" + sessionId);
    return null;
  }

  @RequestMapping(value="/cookie2", method=RequestMethod.GET)
  @ResponseBody
  public String cookie2(@CookieValue(value="JSESSIONID", defaultValue="") Cookie sessionId) {
    System.out.println("sessionId=" + sessionId);
    return null;
  }

  // 如请求参数包含?username=zhang&password=123&workInfo.city=bj自动绑定到user中的workInfo属性的city属性中
  // @ModelAttribute
  @RequestMapping(value="/modelAttribute1", method=RequestMethod.GET)
  @ResponseBody
  public String modelAttribute1(@ModelAttribute("user") User user) {
    System.out.println(user.toString());
    return null;
  }
}
