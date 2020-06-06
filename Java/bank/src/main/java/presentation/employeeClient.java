package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import domain.AdminOperations;
import domain.EmployeeClientOperations;

import static domain.EmployeeClientOperations.addClient;

public class employeeClient extends JFrame {
    private static String emplName;
    private JFrame frame;
    private JPanel panel;
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JPanel p5 = new JPanel();
    private JLabel l1 = new JLabel("Name");
    private JLabel l2 = new JLabel("Id nr");
    private JLabel l3 = new JLabel("PNC");
    private JLabel l4 = new JLabel("Address");
    private JTextField t1 = new JTextField("");
    private JTextField t2 = new JTextField("");
    private JTextField t3 = new JTextField("");
    private JTextField t4 = new JTextField("");
    private JButton add = new JButton("Add");
    private JButton update = new JButton("Update");
    private JButton view = new JButton("View");

    //Add/update/view client information (name, identity card number, personal numerical code, address, etc.).
    String name, address;
    int idNr;
    int pnc;
    String msg = "";
    EmployeeClientOperations em = new EmployeeClientOperations();

    private void getInputs() {
        name = t1.getText().trim();
        address = t4.getText().trim();
        try {
            idNr = Integer.parseInt(t2.getText().trim());
            pnc = Integer.parseInt(t3.getText().trim());
        } catch (NumberFormatException e) {
        }
    }

    private void actionListeners() {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getInputs();
                    msg = addClient(name, idNr, pnc, address);
                    AdminOperations.insertEmployeeAction(GUI.employeeName(), "Added client " + name+",id "+idNr+",pnc "+pnc+",address "+address);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getInputs();
                    msg = em.updateClient(name, idNr, pnc, address);
                    AdminOperations.insertEmployeeAction(GUI.employeeName(), "Updated client " + name+",id "+idNr+",pnc "+pnc+",address "+address);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getInputs();
                    System.out.println(name);
                    msg = em.viewClient(name);
                    JOptionPane.showMessageDialog(panel, msg);
                    System.out.println("employee is: " + GUI.employeeName());
                    AdminOperations.insertEmployeeAction(GUI.employeeName(), "Viewed client " + name);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initializeEmployeeClient() {
        frame = new JFrame();
        panel = new JPanel();

        t1.setColumns(15);
        t2.setColumns(15);
        t3.setColumns(15);
        t4.setColumns(15);

        p1.add(l1);
        p1.add(t1);
        p2.add(l2);
        p2.add(t2);
        p3.add(l3);
        p3.add(t3);
        p4.add(l4);
        p4.add(t4);
        p5.add(add);
        p5.add(update);
        p5.add(view);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p5);

        actionListeners();

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 300);
        frame.setTitle("Client operations");
    }

    public employeeClient(String emplName) {
        initializeEmployeeClient();
        emplName = GUI.employeeName();
        System.out.println(emplName);
    }

    public static void main(String[] args) {
        //employeeClient empC = new employeeClient(emplName);
    }
}
