package schedule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import schedule.Strategy.SelectionPolicy;
import thread.Client;
import thread.Queue;

public class Scheduler {
	public static List<Queue> queues ;
	private int maxNoQueues;
	private int maxClientsPerQueue;
	public static Strategy strategy;
	
	public Scheduler(int maxNoQueues, int maxClientsPerQueue) {
		queues = new ArrayList<Queue>(maxNoQueues);
		for (int i = 0; i < maxNoQueues; i++) {
			Queue q = new Queue(i);
			this.queues.add(q);
			queues.get(i).start();
		}
	}
	
	public Scheduler() {}
	
	public void changeStrategy(SelectionPolicy policy) {
		if(policy == SelectionPolicy.shortest_queue) {
			strategy = new ConcreteStrategyQueue();			
		}
		if(policy == SelectionPolicy.shortest_time) {
			strategy = new ConcreteStrategyTime();			
		}
	}
	
	public int dipatchClient(Client c) {	
		return strategy.addClients(this.getQueues(),c);
	}
	
	public List<Queue> getQueues(){
		return queues;
	}
	
}
