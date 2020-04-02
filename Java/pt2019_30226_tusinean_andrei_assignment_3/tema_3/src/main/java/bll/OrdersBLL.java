package bll;

import java.util.NoSuchElementException;
import dao.OrdersDAO;
import model.Orders;

public class OrdersBLL {
	OrdersDAO od = new OrdersDAO();

	public static boolean validate(Orders o) {
		if (o.getId() < 0)
			throw new IllegalArgumentException("Id incorect!");
		if (o.getCnt() < 0)
			throw new IllegalArgumentException("Cantitate incorecta!");
		if (o.getName() == "" || o.getName() == " " || o.getName().matches("[0-9]+"))
			throw new IllegalArgumentException("Nume produs incorect!");
		if (o.getClientn() == "" || o.getClientn() == " " || o.getClientn().matches("[0-9]+"))
			throw new IllegalArgumentException("Nume client incorect!");
		else
			return true;
	}
	
	public Orders findID(int id) {
		Orders p = od.find(id);
		if (p == null)
			throw new NoSuchElementException("Order " + id + " nu exista!");
		return p;
	}

	public int insertOrders(Orders p) {
		if (validate(p))
			return OrdersDAO.insert(p);
		else
			return -1;
	}

	public static void deleteID(int id) {
		OrdersDAO od = new OrdersDAO();
		if (id < 0)
			throw new NoSuchElementException("Order " + id + " nu exista!");
		else
			od.delete(id);
	}

	public static void update(int id, String name, String clientn, int cnt) {
		Orders c = new Orders(id, name, clientn, cnt);
		if (validate(c))
			OrdersDAO.update(id, name, clientn, cnt);
		else
			throw new NoSuchElementException("Date incorecte!");
	}
}
