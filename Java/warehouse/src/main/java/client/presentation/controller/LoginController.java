package client.presentation.controller;

import client.ClientMessage;
import client.presentation.view.LoginUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    public static void LoginUIActionListeners(){
        LoginUI.btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginUI.getInputs();
                ClientMessage.viewToClient("loginCheck,"+LoginUI.name+","+LoginUI.pass);
            }
        });
    }
    public static void main(String[] args){ }
}
