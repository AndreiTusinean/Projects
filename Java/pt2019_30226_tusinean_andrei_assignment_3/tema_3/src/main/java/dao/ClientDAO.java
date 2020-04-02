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
import model.Client;
import reflection.Reflection.AbstractDAO;

public class ClientDAO extends AbstractDAO<Client>{

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());

	public static int insert(Client c) {
		try {
			Connection dbc = Conection.getConn();
			PreparedStatement is = dbc.prepareStatement("INSERT INTO client (id,name)VALUES (?,?)");
			is.setInt(1, c.getId());
			is.setString(2, c.getName());
			is.executeUpdate();
			is.close();
			Conection.close(is);
			Conection.close(dbc);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Client insert " + e.getMessage());
		}
		return c.getId();
	}
	
	public static void update(int id,String name) {
		try {
			Connection dbc = Conection.getConn();
			PreparedStatement is = dbc.prepareStatement("UPDATE client SET name = ? WHERE id = ?");
			is.setString(1, name);
			is.setInt(2, id);
			is.executeUpdate();
			is.close();
			Conection.close(is);
			Conection.close(dbc);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Client insert " + e.getMessage());
		}
	}
	
	public static List<Client> findAll() {
		Client c = null;
		List<Client> l = new ArrayList<Client>();
		try {
			Connection dbc = Conection.getConn();
			PreparedStatement s = dbc.prepareStatement("SELECT * FROM client");
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				c = new Client(rs.getInt("id"), rs.getString("name"));
				l.add(c);
			}
			Conection.close(rs);
			Conection.close(s);
			Conection.close(dbc);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Client find " + e.getMessage());
		}
		return l;
	}
	
}
