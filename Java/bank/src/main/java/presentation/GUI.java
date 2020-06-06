package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import domain.LoginCheck;

public class GUI {
    private static String emplName;
    private JButton btn_login = new JButton("Login");
    private static JTextField textField_name = new JTextField();
    private JPasswordField textField_pass = new JPasswordField();
    private JLabel lable_name = new JLabel("Name");
    private JLabel lable_pass = new JLabel("Password");
    private JLabel title = new JLabel("Login page");
    String msg = null;
    String name, pass;
    employeeClient empC;
    EmployeeAccount empA;
    EmployeeBalanceActions empB;
    EmployeeBills empBl;
    AdminUI admin;
    AdminReports adminRep;


    private void redirect(JPanel panel,String msg){
        if(msg.equals("Username or password incorect"))
            JOptionPane.showMessageDialog(panel, msg);
        else
            if(msg.trim().equals("employee")){
                System.out.println("User is an employee");
                empC = new employeeClient(employeeName());
                empA = new EmployeeAccount(employeeName());
                empB = new EmployeeBalanceActions(employeeName());
                empBl = new EmployeeBills(employeeName());
            }
            if(msg.trim().equals("admin")){
                System.out.println("User is an admin");
                admin = new AdminUI();
                adminRep = new AdminReports();
            }
    }

    public static String employeeName(){
        return  textField_name.getText().trim();
    }

    private void initialize() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(300, 50));
        textField_name.setColumns(10);
        textField_pass.setColumns(10);
        textField_pass.setEchoChar('*');
        btn_login.setPreferredSize(new Dimension(150, 30));
        btn_login.setHorizontalAlignment(SwingConstants.CENTER);
        lable_name.setPreferredSize(new Dimension(100, 50));
        lable_pass.setPreferredSize(new Dimension(100, 50));
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginCheck l = new LoginCheck();
                try {
                    name = textField_name.getText().trim();
                    pass = textField_pass.getText().trim();
                    msg = LoginCheck.checkLogin(name, pass);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                redirect(panel,msg);
            }
        });
        p1.add(title);
        p2.add(lable_name);
        p2.add(textField_name);
        p3.add(lable_pass);
        p3.add(textField_pass);
        p4.add(btn_login);
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bank login");
        frame.setVisible(true);
        frame.setSize(400, 500);
    }

    public GUI() {
        initialize();
    }

}
