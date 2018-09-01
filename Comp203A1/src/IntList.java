
public class IntList {
	private Node head;
	static int qsortCount = 0;
	private Node current;// This will have latest node

	private Node left;
	public int count = 0;

	public IntList() {
		this.head = new Node();
		this.current = head;
	}

	public void Add(int data) {
		Node newNode = new Node();
		newNode.setValue(data);
		this.current.setNext(newNode);
		this.current = newNode;
		count++;
	}

	public Node Find(int n) {
		if (n == 0) {
			System.out.println("The elements cannot be zero position.");
			return head;
		} else {

			current = head.getNext();
			for (int i = 1; i < n; i++) {
				current = current.getNext();
			}
			return current;
		}
	}

	public Node Remove(int n) {
		if (n == 0) {
			System.out.println("There is no element in position 0.");
		}
		Node removedNode = null;
		if (this.head != null) {
			this.left = head;
			this.current = head.getNext();
			for (int i = 1; i < n; i++) {
				this.left = this.current;
				this.current = this.left.getNext();
			}
			this.left.setNext(this.current.getNext());

			removedNode = this.current;

		}
		return removedNode;
	}

	public void Insert(Node preNode, int data) {
		if (preNode == null) {
			System.out.println("The given previous node cannot be null");
			return;
		}

		Node newNode = new Node(data);
		newNode.setNext(preNode.getNext());
		preNode.setNext(newNode);
	}

	public void Dump() {
		System.out.print("Head ->");
		Node curr = head;
		while (curr.getNext() != null) {
			curr = curr.getNext();
			System.out.print(curr.getValue());
			System.out.print("->");
		}
		System.out.println("NULL");
	}

	public void InsertionSort() {
		int isortCount = 0;
		System.out.println(count);
		for (int i = 1; i <= count; i++) {
			//System.out.println("-----------------------------");
			//this.Dump();
			int key = this.Remove(i).getValue();
			//this.Dump();
			//System.out.println("*****************************");
			int j = i - 1;
			while (j > 0 && this.Find(j).getValue() > key) {
				j--;
				isortCount++;
				//this.Dump();
			}
			this.Insert(this.Find(j), key);
		}
		System.out.println("The numbers of comparison of insertion sort is " + isortCount);
	}

	public void QuickSort(int left, int right) {

		int i, j, t, pivot;
		if (left > right)
			return;
		pivot = this.Find(left).getValue();
		i = left;
		j = right;
		while (i != j) {
			while (this.Find(j).getValue() >= pivot && i < j) {
				j--;
				qsortCount++;
			}
			while (this.Find(i).getValue() <= pivot && i < j) {
				i++;
				qsortCount++;
			}
			if (i < j) {
				t = this.Find(i).getValue();
				this.Find(i).setValue(this.Find(j).getValue());
				this.Find(j).setValue(t);
				qsortCount++;
			}
		}
		this.Find(left).setValue(this.Find(i).getValue());
		this.Find(i).setValue(pivot);
		QuickSort(left, i - 1);
		QuickSort(i + 1, right);

	}
	
	public void BubbleSort() {
		if(this.count <2) {
			return;
		}
		
		int bubbleSortCount = 0;
		int swapTimer;
		do {
			 swapTimer =0;
			for(int i =1; i < this.count; i++) {
				if(this.Find(i).getValue() > this.Find(i+1).getValue()) {
					int temp = 0;
					temp = this.Find(i+1).getValue();
					this.Find(i+1).setValue(this.Find(i).getValue());
					this.Find(i).setValue(temp);
					bubbleSortCount++;
					swapTimer++;
				}
			}
			
		}while(swapTimer > 1);
		
		System.out.println("The number of comparison of bubble sort is " + bubbleSortCount);
	}
}
