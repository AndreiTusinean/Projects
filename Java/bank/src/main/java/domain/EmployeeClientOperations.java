package domain;

import java.sql.*;

import static data_source.Conn.connect;

public class EmployeeClientOperations {
    static Connection conn = null;

    public static String addClient(String name, int idNr, int pnc, String address) throws SQLException {
        String msg = "";
        try {
            conn = connect(conn);
            String query = " insert into client (name,id_card,pnc,address)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setInt(2, idNr);
            preparedStmt.setInt(3, pnc);
            preparedStmt.setString(4, address);
            preparedStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        if (msg == "")
            msg = "Incorect values";
        return msg;
    }

    public String updateClient(String name, int idNr, int pnc, String address) throws SQLException {
        String msg = "";
        try {
            conn = connect(conn);
            String query = " update client set id_card=?, address=? where name=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, idNr);
            preparedStmt.setString(2, address);
            preparedStmt.setString(3, name);
            preparedStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        if (msg.equals(""))
            msg = "Incorect values";
        return msg;
    }

    public String viewClient(String name) throws SQLException {
        String msg = "";
        try {
            conn = connect(conn);
            Statement myStmt = conn.createStatement();
            ResultSet myRes = myStmt.executeQuery("select * from client where name = '" + name + "'");
            while (myRes.next()) {
                msg += myRes.getString("name") + ", " + myRes.getString("id_card") + ", " + myRes.getString("pnc") + ", " + myRes.getString("address");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        if (msg.equals(""))
            msg = "Client not found";
        return msg;
    }
}
