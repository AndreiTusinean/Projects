package client.presentation.view;

import client.presentation.controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame{
    public static JButton btn_login = new JButton("Login");
    private static JTextField textField_name = new JTextField();
    private static JPasswordField textField_pass = new JPasswordField();
    private JLabel lable_name = new JLabel("Name");
    private JLabel lable_pass = new JLabel("Password");
    private JLabel title = new JLabel("Login page");
    public static JFrame frame = new JFrame();

    public static String name,pass;

    public static void getInputs() {
        name = textField_name.getText().trim();
        pass = textField_pass.getText().trim();
    }

    public void setName(String n){
        name=n;
    }

    public String getName(){
        return name;
    }

    public void setPass(String p){
        pass=p;
    }

    public String getPass(){
        return pass;
    }

    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public void initialize(){

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

        LoginController.LoginUIActionListeners();

        frame.add(panel);
        frame.setTitle("Warehouse login");
        frame.setVisible(true);
        frame.setSize(400, 500);
    }
    int LoginIndex;
    public LoginUI(int LoginIndex){
        this.LoginIndex=LoginIndex;
        initialize();
    }

    public static void main(String[] args){
        LoginUI l = new LoginUI(0);


    }
}
