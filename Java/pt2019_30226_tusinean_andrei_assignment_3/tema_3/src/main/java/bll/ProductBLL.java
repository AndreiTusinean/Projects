package bll;

import java.util.NoSuchElementException;
import dao.ProductDAO;
import model.Product;

public class ProductBLL {
	ProductDAO pd = new ProductDAO();

	public static boolean validate(Product p) {
		if (p.getId() < 0)
			throw new IllegalArgumentException("Id incorect!");
		if (p.getCnt() < 0)
			throw new IllegalArgumentException("Cantitate incorecta!");
		if (p.getName() == "" || p.getName() == " " || p.getName().matches("[0-9]+"))
			throw new IllegalArgumentException("Nume incorect!");
		else
			return true;
	}

	public Product findID(int id) {
		Product p = pd.find(id);
		if (p == null)
			throw new NoSuchElementException("Produsul " + id + " nu exista!");
		return p;
	}

	public int insertProduct(Product p) {
		if (validate(p))
			return ProductDAO.insert(p);
		else
			return -1;
	}

	public static void deleteID(int id) {
		ProductDAO pd = new ProductDAO();
		if (id < 0)
			throw new NoSuchElementException("Clientul " + id + " nu exista!");
		else
			pd.delete(id);
	}

	public static void update(int id, String name, int cnt) {
		Product c = new Product(id, name, cnt);
		if (validate(c))
			ProductDAO.update(id, name, cnt);
		else
			throw new NoSuchElementException("Date incorecte!");
	}
}
