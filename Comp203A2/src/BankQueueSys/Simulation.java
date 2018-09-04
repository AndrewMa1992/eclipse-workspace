package BankQueueSys;

import java.util.Random;
import java.lang.*;
public class Simulation {
	
	
	public Simulation(int num) {
		Random rd = new Random();
		ClientQueue cq = new ClientQueue();
		int arriveTime = 0;
		int serviceTime = rd.nextInt(2)+1;
		int currentTime = 0;
		int nextDepartureTime = 0;
		int nextArrivalTime = 0;
		int sumWaitTime = 0;
		Client c = new Client(0, serviceTime);
		Client head;
		cq.enqueue(c);
		for(int i = 1; i <= num-1; i++) {
			head = cq.peek();
			nextArrivalTime += rd.nextInt(2)+1;
			serviceTime = rd.nextInt(2)+1;
			Client client = new Client(nextArrivalTime,serviceTime);
			if(head != null) {
				nextDepartureTime = head.getArriveTime() + head.getServiceTime();
				cq.enqueue(client);
				cq.dequeue();
				if(nextDepartureTime < nextArrivalTime) {
					currentTime = nextArrivalTime;
				}else {
					currentTime = nextDepartureTime;
				}
				sumWaitTime += nextDepartureTime - head.getArriveTime();
			}else {
				cq.enqueue(client);
				currentTime = nextArrivalTime;
			}
		}
		while(!cq.isEmpty()) {
			head = cq.peek();
			currentTime += head.getServiceTime();
			sumWaitTime += currentTime - head.getArriveTime();
			cq.dequeue();
		}
		
		System.out.println(currentTime +" "+ (double)sumWaitTime / num);
		
	}

}
