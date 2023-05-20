package sk.uniza.fri.pokemon;

public class RockPokemon extends Pokemon {
    public RockPokemon(String name, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, health, attack, defense, speed, evolvesInto);
    }

    public RockPokemon(String name, int health, int attack, int defense, int speed) {
        super(name, health, attack, defense, speed);
    }
}
