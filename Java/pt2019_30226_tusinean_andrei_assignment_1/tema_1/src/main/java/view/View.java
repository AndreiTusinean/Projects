package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Polinom;

public class View extends JFrame {
	private JPanel panel;
	static JButton b11, b12, b21, b22, b3, b4, b5, b6;
	static JLabel l1, l2, l3;
	static JTextField tf1, tf2, tf3;
	static Polinom p = new Polinom();
	static View v = new View();
	
	public View() {
		this.panel = new JPanel();
		this.b11 = new JButton("Integrare");
		this.b12 = new JButton("Integrare");
		this.b21 = new JButton("Derivare");
		this.b22 = new JButton("Derivare");
		this.b3 = new JButton(" + ");
		this.b4 = new JButton(" - ");
		this.b5 = new JButton(" * ");
		this.b6 = new JButton(" / ");
		this.l1 = new JLabel("Polinom 1 ");
		this.l2 = new JLabel("Polinom 2");
		this.l3 = new JLabel("Rezultat");
		this.tf1 = new JTextField("                                       		");
		this.tf2 = new JTextField("                                       		");
		this.tf3 = new JTextField("                                       						");
	}

	public void addAddL() {
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Polinom p1 = p.parse(v.getPol(0));
				Polinom p2 = p.parse(v.getPol(1));
				String s = p.addPol(p1, p2).toString();
				v.result(s);
			}
		});
	}

	public void addIntL1() {
		b11.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Polinom p1 = p.parse(v.getPol(0));
			String s = p.intPol(p1).toString();
			v.result(s);
		}
		});
	}

	public void addIntL2() {
		b12.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Polinom p2 = p.parse(v.getPol(1));
			String s = p.intPol(p2).toString();
			v.result(s);
		}
		});
	}
	
	public void addDerL1() {
		b21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Polinom p1 = p.parse(v.getPol(0));
				String s = p.derPol(p1).toString();
				v.result(s);
			}
		});
	}

	public void addDerL2() {
		b22.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Polinom p2 = p.parse(v.getPol(1));
			String s = p.derPol(p2).toString();
			v.result(s);
		}
		});
	}
	
	public void addSubL() {
		b4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Polinom p1 = p.parse(v.getPol(0));
			Polinom p2 = p.parse(v.getPol(1));
			String s = p.subPol(p1, p2).toString();
			v.result(s);
		}
		});
	}
	
	public void addMulL() {
		b5.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Polinom p1 = p.parse(v.getPol(0));
			Polinom p2 = p.parse(v.getPol(1));
			String s = p.mulPol(p1, p2).toString();
			v.result(s);
		}
		});
	}

	public void addDivL( ) {
		b6.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Polinom p1 = p.parse(v.getPol(0));
			Polinom p2 = p.parse(v.getPol(1));
			String s = p.divPol(p1, p2).toString();
			v.result(s);
		}
		});
	}
	
	public String getPol(int i) {
		if (i == 0)
			return tf1.getText();
		if (i == 1)
			return tf2.getText();
		return null;
	}

	public void result(String s) {
		this.tf3.setText(s);
	}

	public void buidUI() {
		FlowLayout experimentLayout = new FlowLayout();
		JFrame frame = new JFrame("Operatii cu polinoame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();

		panel1.setLayout(experimentLayout);

		panel1.add(b11);
		panel1.add(l1);
		panel1.add(tf1);
		panel1.add(b21);

		panel2.add(b3);
		panel2.add(b4);
		panel2.add(b5);
		panel2.add(b6);

		panel3.add(b12);
		panel3.add(l2);
		panel3.add(tf2);
		panel3.add(b22);

		panel4.add(l3);
		panel4.add(tf3);

		JPanel p = new JPanel();
		p.add(panel1);
		p.add(panel2);
		p.add(panel3);
		p.add(panel4);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

		frame.setContentPane(p);
		frame.setVisible(true);
	}

	

}
