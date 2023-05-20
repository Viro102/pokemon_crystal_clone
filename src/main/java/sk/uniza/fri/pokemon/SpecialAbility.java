package sk.uniza.fri.pokemon;

import sk.uniza.fri.item.Effect;

public class SpecialAbility extends Ability {
    protected SpecialAbility(String name, String description, Effect statusEffect, int levelRequirement, Effect effect) {
        super(name, description, levelRequirement, effect);
    }

    @Override
    public void activate(Pokemon target) {
        target.setStatusEffect(this.getEffect());
    }
}
