package database;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;


public class MainWindow extends JFrame {


    public static void main(String[] args) { new MainWindow();}


    public MainWindow() {
        setTitle("学生信息管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //设置窗口可以关闭,使用 System exit 方法退出应用程序。
        setSize(500,500);
        setResizable(false);
        setLocationRelativeTo(null);   //将MainWindow置于中央


    //设置按钮：
        //删除课程按钮
        JButton shanchuBtn = new JButton("课程信息删除");
        shanchuBtn.addActionListener(new shanchuListener());
        shanchuBtn.setFont(new Font("幼圆", Font.BOLD, 20));


        //插入新生按钮
        JButton charuBtn = new JButton("新生信息录入");
        charuBtn.addActionListener(new charuListener());
        charuBtn.setFont(new Font("幼圆", Font.BOLD, 20));



        //更新学生专业按钮
        JButton gengxinBtn = new JButton("转专业信息更新");
        gengxinBtn.addActionListener(new gengxinListener());
        gengxinBtn.setFont(new Font("幼圆", Font.BOLD, 20));


        //查询选课情况按钮
        JButton chaxunBtn = new JButton("选课信息查询");
        chaxunBtn.addActionListener(new chaxunListener());
        chaxunBtn.setFont(new Font("幼圆", Font.BOLD, 20));


        //退出系统按钮
        JButton tuichuBtn = new JButton("退 出 系 统");
        tuichuBtn.addActionListener(new tuichuListener());
        tuichuBtn.setFont(new Font("幼圆", Font.BOLD, 20));



        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 500, 500);
        setContentPane(panel);

        panel.setLayout(null); //使用绝对布局，部件不随窗体改变



        JLabel label = new JLabel("学生信息管理系统");
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label.setBounds(130, 20, 450, 40);

        shanchuBtn.setBounds(150,100,200,40);
        charuBtn.setBounds(150,170,200,40);
        gengxinBtn.setBounds(150,240,200,40);
        chaxunBtn.setBounds(150,310,200,40);
        tuichuBtn.setBounds(150,380,200,40);


        panel.add(label);
        panel.add(shanchuBtn);
        panel.add(charuBtn);
        panel.add(gengxinBtn);
        panel.add(chaxunBtn);
        panel.add(tuichuBtn);
        setVisible(true);
    }


    //删除触发器
    static class shanchuListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            new ShanChu();
        }
    }


    //插入触发器
    static class charuListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            new ChaRu();
        }
    }


    //更新触发器
    static class gengxinListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            new GengXin();
        }
    }


    //查询触发器
    static class chaxunListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            new ChaXun();
        }
    }

    //退出触发器
    class tuichuListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}


