package database;


import javax.swing.JFrame;
import javax.swing.JPanel;

public class EnterFrame extends JFrame {

    private JPanel contentPane;
    public EnterPanel lp;  //enterpanel为自定义类，构造函数参数为一个JFrame类的对象


    public static void main(String[] args) {
        new EnterFrame();
    }


    public EnterFrame() {
        setTitle("学生信息管理系统");
        setSize(610, 445);
        setLocation(500, 300);
        setResizable(false);
        lp = new EnterPanel(this);  //创建EnterPanel类对象
        lp.enterbutton.setLocation(258, 372);
        this.getContentPane().add(lp);  //用getContentPane()方法获得JFrame的内容面板，再对其加入组件：frame.getContentPane().add(childComponent)
        lp.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}


