package presentation;

import domain.AdminOperations;
import domain.EmployeeClientOperations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static domain.EmployeeClientOperations.addClient;

public class EmployeeBalanceActions {
    private static String emplName;
    private JFrame frame;
    private JPanel panel;
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JLabel l1 = new JLabel("Account transfering");
    private JLabel l3 = new JLabel("Transfer amount");
    private JLabel l2 = new JLabel("Account receiving");
    private JTextField t1 = new JTextField("");
    private JTextField t2 = new JTextField("");
    private JTextField t3 = new JTextField("");
    private JButton transfer = new JButton("Transfer");

    //money between accounts.
    //Process utilities bills.

    int account1, account2, amount;
    String msg = "";
    AdminOperations ad = new AdminOperations();

    private void getInputs() {
        try {
            account1 = Integer.parseInt(t1.getText().trim());
            account2 = Integer.parseInt(t2.getText().trim());
            amount = Integer.parseInt(t3.getText().trim());
        } catch (NumberFormatException e) {
        }
    }

    private void message(JPanel panel, String msg) {
        if (!msg.equals(""))
            JOptionPane.showMessageDialog(panel, msg);
        else
            JOptionPane.showMessageDialog(panel, "Task failed");
    }

    private void actionListeners() {
        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getInputs();
                    msg = AdminOperations.transferBalance(account1,account2,amount);
                    System.out.println(msg);
                    if(!msg.equals("Incorrect values")){
                        System.out.println(msg);
                        AdminOperations.insertEmployeeAction(GUI.employeeName(), "Transfered "+amount+ " from account" + account1 + " to account " + account2);
                    }
                    message(panel, msg);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initializeTransfer() {
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
        p4.add(transfer);

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
        frame.setTitle("Transfer");
    }

    public EmployeeBalanceActions(String emplName) {
        initializeTransfer();
        emplName = GUI.employeeName();
        System.out.println(emplName);
    }

    public static void main(String[] args) {
        EmployeeBalanceActions empBA = new EmployeeBalanceActions(emplName);
    }
}
