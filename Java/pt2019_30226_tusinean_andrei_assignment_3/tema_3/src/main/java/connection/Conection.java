package connection;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conection {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/tema3";
	private static final String USER = "root";
	private static final String PASS = "mysql";
	
	private static Conection cf = new Conection();
	
	private Conection() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
		}
	}
	
	private Connection createConn() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DBURL,USER,PASS);
		} catch (SQLException e) {}
		return con;
	}
	
	public static Connection getConn() {
		return cf.createConn();
	}
	
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {}
		}
	}
	
	public static void close(Statement s) {
		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {}
		}
	}
	
	public static void close(ResultSet r) {
		if (r != null) {
			try {
				r.close();
			} catch (SQLException e) {}
		}
	}
}
