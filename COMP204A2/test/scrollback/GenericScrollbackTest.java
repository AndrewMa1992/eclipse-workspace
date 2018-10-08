package scrollback;

import org.junit.Assert;
import org.junit.Test;

import misc.Pokemon;
import misc.PokemonEncounter;

public class GenericScrollbackTest {
	/* constructor test method*/
    @Test
    public void testConstructorWithoutArgument() throws Exception {
        GenericScrollback<String> sb = new GenericScrollback<String>();
        Assert.assertEquals("testConstructorWithoutArgument", sb.getCapacity(), 10);
    }
    /* constructor test method*/
    @Test
    public void testConstructorWithArgument() throws Exception {
        GenericScrollback<String> sb = new GenericScrollback<String>(15);
        Assert.assertEquals("testConstructorWithArgument", sb.getCapacity(), 15);
    }
    /*add method test*/
    @Test
    public void testAddSameItem() throws Exception {
        GenericScrollback<String> sb = new GenericScrollback<String>();
        sb.add("test");
        sb.add("test");
        sb.add("test");
        Assert.assertEquals("testAddSameItem", sb.getCount(), 1);
    }
   /*add method test*/
    @Test
    public void testAdd() throws Exception {
        GenericScrollback<String> sb = new GenericScrollback<String>();
        sb.add("test");
        Assert.assertEquals("testAdd", sb.getCount(), 1);
    }
    /*get last method test*/
    @Test
    public void testGetLastWithoutItem() throws Exception {
        GenericScrollback<String> sb = new GenericScrollback<String>();
        Assert.assertEquals("testGetLastWithoutItem", sb.getLast(), null);
    }
    /*get last method test*/
    @Test
    public void testGetLastWithItem() throws Exception {
        GenericScrollback<String> sb = new GenericScrollback<String>();
        sb.add("test");
        Assert.assertEquals("testGetLastWithItem", sb.getLast(), "test");
    }
    /*clear method test*/
    @Test
    public void testClear() throws Exception {
        GenericScrollback<String> sb = new GenericScrollback<String>();
        sb.add("test");
        Assert.assertEquals("testClear", sb.getCount(), 1);
        sb.clear();
        Assert.assertEquals("testClear", sb.getCount(), 0);
    }
/* call method test*/
    @Test
    public void testSuccessiveCallGetLast() throws Exception {
        GenericScrollback<String> sb = new GenericScrollback<String>();
        sb.add("test1");
        sb.add("test2");
        sb.add("test3");
        sb.add("test4");
        sb.getLast();
        sb.getLast();
        Assert.assertEquals("testSuccessiveCallGetLast", sb.getLast(), "test2");
    }
/* call get last method test*/
    @Test
    public void testCircularCallGetLast() throws Exception {
        GenericScrollback<String> sb = new GenericScrollback<String>();
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

    // remove method
    @Test
    public void testRemoveOlderItem() throws Exception {
        GenericScrollback<String> sb = new GenericScrollback<String>(4);
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
    @Test
    public void testAddPokemonEncounter() throws Exception {
        GenericScrollback<PokemonEncounter> sb = new GenericScrollback<PokemonEncounter>();
        sb.add(new PokemonEncounter(0, 0, Pokemon.Aerodactyl));
        Assert.assertEquals("testAddPokemonEncounter", sb.getCount(), 1);
    }

    @Test
    public void testAddSamePokemonEncounter() throws Exception {
        GenericScrollback<PokemonEncounter> sb = new GenericScrollback<PokemonEncounter>();
        sb.add(new PokemonEncounter(0, 0, Pokemon.Aerodactyl));
        sb.add(new PokemonEncounter(0, 0, Pokemon.Aerodactyl));
        Assert.assertEquals("testAddSamePokemonEncounter", sb.getCount(), 1);
    }
}
