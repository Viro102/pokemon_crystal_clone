package sk.uniza.fri.pokemon;

public class ElectricPokemon extends Pokemon {
    public ElectricPokemon(String name, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, health, attack, defense, speed, evolvesInto);
    }

    public ElectricPokemon(String name, int health, int attack, int defense, int speed) {
        super(name, health, attack, defense, speed);
    }
}
