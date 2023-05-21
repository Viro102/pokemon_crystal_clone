package sk.uniza.fri.pokemon;

public class RockPokemon extends Pokemon {
    public RockPokemon(String name, int tier, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, tier, health, attack, defense, speed, evolvesInto);
    }

    public RockPokemon(RockPokemon original) {
        super(original);
    }

    @Override
    public String getType() {
        return "rock";
    }

    @Override
    public double getEffectiveness(Pokemon otherPokemon) {
        if (otherPokemon instanceof FirePokemon
                || otherPokemon instanceof IcePokemon) {
            return 2;
        } else if (otherPokemon instanceof RockPokemon) {
            return 0.5;
        } else {
            return 1;
        }
    }

    @Override
    public Pokemon copy() {
        return new RockPokemon(this);
    }
}
