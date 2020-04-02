package schedule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import thread.Client;
import thread.Queue;

public class ConcreteStrategyQueue implements Strategy {
	public List<Queue> queues = new ArrayList<Queue>();
	Client c = new Client();

	public int addClients(List<Queue> queues, Client c) {
		List<Integer> arl = new ArrayList<Integer>();
		int min = 1000000, ind = 0;
		for (Queue q : queues) {
			arl.add(q.clients.size());
		}
		for (int i = 0; i < arl.size(); i++) {
			if (arl.get(i) < min) {
				min = arl.get(i);
				ind = i;
			}
		}
		queues.get(ind).addClient(c);
		return ind;
	}

}
