package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Restaurant implements Serializable{
	public static Map<Order,ArrayList<MenuItem>> orders;
	public static ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
	
	public Restaurant() {
		orders = new HashMap<Order,ArrayList<MenuItem>>();
	}
	
}
