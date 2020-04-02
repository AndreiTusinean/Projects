package reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.Conection;

public class Reflection {

	public static void extract(Object o) {
		for (Field f : o.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			Object v;
			try {
				v = f.get(o);
				System.out.println(f.getName() + "=" + v);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public static class AbstractDAO<T> {
		protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
		public Class<T> type;
	
		@SuppressWarnings("unchecked")
		public AbstractDAO() {
			this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		
		private String selQ(String f) {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append(" * ");
			sb.append(" FROM ");
			sb.append(type.getSimpleName());
			sb.append(" WHERE " + f + " =?");
			return sb.toString();
		}
		
		private String delQ(String f) {
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE ");
			sb.append(" FROM ");
			sb.append(type.getSimpleName());
			sb.append(" WHERE " + f + " =?");
			return sb.toString();
		}
		
		public T find(int id) {
			Connection dbc =null;
			PreparedStatement s = null;
			ResultSet rs =null;
			String q = selQ("id");
			try {
				dbc = Conection.getConn();
				s = dbc.prepareStatement(q);
				s.setInt(1, id);
				rs = s.executeQuery();
				return createObj(rs).get(0);
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "DAO:find " + e.getMessage());
			} finally {
				Conection.close(rs);
				Conection.close(s);
				Conection.close(dbc);
			}
			return null;
		}
			
		public void delete(int id) {
			String q = delQ("id");
			try {
				Connection dbc = Conection.getConn();
				PreparedStatement is = dbc.prepareStatement(q);
				is.setInt(1, id);
				is.executeUpdate();
				is.close();
				Conection.close(is);
				Conection.close(dbc);
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Client insert " + e.getMessage());
			}
		}
		
		public List<T> createObj(ResultSet rs){
			List<T> l = new ArrayList<T>();
			try {
				while(rs.next()) {
					@SuppressWarnings("deprecation")
					T i = type.newInstance();
					for(Field f : type.getDeclaredFields()) {
						Object v = rs.getObject(f.getName());
						PropertyDescriptor pd = new PropertyDescriptor(f.getName(),type);
						Method m = pd.getWriteMethod();
						m.invoke(i, v);
					}
					l.add(i);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
			return l;
		}
	}
}
