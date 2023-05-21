package sk.uniza.fri.pokemon;

public class PoisonPokemon extends Pokemon {
    public PoisonPokemon(String name, int tier, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, tier, health, attack, defense, speed, evolvesInto);
    }

    public PoisonPokemon(PoisonPokemon original) {
        super(original);
    }

    @Override
    public String getType() {
        return "poison";
    }

    @Override
    public double getEffectiveness(Pokemon otherPokemon) {
        if (otherPokemon instanceof GrassPokemon) {
            return 2;
        } else if (otherPokemon instanceof PoisonPokemon
                || otherPokemon instanceof RockPokemon) {
            return 0.5;
        } else {
            return 1;
        }
    }

    @Override
    public Pokemon copy() {
        return new PoisonPokemon(this);
    }
}
