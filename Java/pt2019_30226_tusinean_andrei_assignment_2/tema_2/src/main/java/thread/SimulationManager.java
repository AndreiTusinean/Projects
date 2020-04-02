package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import schedule.ConcreteStrategyQueue;
import schedule.Scheduler;
import schedule.Strategy.SelectionPolicy;
import view.SimulationFrame;

public class SimulationManager implements Runnable {

	public int timeLimit = 100;
	public static int maxTime;// = 10;
	public static int minTime;// = 2;
	public int numberOfQueues;// =3;
	public int numberOfClients = 10;
	public SelectionPolicy selectionPolicy = SelectionPolicy.shortest_queue;

	private Scheduler scheduler;
	private List<Client> generatedClients = new ArrayList<Client>();
	int[] avgTime;
	int[] avgTimeClients;
	int[] peakTime;

	ConcreteStrategyQueue cnc = new ConcreteStrategyQueue();

	public SimulationManager() {
		maxTime = SimulationFrame.getMax();
		minTime = SimulationFrame.getMin();
		numberOfQueues = SimulationFrame.getQ();
		numberOfClients = SimulationFrame.getC();
		avgTime = new int[numberOfQueues];
		avgTimeClients = new int[numberOfQueues];
		peakTime = new int[numberOfQueues];
		scheduler = new Scheduler(numberOfQueues, numberOfClients);
		scheduler.changeStrategy(selectionPolicy);
		Thread[] t = new Thread[numberOfQueues];
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread();
			t[i].start();
		}
		genNrandomClients();
	}

	private void genNrandomClients() {
		Random rand = new Random();
		for (int i = 0; i < numberOfClients; i++) {
			int arrTime = rand.nextInt((getMaxTime() - getMinTime()) + 1) + getMinTime();
			int prcTime = rand.nextInt((getMaxTime() - getMinTime()) + 1) + getMinTime();
			Client c = new Client(arrTime, prcTime, i + 1);
			generatedClients.add(c);
		}
		Collections.sort(generatedClients, new Comparator<Client>() {
			public int compare(Client c1, Client c2) {
				return c1.getArrTime() - c2.getArrTime();
			}
		});
	}

	public void run() {
		int currentTime = 0, ind = 10;
		while (currentTime < timeLimit) {
			for (int i = 0; i < generatedClients.size(); i++) {
				if (generatedClients.get(i).getArrTime() >= 0) {
					ind = scheduler.dipatchClient(generatedClients.get(i));
					avgTime[ind] += generatedClients.get(i).prcTime;
					avgTimeClients[ind]++;
					if (generatedClients.get(i).prcTime > peakTime[ind])
						peakTime[ind] = generatedClients.get(i).prcTime;
					System.out.println("Coada " + ind + " : " + generatedClients.get(i).toString());
					for (int j = 0; j < getNumberOfQueues(); j++) {
						 //System.out.println("Coada " + j + " : " +
						 //scheduler.queues.get(j).clients.toString());
					}
				}
				generatedClients.remove(i);
			}
			currentTime++;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}

		for (int i = 0; i < numberOfQueues; i++) {
			if (avgTimeClients[i] != 0)
				System.out.println("Avg times pentru coada " + i + " total time " + avgTime[i] + ", avg time "
						+ avgTime[i] / avgTimeClients[i] + " nr clients " + avgTimeClients[i] + ", peak time "+peakTime[i]);
		}
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public static int getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(int maxTime) {
		SimulationManager.maxTime = maxTime;
	}

	public static int getMinTime() {
		return minTime;
	}

	public static void setMinTime(int minTime) {
		SimulationManager.minTime = minTime;
	}

	public int getNumberOfQueues() {
		return numberOfQueues;
	}

	public void setNumberOfQueues(int numberOfQueues) {
		this.numberOfQueues = numberOfQueues;
	}

	public int getNumberOfClients() {
		return numberOfClients;
	}

	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}

	public static void main(String[] args) {
		SimulationFrame frame = new SimulationFrame();
	}
}
