package sk.uniza.fri.pokemon;

public class FirePokemon extends Pokemon {
    public FirePokemon(String name, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, health, attack, defense, speed, evolvesInto);
    }

    public FirePokemon(String name, int health, int attack, int defense, int speed) {
        super(name, health, attack, defense, speed);
    }
}