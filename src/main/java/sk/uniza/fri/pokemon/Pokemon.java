package sk.uniza.fri.pokemon;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Pokemon extends Actor {
    private final String name;
    private final ArrayList<Skill> skills;
    private int defense;
    private int speed;
    private TextureAtlas textureAtlas;
    private int attack;
    private int health;
    private boolean fainted;
    private int level;
    private int experience;

    protected Pokemon(String name, int level, int health, int attack, int defense, int speed, ArrayList<Skill> skills) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.skills = skills;
        this.experience = 0;
        this.fainted = false;

        this.initSprite();
    }

    protected Pokemon(String name, int health, int attack, int defense, int speed) {
        this.name = name;
        this.defense = defense;
        this.speed = speed;
        this.attack = attack;
        this.health = health;
        this.skills = new ArrayList<>();
        this.level = 0;

        this.initSprite();
    }

    public void initSprite() {
        this.textureAtlas = new TextureAtlas("Atlas/Pokemons.atlas");
        if (this.textureAtlas.findRegion(this.name) == null) {
            System.out.println("Texture for " + this.name + " not found!");
        } else {
            TextureRegion textureRegion = this.textureAtlas.findRegion(this.name);
            this.setWidth(textureRegion.getRegionWidth());
            this.setHeight(textureRegion.getRegionHeight());
        }
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
        this.health += 1 / 50f * this.health;
        this.attack += 1 / 50f * this.attack;
        this.defense += 1 / 50f * this.defense;
        this.speed += 1 / 50f * this.speed;
    }

    public int getLevel() {
        return this.level;
    }

    public void evolve() {
        System.out.println("Pokemon " + this.name + " evolved!");
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
    public String getName() {
        return this.name;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.textureAtlas.findRegion(this.getName()), this.getX(), this.getY());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name)
                .append(" HP: ")
                .append(this.health)
                .append(" ATT: ")
                .append(this.attack)
                .append(System.lineSeparator());
        for (Skill skill : this.skills) {
            sb.append(skill)
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
