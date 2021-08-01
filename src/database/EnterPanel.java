package database;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EnterPanel extends JPanel {


    public JLabel namelable, titlelable,pwdlable,titlelable2;
    public JButton enterbutton;
    public JTextField namefield;
    public JPasswordField pwdfield;
    @SuppressWarnings("unused")
    public JFrame iframe;

    public EnterPanel(JFrame frame) {
        iframe = frame;
        titlelable = new JLabel("学生课程管理系统");
        titlelable.setHorizontalAlignment(SwingConstants.CENTER);
        titlelable.setBounds(0, 113, 610, 30);
        titlelable.setFont(new Font("华文新魏", Font.BOLD, 25));


        titlelable2=new JLabel("管理员登录界面");
        titlelable2.setFont(new Font("幼圆", Font.BOLD, 18));
        titlelable2.setHorizontalAlignment(SwingConstants.CENTER);
        titlelable2.setBounds(0, 143, 610, 30);


        namelable = new JLabel("用户名:");
        namelable.setFont(new Font("微软雅黑", Font.BOLD, 18));
        namelable.setBounds(202, 274, 67, 23);


        namefield = new JTextField(14);
        namefield.setBounds(269, 274, 150, 25);


        pwdlable=new JLabel("密   码:");
        pwdlable.setFont(new Font("微软雅黑", Font.BOLD, 18));
        pwdlable.setBounds(202, 318, 67, 23);


        pwdfield = new JPasswordField(14);
        pwdfield.setBounds(269, 319, 150, 25);
        pwdfield.setEchoChar('*');


        enterbutton = new JButton("登录");
        enterbutton.setBounds(279, 367, 80, 25);


        enterbutton.addActionListener(new CreatNewWindow());// 对enterbutton组件注册监听内部类(鼠标)

        enterbutton.addKeyListener(new KeyAdapter()//对enterbutton组件注册监听内部类(键盘)
        {
            public void keyPressed(KeyEvent e)
            {
                if (namefield.getText().equals("root") && String.valueOf(pwdfield.getPassword()).equals("123456")) {
                    System.out.println("right");
                    iframe.dispose();
                    new MainWindow();
                } else {
                    System.out.println("wrong");
                    new ErrorMsg();
                }
            }
        });




        add(titlelable);
        add(titlelable2);
        add(namelable);
        add(namefield);
        add(pwdfield);
        add(enterbutton);
        add(pwdlable);

        setLayout(null);

    }




    class CreatNewWindow implements ActionListener {
        int p = 0;

        public void actionPerformed(ActionEvent e) {
            if (namefield.getText().equals("root") && String.valueOf(pwdfield.getPassword()).equals("123456")) {
                p = 1;
                System.out.println("right");
                iframe.dispose();
                new MainWindow();
            } else {
                System.out.println("wrong");
                new ErrorMsg();
            }
        }
    }


}
