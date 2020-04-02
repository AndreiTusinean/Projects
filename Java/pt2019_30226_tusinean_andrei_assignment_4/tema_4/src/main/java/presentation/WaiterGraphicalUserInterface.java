package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.BaseProduct;
import business.MenuItem;
import business.Order;
import business.Restaurant;
import data.FileWriter;
import data.RestaurantSerializator;

public class WaiterGraphicalUserInterface extends JFrame {
	JPanel panel = new JPanel();
	static String filename = "Menu.txt";
	JLabel jl1 = new JLabel("OrderId");
	JLabel jl2 = new JLabel("Date");
	JLabel jl3 = new JLabel("Table");
	JLabel jl4 = new JLabel("Items");
	JTextField jtf1 = new JTextField("		");
	JTextField jtf2 = new JTextField("		");
	JTextField jtf3 = new JTextField("		");
	static JTextField jtf4 = new JTextField("		");
	static JTable jtbm = new JTable();
	JTable jtbo = new JTable();
	static int price = 0;
	Restaurant r = new Restaurant();
	Order otemp = new Order(0, "", 0);
	static Map<Order, ArrayList<MenuItem>> orders = new HashMap<Order, ArrayList<MenuItem>>();;

	public Order getOrder() {
		int id = Integer.parseInt(jtf1.getText().replaceAll("\\s+", ""));
		String date = jtf2.getText().replaceAll("\\s+", "");
		int table = Integer.parseInt(jtf3.getText().replaceAll("\\s+", ""));
		Order o = new Order(id, date, table);
		return o;
	}

	public static JTable update_table(Order o, JTable jt) {
		price=0;
		ArrayList<MenuItem> menu1 = new ArrayList<MenuItem>();
		ArrayList<MenuItem> menu2 = RestaurantSerializator.Deserialize(filename);
		final DefaultTableModel model = new DefaultTableModel();
		model.setColumnCount(0);
		model.addColumn("Id");
		model.addColumn("Date");
		model.addColumn("Table");
		model.addColumn("Items");
		model.addColumn("Total price");
		String aux[] = new String[5];
		String[] items = jtf4.getText().split(",");
		for (String a : items) {
			for (int i = 0; i < menu2.size(); i++) {
				BaseProduct bp = (BaseProduct) menu2.get(i);
				if (a.equals(bp.getName())) {
					price += bp.computePrice();
					menu1.add(bp);
				}
			}
		}
		orders.put(o, menu1);
		Iterator hmIterator = orders.entrySet().iterator();
		while (hmIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) hmIterator.next();
			menu1 = (ArrayList<MenuItem>) mapElement.getValue();
//			System.out.println(mapElement.getKey() + " : " + menu1.toString());
			Order onew = (Order) mapElement.getKey();
			aux[0] = "" + onew.getOrderID();
			aux[1] = onew.getDate();
			aux[2] = "" + onew.getTable();
			if(onew.getOrderID()==o.getOrderID()) {
				aux[3] = jtf4.getText();
			}
			
			aux[4] = "" + price;
			model.addRow(aux);
		}
		if(price!=0)
			ChefGraphicalUserInterface.cook(o.toString()+" : "+jtf4.getText());
		jt.setModel(model);
		return jt;
	}

	public WaiterGraphicalUserInterface() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

		JButton order = new JButton("Order");
		JButton bill = new JButton("Generate bill");

		AdministratorGraphicalUserInterface.update_table(jtbm);
		update_table(otemp, jtbo);
		JScrollPane jsp = new JScrollPane(jtbm);
		JScrollPane jsp2 = new JScrollPane(jtbo);

		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order o = getOrder();
				ArrayList<MenuItem> menu = RestaurantSerializator.Deserialize(filename);
				RestaurantSerializator.Serialize(filename, menu);
				AdministratorGraphicalUserInterface.update_table(jtbm);
				update_table(o, jtbo);
			}
		});

		bill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<MenuItem> menu = RestaurantSerializator.Deserialize(filename);
				Order o = getOrder();
				FileWriter.bill(o, jtf4.getText().replaceAll("\\s+", ""), price);
				RestaurantSerializator.Serialize(filename, menu);
				AdministratorGraphicalUserInterface.update_table(jtbm);
				update_table(o, jtbo);
			}
		});

		order.setBackground(new Color(60, 232, 77));
		bill.setBackground(new Color(65, 123, 216));

		p1.add(jsp, BorderLayout.PAGE_START);
		p1.add(jsp2, BorderLayout.PAGE_END);

		p2.add(jl1);
		p2.add(jtf1);
		p2.add(jl2);
		p2.add(jtf2);
		p2.add(jl3);
		p2.add(jtf3);
		p2.add(jl4);
		p2.add(jtf4);
		p2.add(order);
		p2.add(bill);

		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		p2.add(Box.createRigidArea(new Dimension(0, 5)));

		p1.setBackground(new Color(66, 244, 155));
		p2.setBackground(new Color(66, 244, 155));
		panel.add(p1);
		panel.add(p2);
		add(panel);

		panel.setBackground(new Color(66, 244, 155));
		setTitle("Waiter page");
		setSize(1200, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		WaiterGraphicalUserInterface wp = new WaiterGraphicalUserInterface();
	}
}
