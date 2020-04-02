package view;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import dao.ProductDAO;
import model.Client;
import model.Orders;
import model.Product;

public class View extends JFrame {
	ClientBLL cb = new ClientBLL();
	ProductBLL pb =  new ProductBLL();
	OrdersBLL ob =  new OrdersBLL();
	private JPanel panel = new JPanel();
	
	JTable jtc,jtp;
	JLabel cl = new JLabel("Client : ");
	JLabel cl1 = new JLabel("Id ");
	JLabel cl2 = new JLabel("Nume");
	public static JTextField ctf1 = new JTextField("            ");
	public static JTextField ctf2 = new JTextField("            ");
	JButton cin = new JButton("Insert");
	JButton cfn = new JButton("Find");
	JButton cup = new JButton("Update");
	JButton cdl = new JButton("Delete");

	JLabel pl = new JLabel("Produs : ");
	JLabel pl1 = new JLabel("Id ");
	JLabel pl2 = new JLabel("Nume");
	JLabel pl3 = new JLabel("Cantitate");
	public static JTextField ptf1 = new JTextField("            ");
	public static JTextField ptf2 = new JTextField("            ");
	public static JTextField ptf3 = new JTextField("            ");
	JButton pin = new JButton("Insert");
	JButton pfn = new JButton("Find");
	JButton pup = new JButton("Update");
	JButton pdl = new JButton("Delete");

	JLabel ol = new JLabel("Order : ");
	JLabel ol1 = new JLabel("Id ");
	JLabel ol2 = new JLabel("Nume");
	JLabel ol3 = new JLabel("Client");
	JLabel ol4 = new JLabel("Cantitate");
	public static JTextField otf1 = new JTextField("            ");
	public static JTextField otf2 = new JTextField("            ");
	public static JTextField otf3 = new JTextField("            ");
	public static JTextField otf4 = new JTextField("            ");
	JButton oin = new JButton("Insert");
	JButton ofn = new JButton("Find");
	JButton oup = new JButton("Update");
	JButton odl = new JButton("Delete");
	public JTable createTableC(List<Client> c) {
		String[] col = { "Id", "Name" };
		Object[][] data = new Object[c.size()][2];
		for (int i = 0; i < c.size(); i++) {
			data[i][0] = c.get(i).getId();
			data[i][1] = c.get(i).getName();
		}
		JTable jt = new JTable(data, col);
		return jt;
	}

	public JTable createTableP(List<Product> p) {
		String[] col = { "Id", "Name", "Cnt" };
		Object[][] data = new Object[p.size()][3];
		for (int i = 0; i < p.size(); i++) {
			data[i][0] = p.get(i).getId();
			data[i][1] = p.get(i).getName();
			data[i][2] = p.get(i).getCnt();
		}
		JTable jt = new JTable(data, col);
		return jt;
	}

	public static Client clData() {
		int id = Integer.parseInt(ctf1.getText().replaceAll("\\s+",""));
		String name =  ctf2.getText().replaceAll("\\s+","");
		Client c = new Client(id,name);
		return c;
	}
	
	public static Product pdData() {
		int id = Integer.parseInt(ptf1.getText().replaceAll("\\s+",""));
		String name =  ptf2.getText().replaceAll("\\s+","");
		int cnt = Integer.parseInt(ptf3.getText().replaceAll("\\s+",""));
		Product p  = new Product(id,name,cnt);
		return p;
	}
	
	public static Orders odData() {
		int id = Integer.parseInt(otf1.getText().replaceAll("\\s+",""));
		String name =  otf2.getText().replaceAll("\\s+","");
		String clientn = otf3.getText().replaceAll("\\s+","");
		int cnt = Integer.parseInt(otf4.getText().replaceAll("\\s+",""));
		Orders o  = new Orders(id,name,clientn,cnt);
		return o;
	}
	
	public void cButtons() {
		cin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cb.insertClient(clData());
			}
		});
		cfn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(cb.findID(clData().getId()).toString());
			}
		});
		cup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client c = clData();
				ClientBLL.update(c.getId(), c.getName());
			}
		});
		cdl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientBLL.deleteID(clData().getId());
			}
		});
	}
	
	public void pButtons() {
		pin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pb.insertProduct(pdData());
			}
		});
		pfn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(pb.findID(Integer.parseInt(ptf1.getText().replaceAll("\\s+",""))).toString());
			}
		});
		pup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product p = pdData();
				ProductBLL.update(p.getId(), p.getName(),p.getCnt());
			}
		});
		pdl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductBLL.deleteID(Integer.parseInt(ptf1.getText().replaceAll("\\s+","")));
			}
		});
	}
	
	public void oButtons() {
		oin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ob.insertOrders(odData());
			}
		});
		ofn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(ob.findID(Integer.parseInt(otf1.getText().replaceAll("\\s+",""))).toString());
			}
		});
		oup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Orders o = odData();
				OrdersBLL.update(o.getId(), o.getName(),o.getClientn(),o.getCnt());
			}
		});
		odl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdersBLL.deleteID(Integer.parseInt(otf1.getText().replaceAll("\\s+","")));
			}
		});
	}
	
	public View() {
		JPanel c = new JPanel();	
		c.add(cl);
		c.add(cl1);
		c.add(ctf1);
		c.add(cl2);
		c.add(ctf2);
		c.add(cl2);
		c.add(ctf2);
		c.add(cin);
		c.add(cfn);
		c.add(cup);
		c.add(cdl);
		cButtons();
		
		JPanel p = new JPanel();
		p.add(pl);
		p.add(pl1);
		p.add(ptf1);
		p.add(pl2);
		p.add(ptf2);
		p.add(pl2);
		p.add(ptf2);
		p.add(pl3);
		p.add(ptf3);
		p.add(pin);
		p.add(pfn);
		p.add(pup);
		p.add(pdl);
		pButtons();
		
		JPanel o = new JPanel();
		o.add(ol);
		o.add(ol1);
		o.add(otf1);
		o.add(ol2);
		o.add(otf2);
		o.add(ol2);
		o.add(otf2);
		o.add(ol3);
		o.add(otf3);
		o.add(ol4);
		o.add(otf4);
		o.add(oin);
		o.add(ofn);
		o.add(oup);
		o.add(odl);
		oButtons();

		JPanel jt = new JPanel();
		jtc = createTableC(ClientDAO.findAll());
		jtp = createTableP(ProductDAO.findAll());
		jt.add(jtc);
		jt.add(jtp);

		panel.add(c);
		panel.add(p);
		panel.add(o);
		panel.add(jt);
		add(panel);
		
		setSize(800, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new GridLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		View v = new View();
	}
}
