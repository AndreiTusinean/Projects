package client.presentation.view;

import client.ClientMessage;
import client.presentation.controller.ManagerController;

import javax.swing.*;

public class ManagerUI {

    private JFrame frame;
    private JPanel panel;
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JPanel p5 = new JPanel();
    private JPanel p6 = new JPanel();
    private JPanel p7 = new JPanel();
    private JLabel l1 = new JLabel("Id");
    private JLabel l2 = new JLabel("Name");
    private JLabel l3 = new JLabel("Amount");
    private JLabel l4 = new JLabel("Price");
    private JLabel l5 = new JLabel("Location");
    private static JTextField t1 = new JTextField("0");
    private static JTextField t2 = new JTextField("");
    private static JTextField t3 = new JTextField("");
    private static JTextField t4 = new JTextField("");
    private static JTextField t5 = new JTextField("");
    public static JButton create = new JButton("Create");
    public static JButton update = new JButton("Request stock update");
    public static JButton delete = new JButton("Delete");
    public static JButton view = new JButton("View");
    public static JTextArea jta = new JTextArea("");
    public static JCheckBox jc1 = new JCheckBox("Amount");
    public static JCheckBox jc2 = new JCheckBox("Orders");
    public static String orderType = "orders";

    public static int id, amount, price, location;
    public static String name;
    int ManagerLocation;

    public static void setT2(String name){
        t2.setText(name);
    }

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
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

    public void setManagerLocation(int l) {
        ManagerLocation = l;
    }

    public int getManagerLocation() {
        return ManagerLocation;
    }

    public static void getInputs() {
        name = t2.getText().trim();
        try {
            id = Integer.parseInt(t1.getText().trim());
            amount = Integer.parseInt(t3.getText().trim());
            price = Integer.parseInt(t4.getText().trim());
            location = Integer.parseInt(t5.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Incorrect inputs");
        }
    }

    public static void getInputId() {
        try {
            id = Integer.parseInt(t1.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Incorrect inputs");
        }
    }

    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private void initializeManager() {
        frame = new JFrame();
        panel = new JPanel();

        t1.setColumns(15);
        t2.setColumns(15);
        t3.setColumns(15);
        t4.setColumns(15);
        t5.setColumns(15);
        jc1.setSelected(true);

        p1.add(l1);
        p1.add(t1);
        p2.add(l2);
        p2.add(t2);
        p3.add(l3);
        p3.add(t3);
        p4.add(l4);
        p4.add(t4);
        p6.add(create);
        p6.add(update);
        p6.add(delete);
        p6.add(view);
        p7.add(l5);
        p7.add(t5);
        p5.add(jta);
        p5.add(jc1);
        p5.add(jc2);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p7);
        panel.add(p6);
        panel.add(p5);

        ManagerController.ManagerUIActionListeners();
        ClientMessage.viewToClient("getRanking,"+orderType);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 300);
        frame.setTitle("Manager stock operations");
    }

    public ManagerUI(int ManagerLocation) {
        this.ManagerLocation = ManagerLocation;
        initializeManager();
    }

    public static void main(String[] args) {
        ManagerUI m = new ManagerUI(1);
    }
}
