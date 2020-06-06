package client;


public class ClientMessage {

    public static void viewToClient(String msg){
        Client.sendMsgToServer(msg);
    }
}
