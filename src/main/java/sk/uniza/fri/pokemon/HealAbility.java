package sk.uniza.fri.pokemon;

import sk.uniza.fri.item.Effect;

public class HealAbility extends Ability {
    protected HealAbility(String name, String description, int levelRequirement, Effect effect) {
        super(name, description, levelRequirement, effect);
    }

    @Override
    public void activate(Pokemon target) {
        target.increaseHP(this.getEffect().getAmount());
    }
}
