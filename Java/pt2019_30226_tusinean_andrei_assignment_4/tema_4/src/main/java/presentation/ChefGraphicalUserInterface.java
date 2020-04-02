package presentation;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ChefGraphicalUserInterface extends JFrame{
	 
	JPanel panel = new JPanel();
	static String filename = "Menu.txt";
	JLabel jl1 = new JLabel("Cooking:");
	static JLabel jl2 = new JLabel("");
	JTextField jtf1 = new JTextField("		");
	static JTable jtb = new JTable();
	
	public static void cook(String s) {
		jl2.setText(s);
	}
	
	public ChefGraphicalUserInterface() {
		JPanel p1 = new JPanel();
		p1.add(jl1);
		p1.add(jl2);
		jl2.setText("This");
		p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
		p1.add(Box.createRigidArea(new Dimension(0, 5)));

		p1.setBackground(new Color(66, 244, 155));
		panel.add(p1);
		add(panel);
		panel.setBackground(new Color(66, 244, 155));
		setBackground(new Color(66, 244, 155));
		setTitle("Chef page");
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		ChefGraphicalUserInterface cp = new ChefGraphicalUserInterface();
	}
}
