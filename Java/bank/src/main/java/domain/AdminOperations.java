package domain;

import presentation.AdminReports;

import java.sql.*;
import java.util.Calendar;

import static data_source.Conn.connect;

public class AdminOperations {
    //CRUD on employees’ information.
    static Connection conn = null;

    public static String addEmployee(String name, String pass, String type) throws SQLException {
        String msg = "Task successful";
        try {
            conn = connect(conn);
            String query = " insert into user (name,pass,type)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, pass);
            preparedStmt.setString(3, type);
            preparedStmt.execute();
        } catch (Exception e) {
            msg = "Incorrect values";
        }
        conn.close();
        return msg;
    }

    public static String updateEmployee(String name, String pass, String type) throws SQLException {
        String msg = "Task successful";
        try {
            conn = connect(conn);
            String query = " update  user set type=?, pass=? where name=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, type);
            preparedStmt.setString(2, pass);
            preparedStmt.setString(3, name);
            preparedStmt.execute();
        } catch (Exception e) {
            msg = "Incorrect values";
        }
        conn.close();
        return msg;
    }

    public static String deleteEmployee(String name) throws SQLException {
        String msg = "Task successful";
        try {
            conn = connect(conn);
            String query = "delete from user where name = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.execute();
        } catch (Exception e) {
            msg = "Incorrect values";
        }
        conn.close();
        return msg;
    }

    public static String viewEmployee(String name) throws SQLException {
        String msg = "";
        try {
            conn = connect(conn);
            Statement myStmt = conn.createStatement();
            ResultSet myRes = myStmt.executeQuery("select * from user where name = '" + name + "'");
            while (myRes.next()) {
                msg += myRes.getString("name") + ", " + myRes.getString("pass") + ", " + myRes.getString("type");
                msg += "\n";
            }
            if (!msg.equals(""))
                msg = name + "'s account:\n" + msg;
            System.out.println(msg);
        } catch (Exception e) {
            msg = "Owner or accounts not found";
        }
        conn.close();
        return msg;
    }

    public static void insertEmployeeAction(String name, String action) throws SQLException {
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        try {
            conn = connect(conn);
            String query = " insert into employee_actions (name,date,action)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setDate(2, date);
            preparedStmt.setString(3, action);
            preparedStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
    }

    public static String transferBalance(int account1, int account2, int amount) throws SQLException {
        String msg = "Task successful";
        try {
            conn = connect(conn);
            String query = " update  account set balance=balance-? where id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, amount);
            preparedStmt.setInt(2, account1);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
            msg = "Incorrect values";
        }
        if (!msg.equals("Incorrect values")) {
            try {
                conn = connect(conn);
                String query = " update  account set balance=balance+? where id=?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, amount);
                preparedStmt.setInt(2, account2);
                preparedStmt.execute();
            } catch (Exception e) {
                msg = "Incorrect values";
            }
        }
        conn.close();
        return msg;
    }

    public static String payBill(int account, int amount) throws SQLException {
        String msg = "Task successful";
        try {
            conn = connect(conn);
            String query = " update  account set balance=balance-? where id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, amount);
            preparedStmt.setInt(2, account);
            preparedStmt.execute();
        } catch (Exception e) {
            msg = "Incorrect values";
        }

        conn.close();
        return msg;
    }

    public static boolean checkDate(Date d1,Date d2,Date d3){
        return d2.compareTo(d1) > 0 && d2.compareTo(d3) < 0;
    }

    public static Date StringToDate(String str){
        int[] d = new int[3];
        try {
            str.matches("\\d{4}-\\d{2}-\\d{2}");
            String[] s = str.split("-");
            d[0] = Integer.parseInt(s[0]);
            d[1] = Integer.parseInt(s[1]);
            d[2] = Integer.parseInt(s[2]);
        } catch (NumberFormatException e) {
            return null;
        }
        return new Date(d[0] - 1900, d[1]-1, d[2]);
    }

    public static String makeReport(String employee, Date d1, Date d2) throws SQLException {
        String msg = "";
        try {
            conn = connect(conn);
            Statement myStmt = conn.createStatement();
            ResultSet myRes = myStmt.executeQuery("select * from employee_actions where name = '" + employee + "'");
            while (myRes.next()) {
                System.out.println(myRes.getString("date") + myRes.getString("action"));
                if(checkDate(d1, StringToDate(myRes.getString("date")),d2)){
                    msg += myRes.getString("date") + myRes.getString("action")+"\n";
                }
            }
            msg = employee + "\n" + msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        if (msg.equals(""))
            msg = "Error";
        return msg;
    }

    public static void main(String[] args){
        java.sql.Date date1 = new Date(2019 - 1900, 0, 15);
        java.sql.Date date2 = new Date(2020 - 1900, 0, 20);
        java.sql.Date date3 = new Date(2021 - 1900, 0, 30);
        System.out.println(date2.compareTo(date1)>0);
        System.out.println(date2.compareTo(date3)<0);
        System.out.println(checkDate(date1,StringToDate("2020-03-24"),date3));
    }
}
