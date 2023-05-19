package sk.uniza.fri.item;

import sk.uniza.fri.character.Player;
import sk.uniza.fri.pokemon.Pokemon;

public class Potion implements UsableItem {
    private final String name;
    private final String description;
    private final Effect effect;

    public Potion(String name, String description, Effect effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void use(Player player, Pokemon pokemon) {
        if (this.effect == Effect.BUFF_ATTACK) {
            pokemon.setAttack(this.effect.getAmount());
        }
    }
}
