package BankQueueSys;

public class Client {
	
	private int arriveTime;
	
	private int serviceTime;
	
	public Client(int arriveTime, int serviceTime) {
		this.arriveTime = arriveTime;
		this.serviceTime = serviceTime;
	}
	
	public int getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(int arriveTime) {
		this.arriveTime = arriveTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	
	

}
