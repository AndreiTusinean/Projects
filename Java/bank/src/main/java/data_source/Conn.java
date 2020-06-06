package data_source;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.*;

public class Conn {
    static Connection conn = null;
    public static Connection connect(Connection conn){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
