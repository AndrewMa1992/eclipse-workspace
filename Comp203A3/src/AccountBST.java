
public class AccountBST {

	private Account root;
	private AccountBST left;
	private AccountBST right;

	public AccountBST(Account root, AccountBST left, AccountBST right) {
		super();
		this.root = root;
		this.left = left;
		this.right = right;
	}

	public Account find(int key) {

		if (this.root != null) {
			System.out.print(this.root.getKey() + "->");
		} else {
			System.out.print(key + "->");
		}

		if (this.root == null || this.root.getKey() == key) {
			return root;
		}

		if (key > this.root.getKey() && this.right != null) {
			return this.right.find(key);
		}
		if (key < this.root.getKey() && this.left != null) {
			return this.left.find(key);
		}

		System.out.print(key + " ");
		return null;

	}

	public void insert(int key, float amount) {

		Account account = new Account(key, amount);

		if (this.root == null || key == this.root.getKey()) {

			root = account;
		}
		if (key < this.root.getKey()) {
			if (this.left == null) {
				this.left = new AccountBST(account, null, null);
			} else {
				this.left.insert(key, amount);
			}
		}

		if (key > this.root.getKey()) {
			if (this.right == null) {
				this.right = new AccountBST(account, null, null);
			} else {
				this.right.insert(key, amount);
			}
		}

	}
	
	

	public void remove(int key) {
		if (this.root == null) {
			return;
		} else {
			if (this.root.getKey() == key) {
				Account account = new Account(0, 0);
				AccountBST accountbst = new AccountBST(account, null, null);
				accountbst.setLeft(this);
				this.remove(key, accountbst);
				this.root = accountbst.getLeft().root;
				accountbst.setLeft(null);
			} else {
				this.remove(key, null);
			}
		}

	}

	private void remove(int key, AccountBST parent) {
		if (key < this.root.getKey()) {
			if (this.left != null) {
				this.left.remove(key, this);
			} else {
				return;
			}
		} else if (key > this.root.getKey()) {
			if (this.right != null) {
				this.right.remove(key, this);
			} else {
				return;
			}
		} else {
			if (this.left != null && this.right != null) {
				this.root = this.right.minValue();
				right.remove(this.root.getKey(), this);
			} else if (parent.left == this) {
				parent.left = (this.left != null) ? this.left : this.right;

			} else if (parent.right == this) {
				parent.right = (this.left != null) ? this.left : this.right;
			}
		}

	}
	
	public void traverse() {
		
		if(this.root == null) {
			return;
		}
		if (left != null)
		this.left.traverse();
		
		System.out.println(root.getKey() + ":" + root.getBalance());
		if (right != null)
		this.right.traverse();
		
	}

	private Account minValue() {
		if (this.left == null)
			return this.root;
		else
			return this.left.minValue();
	}

	public Account getRoot() {
		return root;
	}

	public void setRoot(Account root) {
		this.root = root;
	}

	public AccountBST getLeft() {
		return left;
	}

	public void setLeft(AccountBST left) {
		this.left = left;
	}

	public AccountBST getRight() {
		return right;
	}

	public void setRight(AccountBST right) {
		this.right = right;
	}

}
