package BankQueueSys;

public interface CQInterface {
	
	void enqueue(Client c);
	
	Client dequeue();
	
	Client peek();
	
	boolean isEmpty();
	
	int length();

}
