package presentation;

import domain.AdminOperations;
import domain.EmployeeClientOperations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EmployeeBills extends JFrame {
    private static String emplName;
    private JFrame frame;
    private JPanel panel;
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JPanel p5 = new JPanel();
    private JLabel l1 = new JLabel("Account");
    private JLabel l3 = new JLabel("Amount");
    private JLabel l2 = new JLabel("Bill");
    private JTextField t1 = new JTextField("");
    private JTextField t2 = new JTextField("");
    private JTextField t3 = new JTextField("");
    private JButton bill = new JButton("Pay bill");

    //money between accounts.
    //Process utilities bills.

    int account, amount;
    String billDestination;
    String msg = "";
    AdminOperations ad = new AdminOperations();

    private void getInputs() {
        billDestination = t2.getText().trim();
        try {
            account = Integer.parseInt(t1.getText().trim());
            amount = Integer.parseInt(t3.getText().trim());
        } catch (NumberFormatException e) {
            msg = "Incorrect input";
        }
    }

    private void message(JPanel panel, String msg) {
        if (!msg.equals(""))
            JOptionPane.showMessageDialog(panel, msg);
        else
            JOptionPane.showMessageDialog(panel, "Task failed");
    }

    private void actionListeners() {
        bill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getInputs();
                    if (account > 0 && amount > 0 && !billDestination.equals("")){
                        msg = AdminOperations.payBill(account, amount);
                        System.out.println(msg);
                        if (!msg.equals("Incorrect values")) {
                            AdminOperations.insertEmployeeAction(GUI.employeeName(), "Payed bill to " + billDestination + " amounting " + amount + " USD from account " + account);
                        }
                    }
                    message(panel, msg);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initializeBills() {
        frame = new JFrame();
        panel = new JPanel();

        t1.setColumns(15);
        t2.setColumns(15);
        t3.setColumns(15);

        p1.add(l1);
        p1.add(t1);
        p2.add(l2);
        p2.add(t2);
        p3.add(l3);
        p3.add(t3);
        p4.add(bill);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);


        actionListeners();

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 300);
        frame.setTitle("Pay bills");
    }

    public EmployeeBills(String emplName) {
        initializeBills();
        emplName = GUI.employeeName();
        System.out.println(emplName);
    }

    public static void main(String[] args) {
        EmployeeBills empBl = new EmployeeBills(emplName);
    }
}
