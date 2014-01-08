package com.jqy.client.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.apache.log4j.Logger;

import com.jqy.client.MyClient;
import com.jqy.client.MyPlayer;
import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;

public class SelectRole extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID=2255992124579944706L;

  private Logger log=Logger.getLogger(this.getClass());

  ButtonGroup group_roles;

  private JButton button_create;

  private JButton button_start;

  private JPanel panel_control;

  private MyClient client;

  public SelectRole(MyClient client) {
    this.client=client;
  }

  public void init() {
    panel_control=new JPanel();
    button_create=new JButton("CREATE ROLE");
    button_start=new JButton("START GAME");
    generateRoleListPanel();
    panel_control.add(button_create);
    panel_control.add(button_start);
    this.add(panel_control);
    this.setSize(300, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setTitle("SELECT ROLE");
    setAction();
  }

  /**
   * 生成角色列表
   */
  private void generateRoleListPanel() {
    List<MyPlayer> players=buf_roles();
    // 设置布局
    this.setLayout(new GridLayout(players.size() + 1, 1));
    if(null != players) {
      group_roles=new ButtonGroup();
      group_roles.getSelection();
      for(MyPlayer p: players) {
        JPanel panel=new JPanel();
        JRadioButton radio_player=
          new JRadioButton(String.format("id=%s,nickName=%s,level=%s,sex=%s", p.getId(), p.getNickName(), p.getLevel(),
            p.isSex() == true ? "男" : "女"));
        panel.add(radio_player);
        this.add(panel);
        group_roles.add(radio_player);
      }
    } else {
      this.add(new JPanel().add(new JLabel("no roles can selected,pleast create your first role")));
    }
  }

  /**
   * 请求用户的角色列表
   * 
   * @return
   */
  private List<MyPlayer> buf_roles() {
    MyBuffer buf=buf_getRoles((short)0x0015);
    if(client.sendMessage(buf)) {
      log.debug("req success");
      Object message=client.readMessage();
      if(null != message) {
        // 解析数据
        MyBuffer bodyData=DataUtil.getBodyData(message);
        byte result=bodyData.get();
        if(result == Constant.SUCCESS) {
          int size=bodyData.getInt();
          List<MyPlayer> players=new LinkedList<MyPlayer>();
          for(int i=0; i < size; i++) {
            MyPlayer p=new MyPlayer();
            p.setSex(bodyData.get() == 0 ? false : true);
            p.setId(bodyData.getInt());
            p.setLevel(bodyData.getInt());
            p.setNickName(bodyData.getPrefixedString());
            players.add(p);
          }
          return players;
        }
      }
    }
    log.debug("req faild");
    return null;
  }

  /**
   * 获取用户角色列表数据
   * 
   * @param ptlId
   * @param nickName
   * @param sex
   * @param jobId
   * @return
   */
  private MyBuffer buf_getRoles(short ptlId) {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort(ptlId);
    buf.flip();
    return buf;
  }

  public void setAction() {
    button_create.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
        new CreateRole(client).init();
      }
    });
    button_start.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        boolean selected=false;
        JOptionPane.showMessageDialog(null, String.format("GAME STARTING!"));
        String selectedText="";
        int selectedRoleId=0;
        Enumeration<AbstractButton> enumButton=group_roles.getElements();
        while(enumButton.hasMoreElements()) {
          JRadioButton radio=(JRadioButton)enumButton.nextElement();
          if(group_roles.getSelection() == radio.getModel()) {
            selectedText=radio.getText();
            selected=true;
          }
        }
        log.debug("selectedText=" + selectedText);
        if(selected) {
          Map<String, String> m=new HashMap<String, String>();
          String[] atts=selectedText.split(",");
          for(String att: atts) {
            String[] a=att.split("=");
            log.debug(a[0] + "--->" + a[1]);
            m.put(a[0], a[1]);
          }
          selectedRoleId=Integer.parseInt(m.get("id"));
          startGame(selectedRoleId);
          savePlayer(Integer.parseInt(m.get("id")), m.get("nickName"), Integer.parseInt(m.get("level")), m.get("sex").equals("男")
            ? true : false);
          dispose();
          new Chat(client).init();
        } else {
          JOptionPane.showMessageDialog(null, String.format("you can't selected one role!"));
        }
      }

      private void savePlayer(int id, String nickname, int level, boolean sex) {
        MyPlayer player=new MyPlayer();
        player.setId(id);
        player.setNickName(nickname);
        player.setSex(sex);
        player.setLevel(level);
        client.setPlayer(player);
      }
    });
  }

  private void startGame(int selectedRoleId) {
    MyBuffer buf=buf_startGame((short)0x0011, selectedRoleId);
    if(client.sendMessage(buf)) {
      log.debug("req success");
      Object message=client.readMessage();
      if(null != message) {
        // 解析数据
        MyBuffer bodyData=DataUtil.getBodyData(message);
        byte result=bodyData.get();
        if(result == Constant.SUCCESS) {
          log.debug("start game success");
        }
      }
    }
  }

  private MyBuffer buf_startGame(short ptlId, int id) {
    MyBuffer buf=MyBuffer.allocate(1024);
    buf.put(Constant.REQ);
    buf.putShort(ptlId);
    buf.putInt(id);
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
