package database;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;



public class ShanChu extends JFrame {


    JLabel lb1=new JLabel("课 程 信 息 删 除");
    JLabel lb2=new JLabel("课程代码：");
    JTextField numgot=new JTextField(10);
    JButton deleteBtn=new JButton("删除课程");
    JTable table;
    DefaultTableModel dtm;
    String[] columns = {"学生学号","学生姓名","课程代码","课程名称","修课成绩"};


    public ShanChu(){
        setTitle("课程信息删除");
        setResizable(false);
        dtm=new DefaultTableModel();//这是显示信息的表格

        table = new JTable(dtm);
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane s1=new JScrollPane(table);
        dtm.setColumnCount(5);
        dtm.setColumnIdentifiers(columns);

        getContentPane().setLayout(null);


        lb1.setHorizontalAlignment(SwingConstants.CENTER);
        lb1.setBounds(0,10,614,30);
        lb1.setFont(new Font("华文新魏", Font.BOLD, 28));
        getContentPane().add(lb1);

        lb2.setBounds(10,64,110,25);
        lb2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        getContentPane().add(lb2);


        numgot.setBounds(112,68,112,23);
        numgot.setFont(new Font("宋体",Font.PLAIN,12));
        getContentPane().add(numgot);


        deleteBtn.setBounds(55,204,141,45);
        deleteBtn.setFont(new Font("微软雅黑", Font.BOLD, 22));
        deleteBtn.addActionListener(new deListener());
        getContentPane().add(deleteBtn);


        s1.setBounds(262,60,343,290);
        getContentPane().add(s1);


        JButton returnBtn = new JButton("返  回");
        returnBtn.addActionListener(e -> dispose());
        returnBtn.setFont(new Font("微软雅黑", Font.BOLD, 22));
        returnBtn.setBounds(55, 281, 141, 45);
        getContentPane().add(returnBtn);


//初始化表格窗口------------------------------------------------------------------------
        String Tim="select * from 学生选课情况";
        if(Home.query(Tim)){
            try{
                while(Home.rs.next()){
                    String XueShengBianHao=Home.rs.getString("学生编号"); //getString("")中双引号里的是表格的列的名字
                    String XueShengXingMing=Home.rs.getString("学生姓名");
                    String KeChengBianMa=Home.rs.getString("课程编码");
                    String KeChengMingCheng=Home.rs.getString("课程名称");
                    String KeChengChengJi=Home.rs.getString("课程成绩");

                    Vector v=new Vector();
                    v.add(XueShengBianHao);
                    v.add(XueShengXingMing);
                    v.add(KeChengBianMa);
                    v.add(KeChengMingCheng);
                    v.add(KeChengChengJi);

                    dtm.addRow(v);//dtm是显示信息的表格
                }
            }
            catch(Exception eTIQ){
                System.out.println("初始化表格失败！");
            }
        }


        setSize(630,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class deListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            int rc=dtm.getRowCount();
            for(int i=0;i<rc;i++){
                dtm.removeRow(0);
            }

            String s =numgot.getText();
            int key=Integer.parseInt(s);
            try {
                Home.decourse(key);
                //更新列表
                String Tim="select * from 学生选课情况";
                if(Home.query(Tim)){
                    try{
                        while(Home.rs.next()){
                            String XueShengBianHao=Home.rs.getString("学生编号"); //getString("")中双引号里的是表格的列的名字
                            String XueShengXingMing=Home.rs.getString("学生姓名");
                            String KeChengBianMa=Home.rs.getString("课程编码");
                            String KeChengMingCheng=Home.rs.getString("课程名称");
                            String KeChengChengJi=Home.rs.getString("课程成绩");

                            Vector v=new Vector();
                            v.add(XueShengBianHao);
                            v.add(XueShengXingMing);
                            v.add(KeChengBianMa);
                            v.add(KeChengMingCheng);
                            v.add(KeChengChengJi);

                            dtm.addRow(v);//dtm是显示信息的表格
                        }
                    }
                    catch(Exception eTIQ){
                        System.out.println("初始化表格失败！");
                    }
                }
                numgot.setText("");
                //更新列表结束,将textfield框内容清空
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
