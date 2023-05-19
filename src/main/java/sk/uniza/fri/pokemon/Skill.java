package sk.uniza.fri.pokemon;

public abstract class Skill {
    private final String name;
    private final String description;
    private final int levelRequirement;

    protected Skill(String name, String description, int levelRequirement) {
        this.name = name;
        this.description = description;
        this.levelRequirement = levelRequirement;
    }
    
    public abstract void activate();

    @Override
    public String toString() {
        return "SKILL: " + this.name + " " + this.description + " LVL: " + this.levelRequirement;
    }
}
