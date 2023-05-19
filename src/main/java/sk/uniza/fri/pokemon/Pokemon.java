package sk.uniza.fri.pokemon;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Pokemon {
    private final String name;
    private final int defense;
    private final int speed;
    private final ArrayList<Skill> skills;
    private int attack;
    private int health;
    private boolean fainted;
    private int level;
    private int experience;

    protected Pokemon(String name, int level, int experience, int health, int attack, int defense, int speed, ArrayList<Skill> skills) {
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.skills = skills;
        this.fainted = false;
    }

    protected Pokemon(String name, int health, int attack, int defense, int speed) {
        this.name = name;
        this.defense = defense;
        this.speed = speed;
        this.attack = attack;
        this.health = health;
        this.skills = new ArrayList<>();
        this.level = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void attack(Pokemon pokemon, AttackSkill skill) {
        pokemon.takeDamage(skill.getDamage());
    }

    public void defend(Pokemon pokemon, Skill skill) {

    }

    public Iterator<Skill> getSkills() {
        return this.skills.iterator();
    }

    public void gainExp(int exp) {
        this.experience += exp;
    }

    public void levelUp() {
        this.level++;
        this.experience = 0;
    }

    public boolean hasFainted() {
        return this.fainted;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.fainted = true;
        }
    }

    public void addSkill(Skill skill) {
        if (!this.skills.contains(skill)) {
            this.skills.add(skill);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name)
                .append(": ")
                .append("HP: ")
                .append(this.health)
                .append(" ATT: ")
                .append(this.attack);


        for (Skill skill : this.skills) {
            sb.append(skill);
        }

        return sb.toString();
    }
}
