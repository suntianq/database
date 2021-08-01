package database;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class GengXin extends JFrame {
    JLabel lb1=new JLabel("转 专 业 信 息 更 新");
    JLabel lb2=new JLabel("学生学号 ：");

    JButton updateBtn=new JButton("更  新");

    JTable table;
    DefaultTableModel dtm;
    String[] columns = {"学生学号","学生姓名","专业名称","专业编号","专业人数"};

    JTextField xuehaogot=new JTextField(10);//获得学生学号
    JTextField newmajorgot;//获得学生想要转入的专业编号
    static {
        new JTextField(10);
    }


    public GengXin(){
        setTitle("转专业信息更新");
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

        xuehaogot.setBounds(134,68,112,23);
        xuehaogot.setFont(new Font("宋体",Font.PLAIN,12));
        getContentPane().add(xuehaogot);

        updateBtn.setBounds(55,192,141,45);
        updateBtn.setFont(new Font("微软雅黑", Font.BOLD, 22));
        updateBtn.addActionListener(new updateListener());
        getContentPane().add(updateBtn);

        s1.setBounds(262,60,343,290);
        getContentPane().add(s1);


        JButton returnBtn = new JButton("返  回");
        returnBtn.addActionListener(e -> {
            dispose();//点击返回按钮时，关闭当前窗口
        });
        returnBtn.setFont(new Font("微软雅黑", Font.BOLD, 22));
        returnBtn.setBounds(55, 281, 141, 45);
        getContentPane().add(returnBtn);


        JLabel label = new JLabel("新专业编号：");
        label.setFont(new Font("微软雅黑", Font.BOLD, 20));
        label.setBounds(10, 117, 125, 25);
        getContentPane().add(label);

        newmajorgot = new JTextField(10);
        newmajorgot.setFont(new Font("宋体", Font.PLAIN, 12));
        newmajorgot.setBounds(134, 121, 112, 23);
        getContentPane().add(newmajorgot);


//初始化表格窗口------------------------------------------------------------------------
        String Tim="select * from 学生专业情况";
        if(Home.query(Tim)){//再用查询写一遍遍历
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
            }
            catch(Exception eTIQ){
                System.out.println("初始化表格失败！");
            }
        }


        setSize(630,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class updateListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            int rc=dtm.getRowCount();
            for(int i=0;i<rc;i++){
                dtm.removeRow(0);
            }

            String s1 =xuehaogot.getText();
            String s2 =newmajorgot.getText();

            try {
                Home.updatestu(s1,s2);
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
                    }
                    catch(Exception eTIQ){
                        System.out.println("初始化表格失败！");
                    }
                }
                xuehaogot.setText("");
                newmajorgot.setText("");
                //更新列表结束

            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
    }

}
