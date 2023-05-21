package sk.uniza.fri.pokemon;

public class GrassPokemon extends Pokemon {

    public GrassPokemon(String name, int tier, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, tier, health, attack, defense, speed, evolvesInto);
    }

    public GrassPokemon(GrassPokemon original) {
        super(original);
    }

    @Override
    public String getType() {
        return "grass";
    }

    @Override
    public double getEffectiveness(Pokemon otherPokemon) {
        if (otherPokemon instanceof RockPokemon) {
            return 2;
        } else if (otherPokemon instanceof FirePokemon
                || otherPokemon instanceof PoisonPokemon
                || otherPokemon instanceof GrassPokemon) {
            return 0.5;
        } else {
            return 1;
        }
    }

    @Override
    public GrassPokemon copy() {
        return new GrassPokemon(this);
    }
}
