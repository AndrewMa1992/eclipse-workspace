package scrollback;

public interface GenericScrollbackInterface<T> {
	
	 /**
     * Add an item to the head of the Scrollback list. If the item to insert is the same as the item that is currently the head, then do the new item is not inserted.
     *
     * @param item Item to insert into the Scrollback
     */
    public void add(T item);

    /**
     * Retrieve the previous item in the Scrollback. Successive calls to getLast() gets the next item until an add operation resets it.
     *
     * @return The item retrieved from the current getLast call
     */
    public T getLast();

    /**
     * Remove all items from the Scrollback, restoring it to an as-new state.
     */
    public void clear();

    /**
     * Get the maximum number of entries this Scrollback is capable of holding before entries start to be overwritten
     *
     * @return The maximum number of entries this Scrollback is capable of holding
     */
    public int getCapacity();

    /**
     * Get the number of entries currently in the Scrollback. This should never exceed the value given by getCapacity()
     *
     * @return Number of entries in this Scrollback
     */
    public int getCount();

}
