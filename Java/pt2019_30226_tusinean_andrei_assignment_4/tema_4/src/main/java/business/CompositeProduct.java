package business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompositeProduct extends MenuItem {
	public List<BaseProduct> compP;

	public CompositeProduct() {
		compP = new ArrayList<BaseProduct>();
	}

	public int computePrice() {
		int p = 0;
		for (BaseProduct m : compP) {
			p = p + m.computePrice();
		}
		return p;
	}
	
	public String toString() {
		String s = "CompositeProduct: ";
		for (MenuItem m : compP) {
			s += m.toString();
		}
		return s;
	}
}
