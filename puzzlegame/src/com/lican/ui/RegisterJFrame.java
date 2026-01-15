package com.lican.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    //和注册界面相关的代码

    public RegisterJFrame(){
        this.setSize(488, 500); //调用父类的方法。this可以省略
        this.setVisible(true);

        //设置界面的标题
        this.setTitle("拼图 注册");
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
