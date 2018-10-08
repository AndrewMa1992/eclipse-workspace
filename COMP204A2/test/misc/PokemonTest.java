package misc;

import org.junit.Assert;
import org.junit.Test;

public class PokemonTest {

	@Test
    public void testPokemonNumbersCorrect() throws Exception {
        Assert.assertEquals("Mankey should be Pokemon number 56", 56, Pokemon.Mankey.number);
        Assert.assertEquals("Alakazam should be Pokemon number 65", 65, Pokemon.Alakazam.number);
    }
}
