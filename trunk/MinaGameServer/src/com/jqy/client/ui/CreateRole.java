package com.jqy.client.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.jqy.client.MyClient;
import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;

public class CreateRole extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID=179993448386010936L;

  private Logger log=Logger.getLogger(this.getClass());

  private JLabel label_job1;

  private JLabel label_job2;

  private JLabel label_job3;

  private JLabel label_job4;

  private JRadioButton radio_job1;

  private JRadioButton radio_job2;

  private JRadioButton radio_job3;

  private JRadioButton radio_job4;

  private JLabel label_nickName;

  private JTextField field_nickName;

  private JLabel label_sex;

  private JRadioButton radio_sex1;

  private JRadioButton radio_sex2;

  private JButton button_create;

  private JButton button_return;

  private JPanel panel1;

  private JPanel panel2;

  private JPanel panel3;

  private JPanel panel4;

  private JPanel panel5;

  private JPanel panel6;

  private JPanel panel7;

  private MyClient client;

  public CreateRole(MyClient client) {
    this.client=client;
  }

  public void init() {
    label_job1=new JLabel("WARRIOR");
    label_job2=new JLabel("DOCTOR");
    label_job3=new JLabel("MAGICIAN");
    label_job4=new JLabel("ROBBER");
    radio_job1=new JRadioButton();
    radio_job2=new JRadioButton();
    radio_job3=new JRadioButton();
    radio_job4=new JRadioButton();
    label_nickName=new JLabel("Nickname:");
    field_nickName=new JTextField(12);
    label_sex=new JLabel("Sex:");
    radio_sex1=new JRadioButton("MAN");
    radio_sex2=new JRadioButton("WOMEN");
    button_create=new JButton("CREATE ROLE");
    button_return=new JButton("RETURN SELECT ROLE");
    panel1=new JPanel();
    panel2=new JPanel();
    panel3=new JPanel();
    panel4=new JPanel();
    panel5=new JPanel();
    panel6=new JPanel();
    panel7=new JPanel();
    // 设置布局
    this.setLayout(new GridLayout(8, 1));
    panel1.add(label_job1);
    panel1.add(radio_job1);
    panel2.add(label_job2);
    panel2.add(radio_job2);
    panel3.add(label_job3);
    panel3.add(radio_job3);
    panel4.add(label_job4);
    panel4.add(radio_job4);
    ButtonGroup group1=new ButtonGroup();
    group1.add(radio_job1);
    group1.add(radio_job2);
    group1.add(radio_job3);
    group1.add(radio_job4);
    panel5.add(label_nickName);
    panel5.add(field_nickName);
    panel6.add(label_sex);
    panel6.add(radio_sex1);
    panel6.add(radio_sex2);
    ButtonGroup group2=new ButtonGroup();
    group2.add(radio_sex1);
    group2.add(radio_sex2);
    panel7.add(button_create);
    panel7.add(button_return);
    this.add(panel1);
    this.add(panel2);
    this.add(panel3);
    this.add(panel4);
    this.add(panel5);
    this.add(panel6);
    this.add(panel7);
    this.setSize(300, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setTitle("CREATE ROLE");
    setAction();
  }

  public void setAction() {
    button_create.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // job
        int job=0;
        if(radio_job1.isSelected()) {
          job=0;
        } else if(radio_job2.isSelected()) {
          job=1;
        } else if(radio_job3.isSelected()) {
          job=2;
        } else if(radio_job4.isSelected()) {
          job=3;
        }
        // nickname
        String nickname=field_nickName.getText();
        // sex
        boolean sex=false;
        if(radio_sex1.isSelected()) {
          sex=true;
        } else if(radio_sex2.isSelected()) {
          sex=false;
        }
        log.debug(String.format("JOB=%s,NICKNAME=%s,SEX=%s", job, nickname, sex));
        MyBuffer buf=reqRegPlayer((short)0x0005, nickname, sex, job);
        if(client.sendMessage(buf)) {
          Object message=client.readMessage();
          if(null != message) {
            MyBuffer bodyData=DataUtil.getBodyData(message);
            byte result=bodyData.get();
            log.debug(String.format("RESULT=%s", result));
            switch(result) {
              case 0:
                JOptionPane.showMessageDialog(null, String.format("CREATE ROLE FAILD!"));
                break;
              case 1:
                JOptionPane.showMessageDialog(null, String.format("CREATE ROLE SUCCESS!"));
                dispose();
                new SelectRole(client).init();
                break;
              case 2:
                JOptionPane.showMessageDialog(null, String.format("Nickname EXIST!"));
                field_nickName.setText("Please enter a nickname again!");
                break;
              case 3:
                JOptionPane.showMessageDialog(null, String.format("NOT FOUND THIS JOB!"));
                field_nickName.setText("Please select a job again!");
                break;
            }
          } else {
            log.debug("读到的信息为null");
          }
        }
      }
    });
    button_return.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
        new SelectRole(client).init();
      }
    });
  }

  public MyBuffer reqRegPlayer(short ptlId, String nickName, boolean sex, int jobId) {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort(ptlId);
    buf.putPrefixedString(nickName);
    buf.put((byte)(sex == true ? 1 : 0));
    buf.putInt(jobId);
    buf.flip();
    return buf;
  }

  public void setClient(MyClient client) {
    this.client=client;
  }

  public MyClient getClient() {
    return client;
  }
}
