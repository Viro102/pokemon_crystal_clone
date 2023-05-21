package sk.uniza.fri.pokemon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import sk.uniza.fri.ability.Ability;
import sk.uniza.fri.ability.AbilityLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Pokedex implements Iterable<Pokemon> {
    private final HashMap<String, Pokemon> pokemons;
    private final AbilityLoader abilityLoader;
    private final String[] pokemonNames;

    public Pokedex(AbilityLoader abilityLoader) {
        this.pokemons = new HashMap<>();
        this.abilityLoader = abilityLoader;

        JsonReader json = new JsonReader();
        JsonValue entries = json.parse(Gdx.files.internal("pokemons.json"));
        for (int i = 0; i < entries.size; i++) {
            JsonValue entry = entries.get(i);
            String name = entry.getString("name");
            String type = entry.getString("type");
            int tier = entry.getInt("tier");
            int health = entry.getInt("baseHP");
            int attack = entry.getInt("baseAttack");
            int defense = entry.getInt("baseDefense");
            int speed = entry.getInt("baseSpeed");
            String evolvesInto = entry.getString("evolvesInto");

            switch (type) {
                case "fire": {
                    FirePokemon pokemon = new FirePokemon(name, tier, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    this.addAbilities(pokemon);
                    break;
                }
                case "water": {
                    WaterPokemon pokemon = new WaterPokemon(name, tier, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    this.addAbilities(pokemon);
                    break;
                }
                case "grass": {
                    GrassPokemon pokemon = new GrassPokemon(name, tier, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    this.addAbilities(pokemon);
                    break;
                }
                case "electric": {
                    ElectricPokemon pokemon = new ElectricPokemon(name, tier, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    this.addAbilities(pokemon);
                    break;
                }
                case "rock": {
                    RockPokemon pokemon = new RockPokemon(name, tier, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    this.addAbilities(pokemon);
                    break;
                }
                case "poison": {
                    PoisonPokemon pokemon = new PoisonPokemon(name, tier, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    this.addAbilities(pokemon);
                    break;
                }
                case "ice": {
                    IcePokemon pokemon = new IcePokemon(name, tier, health, attack, defense, speed, evolvesInto);
                    this.pokemons.put(pokemon.getName(), pokemon);
                    this.addAbilities(pokemon);
                    break;
                }
                default: {
                    System.out.println("Unknown type: " + type);
                    break;
                }
            }
        }

        System.out.println("Loaded " + this.pokemons.size() + " pokemons");
        this.pokemonNames = this.pokemons.keySet().toArray(new String[0]);
    }

    private List<Ability> getRandomAbilities(String type) {
        ArrayList<Ability> abilities = new ArrayList<>();
        for (Ability ability : this.abilityLoader.getAbilities(type)) {
            if (ability.getType().equals(type)) {
                abilities.add(ability);
            }
        }
        return abilities;
    }

    private void addAbilities(Pokemon pokemon) {
        ArrayList<Ability> abilities = (ArrayList<Ability>) this.getRandomAbilities(pokemon.getType());
        for (int i = 0; i < 4; i++) {
            Ability ability = abilities.get(MathUtils.random(abilities.size() - 1));
            pokemon.addAbility(ability);
        }
    }

    public Pokemon getRandomPokemon() {
        int random = MathUtils.random(this.pokemonNames.length - 1);
        return this.pokemons.get(this.pokemonNames[random]).copy();
    }

    public int getSize() {
        return this.pokemons.size();
    }

    public Pokemon getPokemon(String name) {
        return this.pokemons.get(name).copy();
    }

    @Override
    public Iterator<Pokemon> iterator() {
        return this.pokemons.values().iterator();
    }
}
