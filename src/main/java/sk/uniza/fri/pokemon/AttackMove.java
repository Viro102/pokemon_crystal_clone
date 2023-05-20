package sk.uniza.fri.pokemon;

public class AttackMove extends Move {
    private final int damage;

    protected AttackMove(String name, String description, int levelRequirement) {
        super(name, description, levelRequirement);
        this.damage = 5;
    }

    public int getDamage() {
        return this.damage;
    }

    @Override
    public void activate() {
    }

    @Override
    public String toString() {
        return super.toString() + " DMG: " + this.damage;
    }
}
