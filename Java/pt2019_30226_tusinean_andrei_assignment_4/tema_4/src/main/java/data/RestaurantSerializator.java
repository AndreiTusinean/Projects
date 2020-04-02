package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import business.BaseProduct;
import business.CompositeProduct;
import business.MenuItem;

public class RestaurantSerializator {

	public static void Serialize(String filename, ArrayList<MenuItem> menu) {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeInt(menu.size());
			for (MenuItem i : menu) {
				out.writeObject(i);
			}
			out.close();
			file.close();
		} catch (IOException ex) {
			System.out.println("IOException is caught at write");
		}
	}

	public static ArrayList<MenuItem> Deserialize(String filename) {
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		CompositeProduct c = new CompositeProduct();
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			int sz = in.readInt();
			MenuItem aux;
			for (int i = 0; i < sz; i++) {
				aux = (MenuItem) in.readObject();
				if (aux.getClass() == c.getClass()) {
					c = (CompositeProduct) aux;
					String name = "";
					int p = 0;
					for (int j = 0; j < c.compP.size(); j++) {
						name += c.compP.get(j).getName() + "+";
						p += c.compP.get(j).getPrice();
					}
					name = name.substring(0, name.length() - 3);
					BaseProduct bp = new BaseProduct(name, p);
					menu.add(bp);
				} else {
					menu.add(aux);
				}
			}
			in.close();
			file.close();
		} catch (IOException ex) {
			System.out.println("IOException is caught at read");
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
		return menu;
	}
}
