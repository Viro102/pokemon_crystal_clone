package sk.uniza.fri.pokemon;

public class WaterPokemon extends Pokemon {
    public WaterPokemon(String name, int tier, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, tier, health, attack, defense, speed, evolvesInto);
    }

    public WaterPokemon(WaterPokemon original) {
        super(original);
    }

    @Override
    public String getType() {
        return "water";
    }

    @Override
    public double getEffectiveness(Pokemon otherPokemon) {
        if (otherPokemon instanceof FirePokemon
                || otherPokemon instanceof RockPokemon) {
            return 2;
        } else if (otherPokemon instanceof GrassPokemon
                || otherPokemon instanceof WaterPokemon) {
            return 0.5;
        } else {
            return 1;
        }
    }

    @Override
    public Pokemon copy() {
        return new WaterPokemon(this);
    }
}
