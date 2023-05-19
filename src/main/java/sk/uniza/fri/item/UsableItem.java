package sk.uniza.fri.item;

import sk.uniza.fri.character.Player;
import sk.uniza.fri.pokemon.Pokemon;

public interface UsableItem extends Item {
    void use(Player player, Pokemon pokemon);
}
