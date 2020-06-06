package presentation;

import domain.AdminOperations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import static domain.AdminOperations.*;

public class AdminReports {
    private JFrame frame;
    private JPanel panel;
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JLabel l1 = new JLabel("Employee");
    private JLabel l2 = new JLabel("Start date");
    private JLabel l3 = new JLabel("End date");
    private JTextField t1 = new JTextField("");
    private JTextField t2 = new JTextField("dd-mm-yyyy");
    private JTextField t3 = new JTextField("dd-mm-yyyy");
    private JButton report = new JButton("Generate report");

    //Generate reports for a particular period containing the activities performed by an employee.

    String d1, d2;
    String employee;
    String msg = "";
    AdminOperations ad = new AdminOperations();

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

    private boolean checkInputs(String employee, String d1, String d2) {
        return verifDate(d1)&&verifDate(d2)&&!employee.equals("");
    }

    private void getInputs() {
        employee = t1.getText().trim();
        d1 = t2.getText().trim();
        d2 = t3.getText().trim();
        if (!verifDate(d1)&&!verifDate(d2))
            message(panel, "Failed input parsing");
    }

    private void message(JPanel panel, String msg) {
        if (!msg.equals(""))
            JOptionPane.showMessageDialog(panel, msg);
        else
            JOptionPane.showMessageDialog(panel, "Task failed");
    }

    public static Date StringToDate(String str){
        int[] d = new int[3];
        try {
            str.matches("\\d{2}-\\d{2}-\\d{4}");
            String[] s = str.split("-");
            d[0] = Integer.parseInt(s[0]);
            d[1] = Integer.parseInt(s[1]);
            d[2] = Integer.parseInt(s[2]);
        } catch (NumberFormatException e) {
           return null;
        }
        return new Date(d[2] - 1900, d[1]-1, d[0]);
    }

    private void actionListeners() {
        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    msg = "";
                    getInputs();
                    if (checkInputs(employee, d1, d2)) {
                        msg = makeReport(employee, StringToDate(d1), StringToDate(d2));
                        message(panel, msg);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initializeAdminUI() {
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
        p4.add(report);

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
        frame.setTitle("Admin reports");
    }

    public AdminReports() {
        initializeAdminUI();
    }

    public static void main(String[] args) {
        AdminReports admin = new AdminReports();
        System.out.println(StringToDate("23-12-2020"));
        Date d = new Date(2000,02,01);
        System.out.println(d.toString());
    }
}
