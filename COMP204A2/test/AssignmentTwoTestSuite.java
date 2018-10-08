import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import misc.PokemonEncounterTest;
import misc.PokemonTest;
import scrollback.GenericScrollbackTest;
import scrollback.ScrollbackTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	PokemonEncounterTest.class,
	PokemonTest.class,
	GenericScrollbackTest.class,
	ScrollbackTest.class
})
public class AssignmentTwoTestSuite {}
