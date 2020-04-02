package business;

public class BaseProduct extends MenuItem {
	String name;
	int price;

	public BaseProduct(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int computePrice() {
		return price;
	}

	public String toString() {
		if(!name.contains("+"))
			return "BaseProduct: name= " + name + " ,price= " + price;
		else
			return "CompositeProduct: name= " + name + " ,price= " + price;
	}
}
