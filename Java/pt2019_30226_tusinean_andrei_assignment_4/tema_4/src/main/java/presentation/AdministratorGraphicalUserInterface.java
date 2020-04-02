package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.BaseProduct;
import business.MenuItem;
import business.Restaurant;
import data.RestaurantSerializator;

public class AdministratorGraphicalUserInterface extends JFrame {
	JPanel panel = new JPanel();
	static String filename = "Menu.txt";
	JLabel jl1 = new JLabel("Name");
	JLabel jl2 = new JLabel("Price");
	JTextField jtf1 = new JTextField("		");
	JTextField jtf2 = new JTextField("		");
	static JTable jtb = new JTable();
	
	public static JTable update_table(JTable jt) {
		ArrayList<MenuItem> menu2 = RestaurantSerializator.Deserialize(filename);
		final DefaultTableModel model = new DefaultTableModel();
		model.setColumnCount(0);
        model.addColumn("Type");
        model.addColumn("Name");
        model.addColumn("Price");
		String aux[] = new String[3];
		for (int i = 0; i < menu2.size(); i++) {
			if (menu2.get(i).toString().contains("+")) {
				aux[0]="CompositeProduct";
			} else {
				aux[0]="BaseProduct";
			}
			BaseProduct bp = (BaseProduct) menu2.get(i);
			aux[1]=bp.getName();
			aux[2]=""+bp.getPrice();
			model.addRow(aux);
		}
		jt.setModel(model);
		return jt;
	}
	
	public AdministratorGraphicalUserInterface() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

		JButton save = new JButton("Save");
		JButton edit = new JButton("Edit");
		JButton delete = new JButton("Delete");
		
		update_table(jtb);
		JScrollPane jsp = new JScrollPane(jtb);

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = jtf1.getText().replaceAll("\\s+", "");
				int price = Integer.parseInt(jtf2.getText().replaceAll("\\s+", ""));
				BaseProduct bp1 = new BaseProduct(name, price);
				ArrayList<MenuItem> menu = RestaurantSerializator.Deserialize(filename);
				menu.add(bp1);
				RestaurantSerializator.Serialize(filename, menu);
				update_table(jtb);
				update_table(WaiterGraphicalUserInterface.jtbm);
			}
		});

		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = jtf1.getText().replaceAll("\\s+", "");
				int price = Integer.parseInt(jtf2.getText().replaceAll("\\s+", ""));
				BaseProduct bp1 = new BaseProduct(name, price);
				ArrayList<MenuItem> menu = RestaurantSerializator.Deserialize(filename);
				for (int i = 0; i < menu.size(); i++) {
					BaseProduct aux = (BaseProduct) menu.get(i);
					if(aux.getName().equals(bp1.getName())) {
						aux.setPrice(bp1.getPrice());
						menu.remove(i);
						menu.add(aux);
						break;
					}
				}
				RestaurantSerializator.Serialize(filename, menu);
				update_table(jtb);
				update_table(WaiterGraphicalUserInterface.jtbm);
			}
		});

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = jtf1.getText().replaceAll("\\s+", "");
				BaseProduct bp1 = new BaseProduct(name, 0);
				ArrayList<MenuItem> menu = RestaurantSerializator.Deserialize(filename);
				for (int i = 0; i < menu.size(); i++) {
					BaseProduct aux = (BaseProduct) menu.get(i);
					if(aux.getName().equals(bp1.getName())) {
						menu.remove(i);
						break;
					}
				}
				RestaurantSerializator.Serialize(filename, menu);
				update_table(jtb);
				update_table(WaiterGraphicalUserInterface.jtbm);
			}
		});

		save.setBackground(new Color(60, 232, 77));
		edit.setBackground(new Color(65, 123, 216));
		delete.setBackground(new Color(232, 60, 60));
		p1.add(jsp, BorderLayout.CENTER);
		p2.add(jl1);
		p2.add(jtf1);
		p2.add(jl2);
		p2.add(jtf2);
		p2.add(save);
		p2.add(edit);
		p2.add(delete);

		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		p2.add(Box.createRigidArea(new Dimension(0, 5)));

		p1.setBackground(new Color(66, 244, 155));
		p2.setBackground(new Color(66, 244, 155));
		panel.add(p1);
		panel.add(p2);
		add(panel);

		panel.setBackground(new Color(66, 244, 155));
		setTitle("Admin page");
		setSize(700, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		AdministratorGraphicalUserInterface ap = new AdministratorGraphicalUserInterface();
	}
}
