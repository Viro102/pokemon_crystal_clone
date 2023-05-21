package sk.uniza.fri.ability;

import sk.uniza.fri.item.Effect;

public class Ability {
    private final String name;
    private final String description;
    private final Effect effect;
    private final int power;
    private final int duration;
    private final int levelRequirement;
    private final int cooldown;
    private final String type;
    private boolean isUnlocked;

    public Ability(String name, String description, String type, int cooldown, int duration, int power, int levelRequirement, Effect effect) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.cooldown = cooldown;
        this.duration = duration;
        this.power = power;
        this.levelRequirement = levelRequirement;
        this.effect = effect;
        this.isUnlocked = false;
    }

    public Ability(String name, String description, String type, int power, int levelRequirement, Effect effect) {
        this(name, description, type, 0, 0, power, levelRequirement, effect);
    }

    public Ability(Ability original) {
        this(original.name, original.description, original.type, original.cooldown, original.duration, original.power, original.levelRequirement, original.effect);
    }

    public boolean isUnlocked() {
        return this.isUnlocked;
    }

    public void unlock() {
        this.isUnlocked = true;
    }

    public String getName() {
        return this.name;
    }

    public Effect getEffect() {
        return this.effect;
    }

    public int getAmount() {
        return this.effect.getAmount(this);
    }

    public int getLvlReq() {
        return this.levelRequirement;
    }

    public Ability copy() {
        return new Ability(this);
    }

    public int getPower() {
        return this.power;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Skill: " + this.name + " " + this.description + " LVLRQ: " + this.levelRequirement;
    }
}
