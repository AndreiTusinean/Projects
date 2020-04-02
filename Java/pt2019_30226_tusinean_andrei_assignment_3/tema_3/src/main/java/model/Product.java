package model;

public class Product {
	int id, cnt;
	String name;

	public Product() {}
	
	public Product(int id, String name, int cnt) {
		this.id = id;
		this.name = name;
		this.cnt = cnt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Product " + id + " : " + name + ", cnt : " + cnt;
	}
}
