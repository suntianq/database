package database;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


public class ChaRu extends JFrame {

    public static int k;//用于保存当前的表格有多少行
    public static int t;//用于判断表格行数是否发生了变化


    JLabel lb1=new JLabel("新 生 信 息 录 入");
    JButton insertBtn=new JButton("新生录入");

    JTable table;
    DefaultTableModel dtm;
    String[] columns = {"学生学号","学生姓名","专业名称","专业编号","专业人数"};
    JTextField xuehaogot=new JTextField(10);
    JTextField xingminggot;

    static {
        new JTextField(10);
    }

    JTextField zhuanyegot;

    static {
        new JTextField(10);
    }

    public ChaRu(){

        //初始化button textfield，label等------------------------------------
        setTitle("新生信息录入");
        setResizable(false);
        dtm=new DefaultTableModel();//这是显示信息的表格

        table = new JTable(dtm);
        table.setEnabled(false);  //table 设置为不可点击
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane s1=new JScrollPane(table);
        dtm.setColumnCount(5);
        dtm.setColumnIdentifiers(columns);

        getContentPane().setLayout(null);
        lb1.setHorizontalAlignment(SwingConstants.CENTER);
        lb1.setBounds(0,10,614,30);
        lb1.setFont(new Font("华文新魏", Font.BOLD, 28));
        getContentPane().add(lb1);



        insertBtn.setBounds(55,223,141,45);
        insertBtn.setFont(new Font("微软雅黑", Font.BOLD, 22));
        insertBtn.addActionListener(new insertListener());
        getContentPane().add(insertBtn);

        s1.setBounds(262,60,343,290);
        getContentPane().add(s1);


        JButton returnBtn = new JButton("返      回");
        returnBtn.addActionListener(e -> {
           dispose();//点击返回按钮时，关闭当前窗口
        });
        returnBtn.setFont(new Font("微软雅黑", Font.BOLD, 22));
        returnBtn.setBounds(55, 281, 141, 45);
        getContentPane().add(returnBtn);

        JLabel lb2=new JLabel("新生学号：");
        lb2.setBounds(10,64,110,25);
        lb2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        getContentPane().add(lb2);
        xuehaogot.setBounds(112,68,112,23);
        xuehaogot.setFont(new Font("宋体",Font.PLAIN,12));
        getContentPane().add(xuehaogot);


        JLabel label = new JLabel("新生姓名：");
        label.setFont(new Font("微软雅黑", Font.BOLD, 20));
        label.setBounds(10, 117, 110, 25);
        getContentPane().add(label);
        xingminggot = new JTextField(10);
        xingminggot.setFont(new Font("宋体", Font.PLAIN, 12));
        xingminggot.setBounds(112, 120, 112, 23);
        getContentPane().add(xingminggot);

        JLabel label_1 = new JLabel("专业编号：");
        label_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
        label_1.setBounds(10, 168, 110, 25);
        getContentPane().add(label_1);

        zhuanyegot = new JTextField(10);
        zhuanyegot.setFont(new Font("宋体", Font.PLAIN, 12));
        zhuanyegot.setBounds(112, 171, 112, 23);
        getContentPane().add(zhuanyegot);


//初始化表格窗口------------------------------------------------------------------------
        String Tim="select * from 学生专业情况";
        if(Home.query(Tim)){
            //System.out.println(Tim);
            try{
                //再用查询写一遍遍历
                while(Home.rs.next()){
                    String XueShengBianHao=Home.rs.getString("学生编号"); //getString("")中双引号里的是表格的列的名字
                    String XueShengXingMing=Home.rs.getString("学生姓名");
                    String ZhuanYeMingCheng=Home.rs.getString("专业名称");
                    String ZhuanYeBianHao=Home.rs.getString("专业编号");
                    String ZhuanYeRenShu=Home.rs.getString("专业人数");

                    Vector v=new Vector();
                    v.add(XueShengBianHao);
                    v.add(XueShengXingMing);
                    v.add(ZhuanYeMingCheng);
                    v.add(ZhuanYeBianHao);
                    v.add(ZhuanYeRenShu);

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


    class insertListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {


            t=dtm.getRowCount();  //插入前的表格行数
            for(int i=0;i<t;i++){         //插入之前先清空
                dtm.removeRow(0);
            }

            String s1 =xuehaogot.getText();
            String s2 =xingminggot.getText();
            String s3 =zhuanyegot.getText();

            try {

                Home.insertstu(s1,s2,s3);
                //更新列表
                String Tim = "select * from 学生专业情况";
                if(Home.query(Tim)){
                    try{
                        while(Home.rs.next()){
                            String XueShengBianHao=Home.rs.getString("学生编号"); //getString("")中双引号里的是表格的列的名字
                            String XueShengXingMing=Home.rs.getString("学生姓名");
                            String ZhuanYeMingCheng=Home.rs.getString("专业名称");
                            String ZhuanYeBianHao=Home.rs.getString("专业编号");
                            String ZhuanYeRenShu=Home.rs.getString("专业人数");

                            Vector v=new Vector();
                            v.add(XueShengBianHao);
                            v.add(XueShengXingMing);
                            v.add(ZhuanYeMingCheng);
                            v.add(ZhuanYeBianHao);
                            v.add(ZhuanYeRenShu);

                            dtm.addRow(v);//dtm是显示信息的表格
                        }
                        //**************
                        k=dtm.getRowCount();  //插入后的表格行数
                        //**************
                        if(t != k){
                            JOptionPane.showMessageDialog(null,"录入新生成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"录入新生失败", "失败", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    catch(Exception eTIQ){
                        System.out.println("初始化表格失败！");
                    }
                }

                xuehaogot.setText("");
                xingminggot.setText("");
                zhuanyegot.setText("");
                //更新列表结束
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
