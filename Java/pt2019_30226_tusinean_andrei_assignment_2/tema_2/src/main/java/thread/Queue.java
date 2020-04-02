package thread;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue extends Thread {
	public BlockingQueue<Client> clients;
	private AtomicInteger waitPeriod;
	public int ID;

	public Queue(int ID) {
		this.ID = ID;
		clients = new LinkedBlockingQueue<Client>();
		waitPeriod = new AtomicInteger();
	}

	public void addClient(Client c) {
		clients.add(c);
		int aux = waitPeriod.get() + c.getPrcTime();
		waitPeriod.set(aux);
	}

	public void run() {
		while (true) {
			Client c = new Client();
			int time = 0;
			//System.out.println("Thread "+ID+" running???");
			try {
				 c = clients.take();
				 time = c.getPrcTime();
			} catch (InterruptedException e) {}
		    try {
		        System.out.println("Thread "+ID+" sleeps for "+time+" client "+c.ID);
		        Thread.sleep(time*100);
		       	int aux = waitPeriod.get() - c.getPrcTime();
		    	waitPeriod.set(aux);
		    } catch (InterruptedException e) { }
		    if(waitPeriod.get()!=0)
		    	System.out.println("Timp ramas (coada "+ID+"): "+waitPeriod);
		    else
		    	System.out.println("Coada "+ID+" este goala");
		}
	}

	public Client[] getClients() throws InterruptedException {
		Client[] c = new Client[clients.size()];
		for (int i = 0; i < clients.size(); i++)
			c[i] = clients.take();
		return c;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < clients.size(); i++)
			try {
				s += clients.take().toString() + '\n';
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		return s;
	}

}
