
public class MyHashTable {

	private int capacity;
	private int slots;
	private int collisions;
	private int[] keys;
	private Account[] datas;
	private String probing;

	public MyHashTable(int capacity, String probing) {
		super();
		this.capacity = capacity;
		this.slots = 0;
		this.collisions = 0;
		this.keys = new int[capacity];
		this.datas = new Account[capacity];
		this.probing = probing;
	}

	public boolean put(int key, Account d) {
		if ((double) this.slots / this.capacity > 0.8) {
			return false;
		} else {
			int index = this.index(key);
			keys[index] = key;
			if (datas[index] != null) {
				slots++;
			}
			datas[index] = d;
			return true;
		}

	}

	public Account get(int key) {

		return this.datas[this.index(key)];
	}

	public void remove(int key) {

		int index = this.index(key);
		keys[index] = 0;

		if (datas[index] != null) {
			slots--;
		}
		datas[index] = null;
	}

	public int getCollisions() {
		return collisions;
	}

	public void setCollisions(int collisions) {
		this.collisions = collisions;
	}

	private int index(int hash) {

		int index = hash % capacity;

		if (this.probing.equals("L")) {
			while (keys[index] != 0 && keys[index] != hash) {
				index = (index + 1) % capacity;
				this.collisions++;
			}
		} else {
			int offset = hash / capacity;
			while (keys[index] != 0 && keys[index] != hash) {
				index = (index + offset) % capacity;
				this.collisions++;
			}
		}
		return index;
	}
}
