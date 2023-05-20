package sk.uniza.fri.pokemon;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import sk.uniza.fri.game.Constants;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Pokemon extends Actor {
    private final ArrayList<Skill> skills;
    private int defense;
    private int speed;
    private TextureAtlas textureAtlas;
    private int attack;
    private int health;
    private boolean fainted;
    private int level;
    private int experience;

    protected Pokemon(String name, int level, int health, int attack, int defense, int speed) {
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.skills = new ArrayList<>();
        this.experience = 0;
        this.fainted = false;

        this.initSprite();
        this.setName(name);
    }

    protected Pokemon(String name, int level, int health, int attack, int defense, int speed, ArrayList<Skill> skills) {
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.skills = skills;
        this.experience = 0;
        this.fainted = false;

        this.initSprite();
        this.setName(name);
    }

    protected Pokemon(String name, int health, int attack, int defense, int speed) {
        this.defense = defense;
        this.speed = speed;
        this.attack = attack;
        this.health = health;
        this.skills = new ArrayList<>();
        this.level = 0;
        this.experience = 0;
        this.fainted = false;

        this.initSprite();
        this.setName(name);
    }


    public void initSprite() {
        this.textureAtlas = new TextureAtlas("Atlas/Pokemons.atlas");
        if (this.textureAtlas.findRegion(this.getName()) == null) {
            System.out.println("Texture for " + this.getName() + " not found!");
        } else {
            TextureRegion textureRegion = this.textureAtlas.findRegion(this.getName());
            this.setWidth(textureRegion.getRegionWidth());
            this.setHeight(textureRegion.getRegionHeight());
        }
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
        if (this.experience >= 100) {
            this.levelUp();
        }
    }

    private void levelUp() {
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
        System.out.println("Pokemon " + this.getName() + " evolved!");
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

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean hasFainted() {
        return this.fainted;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.textureAtlas.findRegion(this.getName()), this.getX(), this.getY());

        if (Constants.DEBUG) {
            BitmapFont font = new BitmapFont();
            String stats = "NAME: " + this.getName()
                    + " LVL: " + this.getLevel()
                    + " HP: " + this.getHealth()
                    + " ATK: " + this.getAttack()
                    + " DEF: " + this.getDefense();
            font.draw(batch, stats, this.getX() + this.getWidth() / 2, this.getY() + this.getHeight());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName())
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

    public int getPowerPoints() {
        int powerPoints = 0;
        powerPoints += 1 / 5f * this.health;
        powerPoints += 1 / 5f * this.attack;
        powerPoints += 1 / 5f * this.defense;
        powerPoints += 1 / 5f * this.speed;
        powerPoints += this.level;
        return powerPoints;
    }
}
