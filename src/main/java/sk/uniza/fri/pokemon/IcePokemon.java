package sk.uniza.fri.pokemon;

public class IcePokemon extends Pokemon {
    public IcePokemon(String name, int tier, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, tier, health, attack, defense, speed, evolvesInto);
    }

    public IcePokemon(IcePokemon original) {
        super(original);
    }

    @Override
    public String getType() {
        return "ice";
    }

    @Override
    public double getEffectiveness(Pokemon otherPokemon) {
        if (otherPokemon instanceof GrassPokemon) {
            return 2;
        } else if (otherPokemon instanceof FirePokemon
                || otherPokemon instanceof WaterPokemon
                || otherPokemon instanceof IcePokemon) {
            return 0.5;
        } else {
            return 1;
        }
    }

    @Override
    public Pokemon copy() {
        return new IcePokemon(this);
    }
}
