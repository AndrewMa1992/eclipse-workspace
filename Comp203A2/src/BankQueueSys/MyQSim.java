
package BankQueueSys;

/**
 * @author Andrew
 *
 */
public class MyQSim {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ClientQueue cq = new ClientQueue();
		for(int i = 1; i <= 2; i++ ) {
			Client c = new Client(0, i);
			cq.enqueue(c);
		}
		
		cq.dump();
		Client c1 = new Client(0, 100);
		cq.enqueue(c1);
		cq.dump();
		Client c2 = new Client(0, 98);
		cq.enqueue(c2);
		cq.dump();
		cq.dequeue();
		cq.dump();
		cq.dequeue();
		cq.dump();
		System.out.println(cq.peek().getServiceTime());

	}

}
