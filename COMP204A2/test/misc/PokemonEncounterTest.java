package misc;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PokemonEncounterTest {

	@Test
    public void testConstructorStores() throws Exception {
        int x = 77;
        int y = 43;
        Pokemon p = Pokemon.Porygon;

        PokemonEncounter pe = new PokemonEncounter(x, y, p);

        Assert.assertEquals("PokemonEncounter x coordinate should be the same as constructor x parameter", x, pe.x);
        Assert.assertEquals("PokemonEncounter y coordinate should be the same as constructor y parameter", y, pe.y);
        Assert.assertEquals("PokemonEncounter pokemon should be the same as constructor pokemon parameter", p, pe.pokemon);
    }
	
	@Test
    public void testToString() throws Exception {
        int x = 12;
        int y = 15;
        Pokemon p = Pokemon.Charizard;

        /* Testing calculated and absolute separately to help identify any other calculation issues */
        final String expectedCalc = p.name() + " found at (" + x + ", " + y + ")";
        final String expectedAbs = "Charizard found at (12, 15)";


        PokemonEncounter pe = new PokemonEncounter(x, y, p);

        Assert.assertEquals("Expected calculated string to be equal to the PokemonEncounter.toString() result", expectedCalc, pe.toString());
        Assert.assertEquals("Expected absolute string to be equal to the PokemonEncounter.toString() result", expectedAbs, pe.toString());
    }
	
	@Test
    public void testHashCodeSameAndDifferentPokemon() throws Exception {
        PokemonEncounter pA = new PokemonEncounter(12, 15, Pokemon.Chansey);
        PokemonEncounter pB = new PokemonEncounter(12, 15, Pokemon.Chansey);
        PokemonEncounter pC = new PokemonEncounter(12, 15, Pokemon.Squirtle);

        assertEquals("Two PokemonEncounters with the same parameters should have the same hashcode", pA.hashCode(), pB.hashCode());
        assertNotEquals("Two PokemonEncounters with different Pokemon should have differing hashcode", pA.hashCode(), pC.hashCode());
    }
	
	@Test
    public void testHashCodeDifferentCoordinates() throws Exception {
        PokemonEncounter pA = new PokemonEncounter(13, 15, Pokemon.Chansey);
        PokemonEncounter pB = new PokemonEncounter(12, 15, Pokemon.Chansey);

        assertNotEquals("Two PokemonEncounters with different coordinates should have differing hashcode", pA, pB);
    }

    @Test
    public void testEqualsSame() throws Exception {
        PokemonEncounter pA = new PokemonEncounter(27, 35, Pokemon.Wartortle);
        PokemonEncounter pB = new PokemonEncounter(27, 35, Pokemon.Wartortle);

        Assert.assertTrue("Two PokemonEncounters with the same parameters should be equal when tested with .equals", pA.equals(pB));

        Assert.assertFalse("Two PokemonEncounters with the same parameters should *not* be equal when tested with ==", pA == pB);
    }

    @Test
    public void testEqualsDifferent() throws Exception {
        PokemonEncounter pA = new PokemonEncounter(27, 57, Pokemon.Marowak);
        PokemonEncounter pB = new PokemonEncounter(27, 35, Pokemon.Marowak);

        Assert.assertFalse("Two PokemonEncounters with different parameters should not be equal when tested with .equals", pA.equals(pB));

        Assert.assertFalse("Two PokemonEncounters with different parameters should *not* be equal when tested with ==", pA == pB);
    }
}
