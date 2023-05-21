package sk.uniza.fri.pokemon;

public class FirePokemon extends Pokemon {
    public FirePokemon(String name, int tier, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, tier, health, attack, defense, speed, evolvesInto);
    }

    public FirePokemon(FirePokemon original) {
        super(original);
    }

    @Override
    public String getType() {
        return "fire";
    }

    @Override
    public double getEffectiveness(Pokemon otherPokemon) {
        if (otherPokemon instanceof WaterPokemon || otherPokemon instanceof FirePokemon) {
            return 0.5;
        } else if (otherPokemon instanceof GrassPokemon || otherPokemon instanceof IcePokemon) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public Pokemon copy() {
        return new FirePokemon(this);
    }

}