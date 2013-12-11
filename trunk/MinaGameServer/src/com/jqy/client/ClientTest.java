package com.jqy.client;

import static org.junit.Assert.*;

import net.sf.json.JSONObject;

import org.apache.mina.core.future.ConnectFuture;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ClientTest {

  public Client client=new Client();

  public ConnectFuture cf;

  public String username;

  public String password;

  public String email;

  public String nickName;

  public boolean sex;

  public int jobId;

  public int roleIndex;

  @Before
  public void setUp() throws Exception {
    cf=client.getCF();
    username="jiangqianyuan";
    password="jiangqianyuan";
    email="jiangqianyuan@qq.com";
    nickName="jqy";
    sex=true;
    jobId=1;
    roleIndex=0;
  }

  @Ignore
  public void testClientStart() {
    fail("Not yet implemented");
  }

  @Test
  public void testRegUser() {
    JSONObject regUserJson=client.regUser(0x0001, username, password, email);
    client.sendData(cf, regUserJson);
  }

  @Test
  public void testLogin() {
    JSONObject loginJson=client.login(0x0003, username, password);
    client.sendData(cf, loginJson);
  }

  @Test
  public void testRegPlayer() {
    JSONObject regPlayerJson=client.regPlayer(0x0005, nickName, sex, jobId);
    client.sendData(cf, regPlayerJson);
  }

  @Test
  public void testStartGame() {
    JSONObject startJson=client.startGame(0x0011, roleIndex);
    client.sendData(cf, startJson);
  }

  @Test
  public void testGetAllPlayer() {
    JSONObject allPlayerJson=client.getAllPlayer(0x0007);
    client.sendData(cf, allPlayerJson);
  }

  @After
  public void setAfter() {
    client.end(cf);
  }
}
