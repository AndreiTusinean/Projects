package client.presentation.view;

import client.presentation.controller.AdminController;

import javax.swing.*;

public class AdminUI {
    private static String emplName;
    private JFrame frame;
    private JPanel panel;
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JPanel p5 = new JPanel();
    private JPanel p6 = new JPanel();
    private JPanel p7 = new JPanel();
    private JPanel p8 = new JPanel();
    private JLabel l1 = new JLabel("Id");
    private JLabel l2 = new JLabel("Name");
    private JLabel l3 = new JLabel("Pass");
    private JLabel l4 = new JLabel("Type");
    private JLabel l5 = new JLabel("Report on warehouse nr:");
    private static JTextField t1 = new JTextField("0");
    private static JTextField t2 = new JTextField("");
    private static JTextField t3 = new JTextField("");
    private static JTextField t4 = new JTextField("");
    private static JTextField t5 = new JTextField("");
    public static JButton create = new JButton("Create");
    public static JButton update = new JButton("Update");
    public static JButton delete = new JButton("Delete");
    public static JButton view = new JButton("View");
    public static JButton grant = new JButton("Grant request");
    public static JButton viewRequests = new JButton("List Item Update Requests");
    public static JButton report = new JButton("Make report");
    public static JCheckBox jc1 = new JCheckBox("Pdf");
    public static JCheckBox jc2 = new JCheckBox("Txt");

    public static int id, location;
    public static String name, pass, type;
    public static String rep = "pdf";

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setPass(String p) {
        pass = p;
    }

    public String getPass() {
        return pass;
    }

    public void setId(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }

    public void setLocation(int l) {
        location = l;
    }

    public int getLocation() {
        return location;
    }

    public static void getInputs() {
        name = t2.getText().trim();
        pass = t3.getText().trim();
        type = t4.getText().trim();
        try {
            id = Integer.parseInt(t1.getText().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println(id);
    }

    public static void getInputId() {
        try {
            id = Integer.parseInt(t1.getText().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void getInputLocation() {
        try {
            location = Integer.parseInt(t5.getText().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private void initializeAdmin() {
        frame = new JFrame();
        panel = new JPanel();

        t1.setColumns(15);
        t2.setColumns(15);
        t3.setColumns(15);
        t4.setColumns(15);
        t5.setColumns(15);

        p1.add(l1);
        p1.add(t1);
        p2.add(l2);
        p2.add(t2);
        p3.add(l3);
        p3.add(t3);
        p4.add(l4);
        p4.add(t4);
        p5.add(create);
        p5.add(update);
        p5.add(delete);
        p5.add(view);
        p6.add(l5);
        p6.add(t5);
        p7.add(jc1);
        p7.add(jc2);
        p7.add(report);
        p8.add(viewRequests);
        p8.add(grant);


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p5);
        panel.add(p6);
        panel.add(p7);
        panel.add(p8);

        jc1.setSelected(true);
        AdminController.AdminUIActionListeners();

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 300);
        frame.setTitle("Operations on managers");
    }

    public AdminUI() {
        initializeAdmin();
    }
    
}
