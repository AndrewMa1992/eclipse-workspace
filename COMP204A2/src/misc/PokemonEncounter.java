package misc;

/**
 * An immutable container class packaging the x & y coordinates of an encounter with a given Pokemon
 */
public final class PokemonEncounter {

	/**
     * X coordinate of the encountered Pokemon
     */
    public final int x;
    
    /**
     * Y coordinate of the encountered Pokemon
     */
    public final int y;
    
    /**
     * Name of the encountered Pokemon
     */
    public Pokemon pokemon;
    
    /**
     * Construct a new immutable Pokemon Encounter instance
     *
     * @param x The X coordinate of the pokemon encounter
     * @param y The Y coordinate of the pokemon encounter
     * @param pokemon The name of the Pokemon encountered
     */
    public PokemonEncounter(int x, int y, Pokemon pokemon) {
        this.x = x;
        this.y = y;
        this.pokemon = pokemon;
    }
    
    /**
     * Compare this PokemonEncounter instance with another object. If the object is a PokemonEncounter instance and all fields equal those of this instance, they are equal.
     *
     * @param object Object to compare to this instance of PokemonEncounter
     * @return True if the object is equal to this
     */
    @Override
    public boolean equals(Object object) {
        if(object instanceof PokemonEncounter) {
            if (((PokemonEncounter)object).x != this.x) {
                return false;
            }

            if (((PokemonEncounter)object).y != this.y) {
                return false;
            }

            if (((PokemonEncounter)object).pokemon != this.pokemon) {
                return false;
            }

            return true;
        }
        return false;
    }
    
    /**
     * Calculate a hash code for this PokemonEncounter instance. Close enough to correct.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return ((x & 0xfff) << 20) | ((y & 0xfff) << 8) | (pokemon.number & 0xff);
    }
    
    /**
     * Generate a String representation of this PokemonEncounter instance.
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return pokemon.name() + " found at (" + x + ", " + y + ")";
    }
}
