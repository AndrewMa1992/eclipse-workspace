package scrollback;

import java.util.LinkedList;

/**
 * A String Scrollback implementation
 */
public class Scrollback implements ScrollbackInterface {
	/* variables */
	private int capacity;
	public LinkedList<String> items = new LinkedList<String>();
	private int cursor = 0;

	/*
	 * constructor
	 */
	public Scrollback() {
		this(10);
	}

	public Scrollback(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public void add(String item) {
		if (items.isEmpty() || !item.equals(items.getFirst())) {
			if (items.size() == capacity) {
				items.removeLast();
			}
			items.addFirst(item);
		}
		cursor = 0;
	}

	/* add method */
	@Override
	public String getLast() {
		if (items.size() == 0) {
			return "";
		}
		String item = items.get(cursor);
		if (cursor < items.size() - 1) {
			cursor++;
		} else {
			cursor = 0;
		}
		return item;
	}

	/* clear method */
	@Override
	public void clear() {

		items.clear();
	}

	/* getter */
	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public int getCount() {
		return items.size();
	}
}
