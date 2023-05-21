package sk.uniza.fri.pokemon;

public class ElectricPokemon extends Pokemon {

    public ElectricPokemon(String name, int tier, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, tier, health, attack, defense, speed, evolvesInto);
    }
    
    public ElectricPokemon(ElectricPokemon original) {
        super(original);
    }

    @Override
    public String getType() {
        return "electric";
    }

    @Override
    public double getEffectiveness(Pokemon otherPokemon) {
        if (otherPokemon instanceof WaterPokemon) {
            return 2;
        } else if (otherPokemon instanceof GrassPokemon
                || otherPokemon instanceof ElectricPokemon) {
            return 0.5;
        } else {
            return 1;
        }
    }

    @Override
    public Pokemon copy() {
        return new ElectricPokemon(this);
    }
}
