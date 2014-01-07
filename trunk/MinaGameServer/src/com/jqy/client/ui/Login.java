package com.jqy.client.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.jqy.client.MyClient;
import com.jqy.client.MyUser;
import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.util.StringUtil;

public class Login extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID=-4410349802082685121L;

  private Logger log=Logger.getLogger(this.getClass());

  private JLabel label_username;

  private JTextField field_username;

  private JLabel label_password;

  private JPasswordField field_password;

  private JButton button_login;

  private JButton button_cancel;

  private JPanel panel1;

  private JPanel panel2;

  private JPanel panel3;

  private MyClient client;

  public void init() {
    label_username=new JLabel("Account :");
    label_password=new JLabel("Password:");
    field_username=new JTextField(12);
    field_password=new JPasswordField(12);
    button_login=new JButton("Login");
    button_cancel=new JButton("Cancel");
    panel1=new JPanel();
    panel2=new JPanel();
    panel3=new JPanel();
    // 设置布局
    this.setLayout(new GridLayout(3, 1));
    panel1.add(label_username);
    panel1.add(field_username);
    panel2.add(label_password);
    panel2.add(field_password);
    panel3.add(button_login);
    panel3.add(button_cancel);
    this.add(panel1);
    this.add(panel2);
    this.add(panel3);
    this.setSize(300, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setTitle("LOGIN");
    setAction();
  }

  public void setAction() {
    button_login.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String username=field_username.getText();
        String password=String.valueOf(field_password.getPassword());
        if(StringUtil.isNull(username) || StringUtil.isNull(password)) {
          JOptionPane.showMessageDialog(panel1, String.format("Account or Password is null!"));
        } else {
          JOptionPane.showMessageDialog(panel1, String.format("账号=%s,密码=%s", username, password));
        }
        client=new MyClient();
        if(client.connection()) {
          MyBuffer buf=reqLogin((short)0x0003, username, password);
          if(!client.sendMessage(buf)) {
            log.error("发送信息失败!");
            return;
          } else {
            log.debug("发送信息成功!");
            Object message=client.readMessage();
            if(null != message) {
              // 解析数据
              MyBuffer bodyData=DataUtil.getBodyData(message);
              byte result=bodyData.get();
              log.debug(String.format("RESULT=%s", result));
              if(result != 0) {
                JOptionPane.showMessageDialog(panel1, String.format("LOGIN SUCCESS!"));
                saveUser(username, password);
                dispose();
                new SelectRole(client).init();
              } else {
                JOptionPane.showMessageDialog(panel1, String.format("LOGIN FAILD!"));
              }
            } else {
              log.debug("读到的信息为null");
            }
          }
        } else {
          JOptionPane.showMessageDialog(panel1, String.format("connect to server faild!"));
        }
      }

      private void saveUser(String username, String password) {
        MyUser user=new MyUser();
        user.setUsername(username);
        user.setPassword(password);
        client.setUser(user);
      }
    });
    button_cancel.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
  }

  public MyBuffer reqLogin(short ptlId, String username, String password) {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort(ptlId);
    buf.putPrefixedString(username);
    buf.putPrefixedString(password);
    buf.flip();
    return buf;
  }
}
