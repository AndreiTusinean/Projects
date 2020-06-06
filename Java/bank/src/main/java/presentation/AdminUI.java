package presentation;

import domain.AdminOperations;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import static domain.AdminOperations.addEmployee;
import static domain.AdminOperations.updateEmployee;
import static domain.AdminOperations.deleteEmployee;
import static domain.AdminOperations.viewEmployee;


public class AdminUI extends JFrame{
    private JFrame frame;
    private JPanel panel;
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JLabel l1 = new JLabel("Name");
    private JLabel l2 = new JLabel("Password");
    private JLabel l3 = new JLabel("Type");
    private JTextField t1 = new JTextField("");
    private JTextField t2 = new JTextField("");
    private JTextField t3 = new JTextField("");
    private JButton create = new JButton("Create");
    private JButton update = new JButton("Update");
    private JButton delete = new JButton("Delete");
    private JButton view = new JButton("View");

    //CRUD on employees’ information.

    String name, pass, type;
    String msg = "";
    AdminOperations ad = new AdminOperations();

    private boolean checkInputs(String name, String pass, String type) {
        return !type.equals("") && !name.equals("") && !pass.equals("");
    }

    private void getInputs() {
        name = t1.getText().trim();
        pass = t2.getText().trim();
        type = t3.getText().trim();
        if (!checkInputs(name,pass,type))
            message(panel, "Failed input parsing");
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
                    if (checkInputs(name, pass, type)) {
                        msg = addEmployee(name, pass, type);
                        message(panel, msg);
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
                    if (checkInputs(name, pass, type)) {
                        msg = updateEmployee(name, pass, type);
                        message(panel, msg);
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
                    if (checkInputs(name, pass, type)) {
                        msg = deleteEmployee(name);
                        message(panel, msg);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                msg = "";
                name = t1.getText().trim();
                if (!name.equals("")) {
                    try {
                        msg = viewEmployee(name);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                message(panel, msg);
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
        p4.add(create);
        p4.add(update);
        p4.add(delete);
        p4.add(view);

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
        frame.setTitle("Admin operations");
    }

    public AdminUI() {
        initializeAdminUI();
    }
    public static void main(String[] args){
        AdminUI admin = new AdminUI();
    }
}
