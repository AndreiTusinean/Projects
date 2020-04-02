package dao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.Conection;
import model.Orders;
import reflection.Reflection.AbstractDAO;

public class OrdersDAO extends AbstractDAO<Orders> {

	protected static final Logger LOGGER = Logger.getLogger(OrdersDAO.class.getName());

	public static void bill(Orders o) {
		String s = "Order_";
		s += Integer.toString(o.getId());
		s += ".txt";
		PrintWriter w = null;
		try {
			w = new PrintWriter(s, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		w.println(o.toString());
		w.close();
	}

	public static int insert(Orders o) {
		try {
			Connection dbc = Conection.getConn();
			PreparedStatement s = dbc.prepareStatement("SELECT * FROM product where name = ?");
			PreparedStatement is = null;
			s.setString(1, o.getName());
			ResultSet rs = s.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			int cnt = rs.getInt("cnt");
			String name = rs.getString("name");
			if (cnt - o.getCnt() >= 0 && o.getCnt() > 0) {
				is = dbc.prepareStatement("INSERT INTO orders (id,name,clientn,cnt)VALUES (?,?,?,?)");
				is.setInt(1, o.getId());
				is.setString(2, o.getName());
				is.setString(3, o.getClientn());
				is.setInt(4, o.getCnt());
				is.executeUpdate();
				is.close();
				bill(o);
				ProductDAO.update(id, name, cnt - o.getCnt());
			} else
				throw new IllegalArgumentException("Stoc insuficitent pentru " + rs.getString("name") + "!");
			s.close();
			Conection.close(is);
			Conection.close(dbc);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Order insert " + e.getMessage());
		}
		return o.getId();
	}

	public static void update(int id, String name, String clientn, int cnt) {
		try {
			Connection dbc = Conection.getConn();
			PreparedStatement is = dbc
					.prepareStatement("UPDATE orders SET name = ? , clientn = ? , cnt = ? WHERE id = ?");
			is.setString(1, name);
			is.setString(2, clientn);
			is.setInt(3, cnt);
			is.setInt(4, id);
			is.executeUpdate();
			is.close();
			Conection.close(is);
			Conection.close(dbc);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Orders insert " + e.getMessage());
		}
	}

}
