package server;

import client.presentation.model.Bill;
import client.presentation.model.Item;
import client.presentation.model.ItemRequest;
import client.presentation.model.User;
import server.business.AdminDAO;
import server.business.EmployeeDAO;
import server.business.LoginDAO;
import server.business.ManagerDAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Instant;
import java.util.Random;

class ConnectionServer extends Thread implements Observer {
    private final Socket clientSocket;

    public ConnectionServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    ObjectOutputStream output;
    ObjectInputStream input;

    @Override
    public void update(int id, String msg) {
        try {
            output.writeObject("notify," + id + "," + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
            while (clientSocket.isConnected()) {


                boolean clientHasData = clientSocket.getInputStream().available() > 0;
                if (clientHasData) {
                    String msg = (String) input.readObject();
                    System.out.println(Instant.now() + " Got from client: " + msg);

                    String[] str = msg.split(",");
                    Item i = new Item();
                    ItemRequest ir = new ItemRequest();
                    User u = new User();
                    Bill b = new Bill();
                    switch (str[0]) {
                        case "loginCheck":
                            output.writeObject(LoginDAO.checkLoginDAO(str[1], str[2]));
                            break;
                        case "addItem":
                            i.setName(str[1]);
                            i.setAmount(Integer.parseInt(str[2].trim()));
                            i.setPrice(Integer.parseInt(str[3].trim()));
                            i.setLocation(Integer.parseInt(str[4].trim()));
                            output.writeObject(EmployeeDAO.insertItemDAO(i));
                            break;
                        case "removeItem":
                            i.setId(Integer.parseInt(str[1].trim()));
                            output.writeObject(EmployeeDAO.deleteItemDAO(i));
                            break;

                        case "updateItem":
                            i.setId(Integer.parseInt(str[1].trim()));
                            i.setName(str[2]);
                            i.setAmount(Integer.parseInt(str[3].trim()));
                            i.setPrice(Integer.parseInt(str[4].trim()));
                            i.setLocation(Integer.parseInt(str[5].trim()));
                            output.writeObject(EmployeeDAO.updateItemDAO(i));
                            if (Integer.parseInt(str[3].trim()) == 0) {
                                Server.notifyAll(Integer.parseInt(str[5].trim()), "Item " + i.getName() + " from warehouse " + i.getLocation() + " is out of stock");
                            }
                            break;

                        case "viewItem":
                            i.setId(Integer.parseInt(str[1].trim()));
                            output.writeObject(EmployeeDAO.viewItemDAO(i));
                            break;

                        case "addUser":
                            u.setName(str[1]);
                            u.setPass(str[2]);
                            u.setType(str[3]);
                            if (str[3].equals("manager")) {
                                Random rand = new Random();
                                int loc = rand.nextInt(2) + 1;
                                u.setLocation(loc);
                            }
                            output.writeObject(AdminDAO.insertUserDAO(u));
                            break;

                        case "removeUser":
                            u.setId(Integer.parseInt(str[1].trim()));
                            output.writeObject(AdminDAO.deleteUserDAO(u));
                            break;

                        case "updateUser":
                            u.setId(Integer.parseInt(str[1].trim()));
                            u.setName(str[2]);
                            u.setPass(str[3]);
                            u.setType(str[4]);
                            output.writeObject(AdminDAO.updateUserDAO(u));
                            break;

                        case "viewUser":
                            u.setId(Integer.parseInt(str[1].trim()));
                            output.writeObject(AdminDAO.viewUserDAO(u));
                            break;

                        case "report":
                            int loc = Integer.parseInt(str[1].trim());
                            output.writeObject(AdminDAO.makeReportDAO(loc, str[2]));
                            break;

                        case "updateItemRequest":
                            ir.setId(Integer.parseInt(str[1].trim()));
                            ir.setName(str[2]);
                            ir.setAmount(Integer.parseInt(str[3].trim()));
                            ir.setPrice(Integer.parseInt(str[4].trim()));
                            ir.setLocation(Integer.parseInt(str[5].trim()));
                            output.writeObject(ManagerDAO.updateItemRequestDAO(ir));
                            break;

                        case "viewRequests":
                            output.writeObject(AdminDAO.viewRequestsDAO());
                            break;

                        case "adminGrantRequest":
                            ir.setId(Integer.parseInt(str[1].trim()));
                            output.writeObject(AdminDAO.grantRequestDAO(ir));
                            break;

                        case "addBill":
                            double d;
                            b.setName(str[1]);
                            b.setAmount(Integer.parseInt(str[2].trim()));
                            if (Integer.parseInt(str[3].trim()) != 0){
                                d = 1 - ((Integer.parseInt(str[3].trim())) / 100.0);
                                output.writeObject(EmployeeDAO.addCustomDiscountBillDAO(b, d, str[4]));
                            }
                            else{
                                d = 0.0;
                                output.writeObject(EmployeeDAO.addStandardDiscountBillDAO(b, d, str[4]));
                            }
                            //output.writeObject(EmployeeDAO.addBillDAO(b, d, str[4]));
                            break;

                        case "getRanking":
                            output.writeObject(ManagerDAO.getRankingsDAO(str[1]));
                            break;

                        default:
                            break;
                    }

                    output.writeObject("");
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Instant.now() + " Client disconnected?");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // cleanup
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}