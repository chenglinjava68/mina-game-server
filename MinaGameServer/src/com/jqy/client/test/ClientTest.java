package com.jqy.client.test;

import net.sf.json.JSONObject;

import org.apache.mina.core.future.ConnectFuture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jqy.client.Client;

public class ClientTest implements Runnable {

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
    int random=(int)(Math.random() * 100);
    System.out.println("start");
    cf=client.getCF();
    username="jiangqianyuan" + random;
    password="jiangqianyuan" + random;
    email="jiangqianyuan2@qq.com";
    nickName="jqy" + random;
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
    while(true) {
      try {
        Thread.sleep(60 * 1000);
        testHearbeat();
        testGetAllPlayer();
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
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

  public void testHearbeat() {
    JSONObject hearbeat=client.hearbeat(0x1001);
    client.sendData(cf, hearbeat);
  }

  @After
  public void setAfter() {
    System.out.println("end");
    client.end(cf);
  }

  public static void main(String[] args) {
    for(int i=0; i < 1; i++) {
      Thread t=new Thread(new ClientTest());
      t.start();
    }
  }

  @Override
  public void run() {
    try {
      setUp();
    } catch(Exception e) {
      e.printStackTrace();
    }
    test();
    setAfter();
  }
}
