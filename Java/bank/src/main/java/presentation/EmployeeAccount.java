package presentation;

import domain.AdminOperations;
import domain.EmployeeAccountOperations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import static domain.EmployeeAccountOperations.addAccount;
import static domain.EmployeeAccountOperations.updateAccount;
import static domain.EmployeeAccountOperations.deleteAccount;
import static domain.EmployeeAccountOperations.viewAccount;


public class EmployeeAccount {
    private static String emplName;
    private JFrame frame;
    private JPanel panel;
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JPanel p5 = new JPanel();
    private JPanel p6 = new JPanel();
    private JLabel l1 = new JLabel("Id");
    private JLabel l2 = new JLabel("Type");
    private JLabel l3 = new JLabel("Balance");
    private JLabel l4 = new JLabel("Date");
    private JLabel l5 = new JLabel("Owner");
    private JTextField t1 = new JTextField("");
    private JTextField t2 = new JTextField("");
    private JTextField t3 = new JTextField("");
    private JTextField t4 = new JTextField("dd-mm-yyyy");
    private JTextField t5 = new JTextField("");
    private JButton create = new JButton("Create");
    private JButton update = new JButton("Update");
    private JButton delete = new JButton("Delete");
    private JButton view = new JButton("View");

    //Create/update/delete/view client account (account information: identification number, type, amount of money, date of creation).

    int id, balance;
    String type, date, owner;
    String msg = "";
    EmployeeAccountOperations ec = new EmployeeAccountOperations();

    private static boolean verifDate(String str) {
        int[] d = new int[3];
        try {
            str.matches("\\d{2}-\\d{2}-\\d{4}");
            String[] s = str.split("-");
            d[0] = Integer.parseInt(s[0]);
            d[1] = Integer.parseInt(s[1]);
            d[2] = Integer.parseInt(s[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        return d[0] > 0 && d[0] <= 31 && d[1] > 0 && d[1] <= 12 && d[2] > 0 && d[2] <= 2100;
    }

    private boolean checkInputs(String type, String date, String owner, int id, int balance) {
        return verifDate(date) && !type.equals("") && !owner.equals("") && id > 0 && balance >= 0;
    }

    private void getInputs() {
        type = t2.getText().trim();
        date = t4.getText().trim();
        owner = t5.getText().trim();
        if (!checkInputs(type, date, owner, id, balance))
            message(panel, "Failed input parsing");
        try {
            id = Integer.parseInt(t1.getText().trim());
            balance = Integer.parseInt(t3.getText().trim());
        } catch (NumberFormatException e) {
            msg = "Failed input parsing";
        }
    }

    private void message(JPanel panel, String msg) {
        if (!msg.equals(""))
            JOptionPane.showMessageDialog(panel, msg);
        else
            JOptionPane.showMessageDialog(panel, "Task failed");
    }

    private void actionListeners() {
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    msg = "";
                    getInputs();
                    if (checkInputs(type, date, owner, id, balance)) {
                        msg = addAccount(id, type, balance, date, owner);
                        message(panel, msg);
                        AdminOperations.insertEmployeeAction(GUI.employeeName(), "Created account " + id + ",name " + owner + ",type " + type + ",balance " + balance + ",date " + date);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    msg = "";
                    getInputs();
                    if (checkInputs(type, date, owner, id, balance)) {
                        msg = updateAccount(id, type, balance, date, owner);
                        message(panel, msg);
                        AdminOperations.insertEmployeeAction(GUI.employeeName(), "Updated account " + id + ",name " + owner + ",type " + type + ",balance " + balance);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    msg = "";
                    getInputs();
                    if (checkInputs(type, date, owner, id, balance)) {
                        msg = deleteAccount(id, owner);
                        message(panel, msg);
                        AdminOperations.insertEmployeeAction(GUI.employeeName(), "Deleted account " + id + ",name " + owner + ",type " + type);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    msg = "";
                    owner = t5.getText().trim();
                    if (!owner.equals("")) {
                        msg = viewAccount(owner);
                        message(panel, msg);
                        AdminOperations.insertEmployeeAction(GUI.employeeName(), "Viewed account " + id + ",name " + owner + ",type " + type + ",balance " + balance);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private void initializeEmployeeAccount() {
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
        p5.add(l5);
        p5.add(t5);
        p6.add(create);
        p6.add(update);
        p6.add(delete);
        p6.add(view);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p5);
        panel.add(p6);

        actionListeners();

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 300);
        frame.setTitle("Account operations");
    }

    public EmployeeAccount(String emplName) {
        initializeEmployeeAccount();
        emplName = GUI.employeeName();
        System.out.println(emplName);
    }

    public static void main(String[] args) {
        //EmployeeAccount empA = new EmployeeAccount();

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Date date2 = new Date(2020 - 1900, 0, 25);
        // print out today's date
        if (date.compareTo(date2) > 0)
            System.out.println(true);
        System.out.println(date);
        System.out.println(date2);
    }
}
