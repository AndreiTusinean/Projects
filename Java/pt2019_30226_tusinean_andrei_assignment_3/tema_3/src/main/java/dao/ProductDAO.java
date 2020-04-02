package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.Conection;
import model.Product;
import reflection.Reflection.AbstractDAO;

public class ProductDAO extends AbstractDAO<Product>{

	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());

	public static int insert(Product p) {
		try {
			Connection dbc = Conection.getConn();
			PreparedStatement is = dbc.prepareStatement("INSERT INTO product (id,name,cnt)VALUES (?,?,?)");
			is.setInt(1, p.getId());
			is.setString(2, p.getName());
			is.setInt(3, p.getCnt());
			is.executeUpdate();
			is.close();
			Conection.close(is);
			Conection.close(dbc);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Product insert " + e.getMessage());
		}
		return p.getId();
	}
	
	public static void update(int id,String name,int cnt) {
		try {
			Connection dbc = Conection.getConn();
			PreparedStatement is = dbc.prepareStatement("UPDATE product SET name = ? , cnt = ? WHERE id = ?");
			is.setString(1, name);
			is.setInt(2, cnt);
			is.setInt(3, id);
			is.executeUpdate();
			is.close();
			Conection.close(is);
			Conection.close(dbc);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Product insert " + e.getMessage());
		}
	}

	public static List<Product> findAll() {
		Product p = null;
		List<Product> l = new ArrayList<Product>();
		try {
			Connection dbc = Conection.getConn();
			PreparedStatement s = dbc.prepareStatement("SELECT * FROM product");
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				p = new Product(rs.getInt("id"), rs.getString("name"),rs.getInt("cnt"));
				l.add(p);
			}
			Conection.close(rs);
			Conection.close(s);
			Conection.close(dbc);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Product find " + e.getMessage());
		}
		return l;
	}
	
}
