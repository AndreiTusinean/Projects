package domain;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.swing.*;
import java.sql.*;

import static data_source.Conn.connect;

public class EmployeeAccountOperations {
    //Create/update/delete/view client account (account information: identification number, type, amount of money, date of creation).
    static Connection conn = null;

    public static String addAccount(int id, String type, int balance, String date, String owner) throws SQLException {
        String msg = "Task successful";
        try {
            conn = connect(conn);
            String query = " insert into account (id,type,balance,date,owner)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.setString(2, type);
            preparedStmt.setInt(3, balance);
            preparedStmt.setString(4, date);
            preparedStmt.setString(5, owner);
            preparedStmt.execute();
        } catch (Exception e) {
            msg = "Incorrect values";
        }
        conn.close();
        return msg;
    }

    public static String updateAccount(int id, String type, int balance, String date, String owner) throws SQLException {
        String msg = "Task successful";
        try {
            conn = connect(conn);
            String query = " update account set type=?, balance=? where id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, type);
            preparedStmt.setInt(2, balance);
            preparedStmt.setInt(3, id);
            preparedStmt.execute();
        } catch (Exception e) {
            msg = "Incorrect values";
        }
        conn.close();
        return msg;
    }

    public static String deleteAccount(int id, String owner) throws SQLException {
        String msg = "Task successful";
        try {
            conn = connect(conn);
            String query = "delete from account where id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
        } catch (Exception e) {
            msg = "Incorrect values";
        }
        conn.close();
        return msg;
    }

    public static String viewAccount(String owner) throws SQLException {
        String msg = "";
        try {
            conn = connect(conn);
            Statement myStmt = conn.createStatement();
            ResultSet myRes = myStmt.executeQuery("select * from account where owner = '" + owner + "'");
            while (myRes.next()) {
                msg += myRes.getString("id") + ", " + myRes.getString("type") + ", " + myRes.getString("balance")
                        + ", " + myRes.getString("date") + ", " + myRes.getString("owner");
                msg += "\n";
            }
            if (!msg.equals(""))
                msg = owner + "'s accounts:\n" + msg;
            System.out.println(msg);
        } catch (Exception e) {
            msg = "Owner or accounts not found";
        }
        conn.close();
        return msg;
    }

}
