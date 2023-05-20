package sk.uniza.fri.pokemon;

import sk.uniza.fri.item.Effect;

public abstract class Ability {
    private final String name;
    private final String description;
    private final int levelRequirement;
    private final Effect effect;

    protected Ability(String name, String description, int levelRequirement, Effect effect) {
        this.name = name;
        this.description = description;
        this.levelRequirement = levelRequirement;
        this.effect = effect;
    }

    public abstract void activate(Pokemon target);

    public Effect getEffect() {
        return this.effect;
    }

    @Override
    public String toString() {
        return "Skill: " + this.name + " " + this.description + " LVL: " + this.levelRequirement;
    }
}
