package bll;

import java.util.NoSuchElementException;
import dao.ClientDAO;
import model.Client;

public class ClientBLL {
	ClientDAO cd = new ClientDAO();
	
	public static boolean validate(Client c) {
		if (c.getId() < 0)
			throw new IllegalArgumentException("Id incorect!");
		if (c.getName() == "" || c.getName() == " " || c.getName().matches("[0-9]+"))
			throw new IllegalArgumentException("Nume incorect!");
		else
			return true;
	}
	
	public Client findID(int id) {
		Client c = cd.find(id);
		if(c==null)
			throw new NoSuchElementException("Clientul "+id+" nu exista!");
		return c;
	}
	
	public int insertClient(Client c) {
		if(validate(c))
			return ClientDAO.insert(c);
		else return -1;
	}
	
	public static void deleteID(int id) {
		ClientDAO cd = new ClientDAO();
		if(id<0)
			throw new NoSuchElementException("Clientul "+id+" nu exista!");
		else
			cd.delete(id);
	}
	
	public static void update(int id,String name) {
		Client c = new Client(id,name);
		if(validate(c))
			ClientDAO.update(id, name);
		else
			throw new NoSuchElementException("Date incorecte!");
	}
}
