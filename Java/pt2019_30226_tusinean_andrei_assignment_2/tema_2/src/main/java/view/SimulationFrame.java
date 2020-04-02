package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import thread.SimulationManager;

public class SimulationFrame extends JFrame {
	private JPanel panel;
	JTextArea jta;
	JScrollPane jsp;
	JLabel l1 = new JLabel("Max processing time");
	JLabel l2 = new JLabel("Min processing time");
	JLabel l3 = new JLabel("Nr cozi");
	JLabel l4 = new JLabel("Nr clienti");
	public static JTextField tf1 = new JTextField("0000");
	public static JTextField tf2 = new JTextField("0000");
	public static JTextField tf3 = new JTextField("0000");
	public static JTextField tf4 = new JTextField("0000");
	JButton bstart = new JButton("Start");
	
	
	public  SimulationFrame() {
		jta = new JTextArea(30,50);
		jta.setEditable (false); 
		jsp = new JScrollPane(jta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		PrintStream printStream = new PrintStream(new CustomOutput(jta));
		System.setOut(printStream);
		System.setErr(printStream);
		
		panel = new JPanel();
		JPanel p1 = new JPanel();
		
		bstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				SimulationManager gen =  new SimulationManager();
				Thread t = new Thread(gen);
				t.start();
			}
		});
		
		p1.add(l1);
		p1.add(tf1);
		p1.add(l2);
		p1.add(tf2);
		p1.add(l3);
		p1.add(tf3);
		p1.add(l4);
		p1.add(tf4);
		p1.add(bstart);
		panel.add(p1);
		panel.add(jsp);
		add(panel);
		
		setSize(800,700);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new GridLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jta.setText("");
	}
	
	public static int getMax() {
		return Integer.parseInt(tf1.getText());
	}
	
	public static int getMin() {
		return Integer.parseInt(tf2.getText());
	}
	
	public static int getQ() {
		return Integer.parseInt(tf3.getText());
	}
	
	public static int getC() {
		return Integer.parseInt(tf4.getText());
	}
	

}
