package presentation;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StartPage extends JFrame{
	private JPanel panel = new JPanel();
	public StartPage() {
		JButton chef   = new JButton("Chef page");
		JButton admin  = new JButton("Admin page");
		JButton waiter = new JButton("Waiter page");
		JLabel jl = new JLabel("Restaurant");
		panel.setLayout(null);
		
		jl.setBounds(110, 50, 120, 35);
		chef.setBounds(80, 80, 120, 35);
		admin.setBounds(80, 110, 120, 35);
		waiter.setBounds(80, 140, 120, 35);
		chef.setBackground(new Color(65, 123, 216));
		admin.setBackground(new Color(65, 123, 216));
		waiter.setBackground(new Color(65, 123, 216));
		
		chef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChefGraphicalUserInterface chefUI = new ChefGraphicalUserInterface();
			}
		});
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministratorGraphicalUserInterface adminUI = new AdministratorGraphicalUserInterface();
			}
		});
		waiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WaiterGraphicalUserInterface waiterUI = new WaiterGraphicalUserInterface();
			}
		});
		
		panel.add(jl);
		panel.add(chef);
		panel.add(admin);
		panel.add(waiter);
		add(panel);
		
		panel.setBackground(new Color(66, 244, 155));
		setTitle("Start page");
		setSize(300,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		StartPage sp = new StartPage();
	}
}
