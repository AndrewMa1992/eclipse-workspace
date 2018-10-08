package scrollback;

import org.junit.Assert;
import org.junit.Test;

public class ScrollbackTest {

	@Test
	public void testConstructorWithoutArgument() throws Exception {
		Scrollback sb = new Scrollback();
		Assert.assertEquals("testConstructorWithoutArgument", sb.getCapacity(), 10);
	}

	@Test
	public void testConstructorWithArgument() throws Exception {
		Scrollback sb = new Scrollback(15);
		Assert.assertEquals("testConstructorWithArgument", sb.getCapacity(), 15);
	}

	@Test
	public void testAddSameItem() throws Exception {
		Scrollback sb = new Scrollback();
		sb.add("test");
		sb.add("test");
		sb.add("test");
		Assert.assertEquals("testAddSameItem", sb.getCount(), 1);
	}

	@Test
	public void testAdd() throws Exception {
		Scrollback sb = new Scrollback();
		sb.add("test");
		Assert.assertEquals("testAdd", sb.getCount(), 1);
	}

	@Test
	public void testGetLastWithoutItem() throws Exception {
		Scrollback sb = new Scrollback();
		Assert.assertEquals("testGetLastWithoutItem", sb.getLast(), "");
	}

	@Test
	public void testGetLastWithItem() throws Exception {
		Scrollback sb = new Scrollback();
		sb.add("test");
		Assert.assertEquals("testGetLastWithItem", sb.getLast(), "test");
	}

	@Test
	public void testClear() throws Exception {
		Scrollback sb = new Scrollback();
		sb.add("test");
		Assert.assertEquals("testClear", sb.getCount(), 1);
		sb.clear();
		Assert.assertEquals("testClear", sb.getCount(), 0);
	}

	@Test
	public void testSuccessiveCallGetLast() throws Exception {
		Scrollback sb = new Scrollback();
		sb.add("test1");
		sb.add("test2");
		sb.add("test3");
		sb.add("test4");
		sb.getLast();
		sb.getLast();
		Assert.assertEquals("testSuccessiveCallGetLast", sb.getLast(), "test2");
	}

	@Test
	public void testCircularCallGetLast() throws Exception {
		Scrollback sb = new Scrollback();
		sb.add("test1");
		sb.add("test2");
		sb.add("test3");
		sb.add("test4");
		sb.getLast();
		sb.getLast();
		sb.getLast();
		sb.getLast();
		Assert.assertEquals("testCircularCallGetLast", sb.getLast(), "test4");
	}

	@Test
	public void testRemoveOlderItem() throws Exception {
		Scrollback sb = new Scrollback(4);
		sb.add("test1");
		sb.add("test2");
		sb.add("test3");
		sb.add("test4");
		sb.add("test5");
		sb.add("test6");
		sb.getLast();
		sb.getLast();
		sb.getLast();
		sb.getLast();

		Assert.assertEquals("testRemoveOlderItem", sb.getLast(), "test6");
	}
}
