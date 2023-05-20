package sk.uniza.fri.pokemon;

import sk.uniza.fri.item.Effect;

public class OffensiveAbility extends Ability {
    private final int damage;

    protected OffensiveAbility(String name, String description, int damage, int levelRequirement, Effect effect) {
        super(name, description, levelRequirement, effect);
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    @Override
    public void activate(Pokemon target) {
        target.decreaseHP(this.damage);
    }

    @Override
    public String toString() {
        return super.toString() + " DMG: " + this.damage;
    }
}
