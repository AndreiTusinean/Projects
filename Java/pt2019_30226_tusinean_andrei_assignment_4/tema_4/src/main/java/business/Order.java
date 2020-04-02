package business;

public class Order {
	private int orderID;
	private String date;
	private int table;

	public Order(int orderID, String date, int table) {
		this.orderID = orderID;
		this.date = date;
		this.table = table;
	}
	public Order() {}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}

	public int HashCode() {
		int result = 1;
		result = 31 * result + ((date == null) ? 0 : date.hashCode());
		result = 31 * result + orderID;
		result = 31 * result + table;
		return result;
	}

	@Override
	public String toString() {
		return "Order: orderID: " + orderID + ", date: " + date + ", table: " + table;
	}
}
