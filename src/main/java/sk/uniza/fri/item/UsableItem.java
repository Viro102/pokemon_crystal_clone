package sk.uniza.fri.item;

import sk.uniza.fri.pokemon.Pokemon;

public interface UsableItem extends Item {
    void use(Pokemon pokemon);
}
