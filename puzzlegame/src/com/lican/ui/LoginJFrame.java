package com.lican.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {
    //登录界面

    public LoginJFrame(){
        //创建登陆界面的时候，同时给界面去设置一些信息

        this.setSize(488, 430);
        this.setVisible(true);

        //设置界面的标题
        this.setTitle("拼图 登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏的关闭模式
        this.setDefaultCloseOperation(3);
        //让窗口显示
        this.setVisible(true);

        //设置游戏的关闭模式
        this.setDefaultCloseOperation(3);

    }
}
