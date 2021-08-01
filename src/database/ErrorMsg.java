package database;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorMsg extends JFrame {

    public ErrorMsg() {
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);//指示顶级窗口不会被任何应用程序模式对话框阻止。
        setResizable(false);
        setTitle("错误提示");
        setSize(400, 200);
        setLocation(500, 300);
        getContentPane().setLayout(null);
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 200);
        getContentPane().add(panel);
        panel.setLayout(null);


        JLabel label = new JLabel("用户名或密码错误!!");
        label.setFont(new Font("幼圆", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(0, 50, 400, 36);
        panel.add(label);


        JButton button = new JButton("返回");//关闭按钮
        button.addActionListener(new ActionListener() {//关闭当前错误提示窗口
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });

        button.setBackground(new Color(230, 230, 250));
        button.setFont(new Font("幼圆", Font.BOLD, 17));
        button.setBounds(130, 120, 120, 27);
        panel.add(button);

        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String args[]) {
        new ErrorMsg();
    }
}
