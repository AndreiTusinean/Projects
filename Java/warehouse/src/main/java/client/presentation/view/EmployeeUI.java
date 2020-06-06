package client.presentation.view;

import client.presentation.controller.EmployeeController;

import javax.swing.*;

public class EmployeeUI {
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
    private JLabel l6 = new JLabel("Discount for bill");
    private static JTextField t1 = new JTextField("0");
    private static JTextField t2 = new JTextField("");
    private static JTextField t3 = new JTextField("");
    private static JTextField t4 = new JTextField("0");
    private static JTextField t5 = new JTextField("0");
    private static JTextField t6 = new JTextField("0");
    public static JButton create = new JButton("Create");
    public static JButton update = new JButton("Update");
    public static JButton delete = new JButton("Delete");
    public static JButton view = new JButton("View");
    public static JButton bill = new JButton("Generate bill");

    //CRUD stock

    public static int id, amount, price, location, discount;
    public static String name;
    static String employeeName;

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

    public static String getEmployeeName(){return employeeName;}

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

    public static void getInputDiscount() {
        try {
            discount = Integer.parseInt(t6.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Incorrect inputs");
        }
    }

    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private void initializeEmployee() {
        frame = new JFrame();
        panel = new JPanel();

        t1.setColumns(15);
        t2.setColumns(15);
        t3.setColumns(15);
        t4.setColumns(15);
        t5.setColumns(15);
        t6.setColumns(15);

        p1.add(l1);
        p1.add(t1);
        p2.add(l2);
        p2.add(t2);
        p3.add(l3);
        p3.add(t3);
        p4.add(l4);
        p4.add(t4);
        p5.add(l6);
        p5.add(t6);


        p6.add(create);
        p6.add(update);
        p6.add(delete);
        p6.add(view);
        p6.add(bill);
        p7.add(l5);
        p7.add(t5);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p7);
        panel.add(p6);
        panel.add(p5);

        EmployeeController.EmployeeUIActionListeners();

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(430, 300);
        frame.setTitle("Employee  stock operations");
    }

    public EmployeeUI(String employeeName) {
        this.employeeName = employeeName;
        initializeEmployee();
    }


}

