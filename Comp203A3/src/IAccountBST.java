
public interface IAccountBST {

	Account find(int key);

	void insert(int key, float amount);

	void remove(int key);

	void traverse();

}
