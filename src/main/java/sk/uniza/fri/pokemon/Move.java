package sk.uniza.fri.pokemon;

public abstract class Move {
    private final String name;
    private final String description;
    private final int levelRequirement;

    protected Move(String name, String description, int levelRequirement) {
        this.name = name;
        this.description = description;
        this.levelRequirement = levelRequirement;
    }

    public abstract void activate();

    @Override
    public String toString() {
        return "Skill: " + this.name + " " + this.description + " LVL: " + this.levelRequirement;
    }
}
