package sk.uniza.fri.item;

import com.badlogic.gdx.math.MathUtils;
import sk.uniza.fri.pokemon.Pokemon;

public class Pokeball implements Item {
    @Override
    public String getName() {
        return "Pokeball";
    }

    @Override
    public String getDescription() {
        return "A device for catching wild Pokemon.";
    }

    public boolean attemptToCatch(Pokemon pokemon) {
        int catchChance = (int) (MathUtils.random() * 100);
        if (catchChance < 50) {
            pokemon.setCollected(true);
            System.out.println("You caught " + pokemon.getName() + "!");
            return true;
        } else {
            System.out.println("You failed to catch " + pokemon.getName() + "!");
            return false;
        }
    }
}
