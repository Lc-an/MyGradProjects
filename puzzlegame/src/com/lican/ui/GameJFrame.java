package com.lican.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //GameJFrame界面表示游戏的主界面
    int[][] data = new int[4][4];

    //记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    //定义一个变量，记录当前展示图片的路径
    String path = "puzzlegame\\image\\animal\\animal3\\";

    int[][] win = {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,0}
    };

    //定义变量统计步数
    int step = 0;

    //创建选项下面的条目
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reloginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");

    //重新游戏menu中的条目
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");

    public GameJFrame(){
        //初始化界面
        initJFrame();  //快捷键ctrl+alt+m

        //初始化菜单
        initJMenuBar();

        //打乱数据
        initData();
        
        //初始化图片
        initImage();

        //让窗口显示
        this.setVisible(true);
    }

    //初始化数据（打乱）
    private void initData() {
        //1.定义一个一维数组
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //2.打乱数组中的数据的顺序
        Random r = new Random();
        //遍历数组，得到每一个元素，拿着每一个元素跟随机索引上的数据进行交换
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            //拿到遍历到的每一个数据，跟随机索引上的数据进行交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //4. 给二维数组添加数据
        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                x = i/4;
                y = i%4;
            }
                data[i / 4][i % 4] = tempArr[i];
        }
    }

    //初始化图片
    private void initImage() {
        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        if(victory()){
            //显示胜利的图标
            JLabel winJLabel = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数:"+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        for(int i = 0; i < 4; i++){
            for (int j = 0; j<4; j++){
                //获取当前要加载图片的序号
                int num = data[i][j];
                JLabel jLabel = new JLabel(new ImageIcon(path+num+".jpg"));
                //指定图片位置
                jLabel.setBounds(105*j+83, 105*i+134, 105, 105);
                //给图片添加边框
                //0:表示让图片凸起来
                //1：表示让图片凹下去
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        //把背景图片添加到界面当中
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项（功能，关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        //创建更换图片
        JMenu changeImage = new JMenu("更换图片");


        //将每一个选项下面的条目添加到选项中
        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);

        //将菜单里面的两个选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);


        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);
    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603, 680); //调用父类的方法。this可以省略
        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏的关闭模式
        this.setDefaultCloseOperation(3);
        //取消默认居中放置，只有取消了才会按照xy轴的形式添加组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            //把界面中所有的图片全部删除
            this.getContentPane().removeAll();
            //加载第一张完整的图片
            JLabel all = new JLabel((new ImageIcon(path+"all.jpg")));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            //添加背景图片
            JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            //把背景图片添加到界面当中
            this.getContentPane().add(background);

            //刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利，如果胜利，此方法需要直接结束，不能再执行下面的移动代码了
        if(victory()){
            //结束方法
            return;
        }
        //对上，下，左，右进行判断
        //左：37 上：38 右：39 下：40
        int code = e.getKeyCode();
        if(code == 37){
            if(y == 3){
                return;
            }
            //逻辑：把空白方块右边的数字向左移动
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            //每移动依次，计数器就自增一次
            step++;
            //调用方法按照最新的数字加载图片
            initImage();

        }else if(code == 38){
            if(x == 3){
                return;
            }
            //逻辑：把空白方块下方的数字向上移动
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            //每移动依次，计数器就自增一次
            step++;
            //调用方法按照最新的数字加载图片
            initImage();
        }else if(code == 39){
            if(y == 0){
                return;
            }
            //逻辑：把空白方块左边的数字向右移动
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            //每移动依次，计数器就自增一次
            step++;
            //调用方法按照最新的数字加载图片
            initImage();

        }else if(code == 40){
            if(x == 0){
                return;
            }
            //逻辑：把空白方块上方的数字向下移动
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            //每移动依次，计数器就自增一次
            step++;
            //调用方法按照最新的数字加载图片
            initImage();
        }else if(code == 65){
            initImage();
        }else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0},
            };
            x = 3;
            y = 3;
            initImage();
        }
    }

    //判断data数组中的数据是否跟win数组中相同
    //如果全部相同，返回true，否则返回false
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            //i：依次表示二维数组data里面的索引
            //data[i]:依次表示每一个一维数组
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        //判断
        if(obj == replayItem){
            System.out.println("重新游戏");

            //计步器清0
            step = 0;
            //再次打乱二维数组中的数据
            initData();
            //重新加载图片
            initImage();

        }else if(obj == reloginItem){
            System.out.println("重新登录");

            //关闭当前游戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        }else if(obj == closeItem){
            System.out.println("关闭游戏");
            //直接关闭虚拟机即可
            System.exit(0);
        }else if(obj == accountItem){
            System.out.println("公众号");

            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //创建一个管理图片的容器对象JLabel
            JLabel jLabel = new JLabel(new ImageIcon("PuzzleGame\\image\\about.png"));
            //设置位置和宽高
            jLabel.setBounds(0,0,258,258);
            //把图片加到弹框中
            jDialog.getContentPane().add(jLabel);
            //给弹框设置大小
            jDialog.setSize(344, 344);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);
            //让弹框显示出来
            jDialog.setVisible(true);
        }else if(obj == girl){
            Random r = new Random();
            int index = r.nextInt(13)+1;
            System.out.println("美女:"+index);
            path = "puzzlegame\\image\\girl\\girl"+index+"\\";
            //再次打乱二维数组中的数据
            initData();
            initImage();
        }else if(obj == animal){
            Random r = new Random();
            int index = r.nextInt(8)+1;
            System.out.println("动物:"+index);
            path = "puzzlegame\\image\\animal\\animal"+index+"\\";
            //再次打乱二维数组中的数据
            initData();
            initImage();
        }else if(obj == sport){
            Random r = new Random();
            int index = r.nextInt(10)+1;
            path = "puzzlegame\\image\\sport\\sport"+index+"\\";
            //再次打乱二维数组中的数据
            initData();
            initImage();
        }
    }
}
