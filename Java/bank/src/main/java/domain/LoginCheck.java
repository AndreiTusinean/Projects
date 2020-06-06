package domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static data_source.Conn.connect;

public class LoginCheck {
    static Connection conn = null;

    public static String checkLogin(String name, String pass) throws SQLException {
        String msg = "";
        try {
            conn = connect(conn);
            Statement myStmt = conn.createStatement();
            ResultSet myRes = myStmt.executeQuery("select * from user where name = '" + name + "' and pass = '" + pass + "'");

            if (myRes.next()) {
                msg = myRes.getString("type");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        if(msg.equals(""))
            msg="Username or password incorect";
        return msg;
    }

}
