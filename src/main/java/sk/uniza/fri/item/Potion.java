package sk.uniza.fri.item;

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
    public void use(Pokemon pokemon) {
        switch (this.effect) {
            case HEAL: {
                pokemon.increaseHP(this.effect.getAmount());
                break;
            }
            case BUFF_DEFENSE: {
                pokemon.increaseDEF(this.effect.getAmount());
                break;
            }
            case BUFF_ATTACK: {
                pokemon.increaseATT(this.effect.getAmount());
                break;
            }
            default: {
                break;
            }
        }
    }
}
