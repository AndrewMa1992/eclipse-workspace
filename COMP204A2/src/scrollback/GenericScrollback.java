package scrollback;

import java.util.LinkedList;

public class GenericScrollback<T> implements GenericScrollbackInterface<T> {

	private int capacity;
	public LinkedList<T> items = new LinkedList<T>();
	private int cursor = 0;

	public GenericScrollback() {
		this(10);
	}

	public GenericScrollback(int capacity) {
		this.capacity = capacity;
	}

	/* add method */
	@Override
	public void add(T item) {
		if (items.isEmpty() || !item.equals(items.getFirst())) {
			if (items.size() == capacity) {
				items.removeLast();
			}
			items.addFirst(item);
		}
		cursor = 0;
	}

	@Override
	public T getLast() {
		if (items.size() == 0) {
			return null;
		}
		T item = items.get(cursor);
		if (cursor < items.size() - 1) {
			cursor++;
		} else {
			cursor = 0;
		}
		return item;
	}

	@Override
	public void clear() {
		items.clear();
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public int getCount() {
		return items.size();
	}
}
