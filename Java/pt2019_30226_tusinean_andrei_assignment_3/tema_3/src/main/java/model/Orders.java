package model;

public class Orders {
	int id, cnt;
	String name, clientn;

	public Orders() {}

	public Orders(int id, String name, String clientn, int cnt) {
		this.id = id;
		this.name = name;
		this.clientn = clientn;
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

	public String getClientn() {
		return clientn;
	}

	public void setClientn(String clientn) {
		this.clientn = clientn;
	}

	public String toString() {
		return "Order " + id + " : " + name + ",client : " + clientn + ", " + "cantitate : " + cnt;
	}
}
