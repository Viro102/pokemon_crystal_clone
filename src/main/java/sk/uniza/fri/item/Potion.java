package sk.uniza.fri.item;

import sk.uniza.fri.pokemon.Pokemon;

public class Potion implements UsableItem {
    private final String name;
    private final String description;
    private final Effect effect;
    private final int amount;
    private final int duration;

    public Potion(String name, String description, int duration, int amount, Effect effect) {
        this.name = name;
        this.duration = duration;
        this.amount = amount;
        this.description = description;
        this.effect = effect;
    }

    public Potion(String name, String description, int amount, Effect effect) {
        this(name, description, 0, amount, effect);
    }


    public int getAmount() {
        return this.amount;
    }

    public int getDuration() {
        return this.duration;
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
                pokemon.increaseHP(this.effect.getAmount(this));
                break;
            }
            case BUFF_DEFENSE: {
                pokemon.increaseDEF(this.effect.getAmount(this));
                break;
            }
            case BUFF_ATTACK: {
                pokemon.increaseATT(this.effect.getAmount(this));
                break;
            }
            default: {
                break;
            }
        }
    }
}
