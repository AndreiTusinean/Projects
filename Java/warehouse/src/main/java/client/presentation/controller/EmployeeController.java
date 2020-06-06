package client.presentation.controller;

import client.ClientMessage;
import client.presentation.model.Item;
import client.presentation.view.EmployeeUI;
import client.presentation.view.ManagerUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController {

    public static void EmployeeUIActionListeners(){
        EmployeeUI.create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeUI.getInputs();
                Item i = new Item(EmployeeUI.name,EmployeeUI.amount,EmployeeUI.price,EmployeeUI.location);
                ClientMessage.viewToClient("addItem,"+i.getName()+","+i.getAmount()+","+i.getPrice()+","+i.getLocation());
            }
        });

        EmployeeUI.update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeUI.getInputs();
                Item i = new Item(EmployeeUI.name,EmployeeUI.amount,EmployeeUI.price,EmployeeUI.location);
                i.setId(EmployeeUI.id);
                ClientMessage.viewToClient("updateItem,"+i.getId()+","+i.getName()+","+i.getAmount()+","+i.getPrice()+","+i.getLocation());
            }
        });
        EmployeeUI.delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeUI.getInputId();
                Item i = new Item();
                i.setId(EmployeeUI.id);
                ClientMessage.viewToClient("removeItem,"+i.getId());
            }
        });
        EmployeeUI.view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeUI.getInputId();
                Item i = new Item();
                i.setId(EmployeeUI.id);
                ClientMessage.viewToClient("viewItem,"+i.getId());
            }
        });

        EmployeeUI.bill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeUI.getInputs();
                EmployeeUI.getInputDiscount();
                String emplName = EmployeeUI.getEmployeeName();
                ClientMessage.viewToClient("addBill,"+EmployeeUI.name+","+EmployeeUI.amount+","+EmployeeUI.discount+","+emplName);

                ClientMessage.viewToClient("getRanking,"+ ManagerUI.orderType);
            }
        });
    }
}
