package sk.uniza.fri.pokemon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokedexTest {
    @Test
    void testConstructor() {
        Pokedex pokedex = new Pokedex();
        Assertions.assertEquals(24, pokedex.getSize());
    }
}
