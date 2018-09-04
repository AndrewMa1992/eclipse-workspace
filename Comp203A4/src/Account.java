
public class Account {

	private int key;
	private float balance;

	public Account(int key, float balance) {
		super();
		this.key = key;
		this.balance = balance;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		
		this.balance = balance;
	}
	
	@Override
	public int hashCode() {
		return this.key;
	}
}
