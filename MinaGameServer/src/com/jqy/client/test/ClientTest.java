package com.jqy.client.test;

import net.sf.json.JSONObject;

import org.apache.mina.core.future.ConnectFuture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jqy.client.Client;

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
    System.out.println("start");
    cf=client.getCF();
    username="jiangqianyuan2";
    password="jiangqianyuan2";
    email="jiangqianyuan2@qq.com";
    nickName="jqy2";
    sex=true;
    jobId=2;
    roleIndex=0;
  }

  @Test
  public void test() {
    testRegUser();
    testLogin();
    testRegPlayer();
    testStartGame();
    testGetAllPlayer();
  }

  public void testRegUser() {
    JSONObject regUserJson=client.regUser(0x0001, username, password, email);
    client.sendData(cf, regUserJson);
  }

  public void testLogin() {
    JSONObject loginJson=client.login(0x0003, username, password);
    client.sendData(cf, loginJson);
  }

  public void testRegPlayer() {
    JSONObject regPlayerJson=client.regPlayer(0x0005, nickName, sex, jobId);
    client.sendData(cf, regPlayerJson);
  }

  public void testStartGame() {
    JSONObject startJson=client.startGame(0x0011, roleIndex);
    client.sendData(cf, startJson);
  }

  public void testGetAllPlayer() {
    JSONObject allPlayerJson=client.getAllPlayer(0x0007);
    client.sendData(cf, allPlayerJson);
  }

  @After
  public void setAfter() {
    System.out.println("end");
    client.end(cf);
  }
}
