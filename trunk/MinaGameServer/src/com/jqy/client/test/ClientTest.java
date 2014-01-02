package com.jqy.client.test;

import org.apache.mina.core.future.ConnectFuture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jqy.client.Client;
import com.jqy.server.core.MyBuffer;

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
    cf=client.connection();
    username="jiangqianyuan" + random;
    password="jiangqianyuan" + random;
    email="jiangqianyuan2@qq.com";
    nickName="jqy" + random;
    sex=true;
    jobId=2;
    roleIndex=0;
  }

  @After
  public void setAfter() {
    System.out.println("end");
    client.end(cf);
  }

  @Test
  public void test() {
//    testRegUser();
    testLogin();
    // testRegPlayer();
    // testStartGame();
    // while(true) {
    // try {
    // Thread.sleep(60 * 1000);
    // testHearbeat();
    // testGetAllPlayer();
    // } catch(InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
  }

  public void testRegUser() {
    MyBuffer buf=client.regUser((short)0x0001, username, password, email);
    client.sendData(cf, buf);
  }

  public void testLogin() {
    MyBuffer buf=client.login((short)0x0003, username, password);
    client.sendData(cf, buf);
  }

  public void testRegPlayer() {
    MyBuffer buf=client.regPlayer((short)0x0005, nickName, sex, jobId);
    client.sendData(cf, buf);
  }

  public void testStartGame() {
    MyBuffer buf=client.startGame((short)0x0011, roleIndex);
    client.sendData(cf, buf);
  }

  public void testGetAllPlayer() {
    MyBuffer buf=client.getAllPlayer((short)0x0007);
    client.sendData(cf, buf);
  }

  public void testHearbeat() {
    MyBuffer buf=client.hearbeat((short)0x1001);
    client.sendData(cf, buf);
  }

  public static void main(String[] args) {
    for(int i=0; i < 100; i++) {
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
