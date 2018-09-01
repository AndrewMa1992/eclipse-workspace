package BankQueueSys;

public class ClientQueue implements CQInterface{
	
	class Node{
		
	Client client;
	
	Node next;
	}

	private Node head = null;
	
	private Node tail = null;

	public void enqueue(Client c) {
		
		Node newNode = new Node();
		newNode.client = c;
		if(head == null) {
			head = tail =  newNode;
		}else {
			tail.next = newNode;
			tail = tail.next;
		}
	
		
	}


	public Client dequeue() {
		Node temp = null;
		if(head == null) {
			return null;
		}
		
		if(head == tail) {
			temp = head;
			head = tail = null;
			return temp.client;
		}
		
		temp = head;
		head = head.next;
		return temp.client;
	}


	public Client peek() {
		if(head == null) {
			return null;
		}
		return head.client;
	}


	public boolean isEmpty() {
		return head == null;
	}


	public int length() {
		int len = 0;
		if(head == null) {
			return len;
		}
		Node current = head;
		
		if(current == tail) {
			len = 1;
			return len;
		}
		while(current.next != tail) {
			current = current.next;
			len++;
		}
		len++;
		return len;
	}
	
	public void dump() {
		System.out.print("head");
		if(head == null) System.out.println("->null");
		Node current = head;
		
		if(current == tail) {
			System.out.print("->" + current.client.getServiceTime());
			System.out.print("->Null");
		}
		while(current != tail) {
			System.out.print("->"+ current.client.getServiceTime());
			current = current.next;
			
		}
		System.out.print("->"+ current.client.getServiceTime());
			System.out.println("->null");
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	
}
