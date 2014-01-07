package com.jqy.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.jqy.client.ClientHandler;
import com.jqy.client.MyClient;
import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;

public class Chat extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID=-884789438209749172L;

  private Logger log=Logger.getLogger(this.getClass());

  private JPanel panel_head;

  private JPanel panel_left;

  private JPanel panel_center;

  @SuppressWarnings("unused")
  private JPanel panel_right;

  private JPanel panel_buttom;

  private JLabel label_head;

  private JTextArea area_playerList;

  private JTextArea area_message;

  private JLabel label_sendMessage;

  private JTextField field_sendMessage;

  private JButton button_sendMessage;

  private MyClient client;

  public Chat(MyClient client) {
    this.client=client;
  }

  /**
   * 
   */
  public void init() {
    registerToHandler();
    this.setLayout(new BorderLayout());
    this.setSize(600, 600);
    // 头
    panel_head=new JPanel();
    panel_head.setBackground(Color.BLACK);
    panel_head.setPreferredSize(new Dimension(600, 25));
    label_head=
      new JLabel(String.format("Chat [Account=%s,Nickname=%s]", client.getUser().getUsername(), client.getPlayer().getNickName()));
    label_head.setForeground(Color.WHITE);
    panel_head.add(label_head);
    // 左
    panel_left=new JPanel();
    panel_left.setBackground(Color.PINK);
    panel_left.setPreferredSize(new Dimension(100, 475));
    area_playerList=new JTextArea(28, 8);
    panel_left.add(area_playerList);
    // 中
    panel_center=new JPanel();
    panel_center.setBackground(Color.CYAN);
    panel_center.setPreferredSize(new Dimension(500, 475));
    area_message=new JTextArea(30, 40);
    area_message.setEditable(false);
    panel_center.add(area_message);
    // 右
    // panel_right=new JPanel();
    // panel_right.setBackground(Color.GRAY);
    // panel_right.setPreferredSize(new Dimension(100, 475));
    // 下
    panel_buttom=new JPanel();
    panel_buttom.setBackground(Color.GREEN);
    label_sendMessage=new JLabel("Enter message:");
    field_sendMessage=new JTextField(30);
    button_sendMessage=new JButton("Send");
    panel_buttom.add(label_sendMessage);
    panel_buttom.add(field_sendMessage);
    panel_buttom.add(button_sendMessage);
    panel_buttom.setPreferredSize(new Dimension(600, 50));
    //
    this.add(panel_head, BorderLayout.PAGE_START);
    this.add(panel_left, BorderLayout.LINE_START);
    this.add(panel_center, BorderLayout.CENTER);
    // this.add(panel_right, BorderLayout.LINE_END);
    this.add(panel_buttom, BorderLayout.PAGE_END);
    // 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setTitle("WORLD");
    setAction();
  }

  private void registerToHandler() {
    ClientHandler ch=(ClientHandler)client.getConnector().getHandler();
    ch.registeComponent("Chat", this);
  }

  private void setAction() {
    button_sendMessage.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String message=field_sendMessage.getText();
        MyBuffer buf=reqSendMessage((short)0x0013, Constant.CHAT_COMMON, message);
        if(client.sendMessage(buf)) {
          Object o=client.readMessage();
          if(null != o) {
            // 解析数据
            MyBuffer bodyData=DataUtil.getBodyData(o);
            byte result=bodyData.get();
            log.debug(String.format("RESULT=%s", result));
            switch(result) {
              case 0:
                break;
              case 1:
                area_message.setText(area_message.getText() + "\r\n" + message);
                break;
            }
          }
        }
      }
    });
  }

  public MyBuffer reqSendMessage(short ptlId, byte type, String message) {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort(ptlId);
    buf.put(type);
    buf.putPrefixedString(message);
    buf.flip();
    return buf;
  }

  public void setClient(MyClient client) {
    this.client=client;
  }

  public MyClient getClient() {
    return client;
  }

  public static void main(String[] args) {
    new Chat(null).init();
  }

  public void fromServerMessage(String message) {
    area_message.setText(area_message.getText() + "\r\n" + message);
  }
}
