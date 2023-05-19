package sk.uniza.fri.pokemon;

import java.util.HashMap;
import java.util.Iterator;

public class Pokedex implements Iterable<Pokemon> {
    private final HashMap<String, Pokemon> pokemons;

    public Pokedex() {
        this.pokemons = new HashMap<>();
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.put(pokemon.getName(), pokemon);
    }

    public Pokemon getPokemon(String name) {
        return this.pokemons.get(name);
    }

    public boolean hasPokemon(Pokemon pokemon) {
        return this.pokemons.containsValue(pokemon);
    }

    @Override
    public Iterator<Pokemon> iterator() {
        return this.pokemons.values().iterator();
    }
}
