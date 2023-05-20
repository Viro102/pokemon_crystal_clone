package sk.uniza.fri.pokemon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;
import java.util.Iterator;

public class Pokedex implements Iterable<Pokemon> {
    private final HashMap<String, Pokemon> pokemons;
    private final String[] pokemonNames;

    public Pokedex() {
        this.pokemons = new HashMap<>();
        JsonReader json = new JsonReader();
        JsonValue entries = json.parse(Gdx.files.internal("pokemons.json"));
        for (int i = 0; i < entries.size; i++) {
            JsonValue entry = entries.get(i);
            String name = entry.getString("name");
            String type = entry.getString("type");
            int health = entry.getInt("baseHP");
            int attack = entry.getInt("baseAttack");
            int defense = entry.getInt("baseDefense");
            int speed = entry.getInt("baseSpeed");
            String evolvesInto = entry.getString("evolvesInto");

            switch (type) {
                case "fire": {
                    FirePokemon pokemon = new FirePokemon(name, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    break;
                }
                case "water": {
                    WaterPokemon pokemon = new WaterPokemon(name, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    break;
                }
                case "grass": {
                    GrassPokemon pokemon = new GrassPokemon(name, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    break;
                }
                case "electric": {
                    ElectricPokemon pokemon = new ElectricPokemon(name, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    break;
                }
                case "rock": {
                    RockPokemon pokemon = new RockPokemon(name, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    break;
                }
                case "poison": {
                    PoisonPokemon pokemon = new PoisonPokemon(name, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    break;
                }
                case "ice": {
                    IcePokemon pokemon = new IcePokemon(name, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    break;
                }
                default: {
                    System.out.println("Unknown type: " + type);
                }
            }
        }
        
        System.out.println("Loaded " + this.pokemons.size() + " pokemons");
        this.pokemonNames = this.pokemons.keySet().toArray(new String[0]);
    }

    public Pokemon getRandomPokemon() {
        int random = MathUtils.random(this.pokemonNames.length - 1);
        return this.pokemons.get(this.pokemonNames[random]);
    }

    public int getSize() {
        return this.pokemons.size();
    }

    public Pokemon getPokemon(String name) {
        return this.pokemons.get(name);
    }

    @Override
    public Iterator<Pokemon> iterator() {
        return this.pokemons.values().iterator();
    }
}
