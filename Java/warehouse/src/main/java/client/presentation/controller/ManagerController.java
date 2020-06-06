package client.presentation.controller;

import client.ClientMessage;
import client.presentation.model.Item;
import client.presentation.model.ItemRequest;
import client.presentation.view.ManagerUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ManagerController {
    public static void ManagerUIActionListeners(){
        ManagerUI.create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerUI.getInputs();
                Item i = new Item(ManagerUI.name,ManagerUI.amount,ManagerUI.price,ManagerUI.location);
                ClientMessage.viewToClient("addItem,"+i.getName()+","+i.getAmount()+","+i.getPrice()+","+i.getLocation());
            }
        });

        ManagerUI.update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerUI.getInputs();
                ItemRequest i = new ItemRequest(ManagerUI.name,ManagerUI.amount,ManagerUI.price,ManagerUI.location);
                i.setId(ManagerUI.id);
                ClientMessage.viewToClient("updateItemRequest,"+i.getId()+","+i.getName()+","+i.getAmount()+","+i.getPrice()+","+i.getLocation());
            }
        });
        ManagerUI.delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerUI.getInputId();
                Item i = new Item();
                i.setId(ManagerUI.id);
                ClientMessage.viewToClient("removeItem,"+i.getId());
            }
        });
        ManagerUI.view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerUI.getInputId();
                Item i = new Item();
                i.setId(ManagerUI.id);
                ClientMessage.viewToClient("viewItem,"+i.getId());
            }
        });

        ManagerUI.jc1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ManagerUI.orderType = e.getStateChange() == ItemEvent.SELECTED ? "amount" : "orders";
                if(ManagerUI.orderType.equals("amount"))
                    ManagerUI.jc2.setSelected(false);
                else
                    ManagerUI.jc2.setSelected(true);
                ClientMessage.viewToClient("getRanking,"+ ManagerUI.orderType);
            }

        });

        ManagerUI.jc2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ManagerUI.orderType = e.getStateChange() == ItemEvent.SELECTED ? "orders" : "amount";
                if(ManagerUI.orderType.equals("orders"))
                    ManagerUI.jc1.setSelected(false);
                else
                    ManagerUI.jc1.setSelected(true);
                ClientMessage.viewToClient("getRanking,"+ ManagerUI.orderType);
            }
        });

    }
    public static void main(String[] args){ }
}
