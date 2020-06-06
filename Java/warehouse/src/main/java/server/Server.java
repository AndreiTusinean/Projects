package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Server {


    public static List<Observer> l = new ArrayList<Observer>();

    public static void addObserver(Observer o) {
        l.add(o);
    }

    public static void notifyAll(int id, String msg) {
        for (Observer o : l) {
            o.update(id, msg);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (ServerSocket socket = new ServerSocket(3000)) {
            while (true) {
                System.out.println(Instant.now() + " Waiting for client...");
                Socket clientSocket = socket.accept();
                ConnectionServer c = new ConnectionServer(clientSocket);
                addObserver(c);
                System.out.println(l.toString());
                c.start();
            }
        }
    }
}

