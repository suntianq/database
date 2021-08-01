package database;
import com.mysql.cj.xdevapi.Type;

import java.sql.*;
import javax.swing.*;

public class Home {
    public static Connection cn;
    public static Statement st;//Statement用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句。
    public static ResultSet rs;//ResultSet用于接收数据库所有查询记录，并对其内容予以显示

    public static int a,b;

    public static boolean openDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu_mng?characterEncoding=utf8&serverTimezone=GMT","root","123456");//?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT;

            cn.setCatalog("stu_mng");
            System.out.println("数据库连接成功");
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);//第一个参数返回可滚动的结果集，当数据库变化时，当前结果集同步改变。
            //第二个参数不能用结果集更新数据库中的表
            return true;

        } catch (SQLException | ClassNotFoundException sqlEx) {
            System.out.println(sqlEx.getMessage());
            return false;

        }
    }


    public static void closeDB() {
        try {
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){openDB();}


    //删除课程操作
    public static void decourse(int coursen) throws SQLException{
        openDB();
        Statement stmt =cn.createStatement();
        try{
            cn.setAutoCommit(false);
            stmt = cn.createStatement();
            int a=stmt.executeUpdate("delete from course where courseno='"  +   coursen   +   "'");//记录数据库的变化行数
            cn.commit();       //提交JDBC事务
            cn.setAutoCommit(true);// 恢复JDBC事务的默认提交方式
            if(a==1){
                JOptionPane.showMessageDialog(null,"删除课程成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null,"删除课程失败", "失败", JOptionPane.INFORMATION_MESSAGE);
            }
            stmt.close();
        }
        catch (Exception exc) {
            cn.rollback();//回滚JDBC事务
            exc.printStackTrace();
            stmt.close();
        }
    }


    //新生录入操作
    public static void insertstu(String s1,String s2,String s3) throws SQLException{
        openDB();
        Statement stmt =cn.createStatement();
        try{
            cn.setAutoCommit(false);
            stmt = cn.createStatement();

            int a=stmt.executeUpdate("insert into student values('"+ s1 +"','"+ s2 +"','"+ s3 +"')");
            cn.commit();//提交JDBC事务
            cn.setAutoCommit(true);// 恢复JDBC事务的默认提交方式
            stmt.close();
        }
        catch (Exception exc) {
            cn.rollback();//回滚JDBC事务
            exc.printStackTrace();
            stmt.close();
        }
    }


    //转专业信息更新操作
    public static void updatestu(String s1,String s2) throws SQLException{//s1是学生学号，s2是新专业编号
        openDB();
        CallableStatement stmt =cn.prepareCall("{call change_major(?,?,?)}");
        try{
            cn.setAutoCommit(false);
            stmt.setNString(1,s1);
            stmt.setNString(2,s2);
            stmt.registerOutParameter(3,Types.INTEGER);
            a=0;
            a=stmt.executeUpdate();
            cn.commit();//提交JDBC事务
            cn.setAutoCommit(true);// 恢复JDBC事务的默认提交方式
            b=stmt.getInt(3);
            if(a==1 && b==1){
                JOptionPane.showMessageDialog(null,"转专业信息更新成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null,"转专业信息更新失败", "失败", JOptionPane.INFORMATION_MESSAGE);
            }
            stmt.close();
        }
        catch (Exception exc) {
            cn.rollback();//回滚JDBC事务
            exc.printStackTrace();
            stmt.close();
        }
    }



    //查询操作
    public static boolean query(String sqlString) {
        openDB() ;
        try {
            rs = null;
            rs = st.executeQuery(sqlString);
        } catch (Exception Ex) {
            System.out.println("sql exception+   " + Ex);
            return false;
        }
        return true;
    }
}
