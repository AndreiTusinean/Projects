package schedule;

import java.util.ArrayList;
import java.util.List;

import thread.Client;
import thread.Queue;

public interface Strategy {
	public List<Queue> queues = new ArrayList<Queue>();
	Client c = new Client();
	
	public int addClients(List<Queue> queues,Client c);
	
	public enum SelectionPolicy{
		shortest_queue,shortest_time;
	}
	
}
