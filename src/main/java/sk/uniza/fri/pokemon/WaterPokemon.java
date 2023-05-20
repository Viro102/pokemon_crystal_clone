package sk.uniza.fri.pokemon;

public class WaterPokemon extends Pokemon {

    public WaterPokemon(String name, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, health, attack, defense, speed, evolvesInto);
    }

    public WaterPokemon(String name, int health, int attack, int defense, int speed) {
        super(name, health, attack, defense, speed);
    }
}
