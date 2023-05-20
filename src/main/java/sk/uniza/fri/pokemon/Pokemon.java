package sk.uniza.fri.pokemon;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import sk.uniza.fri.game.Constants;
import sk.uniza.fri.item.Effect;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Pokemon extends Actor {
    private final ArrayList<Ability> abilities;
    private final String evolvesInto;
    private int defense;
    private int speed;
    private TextureAtlas textureAtlas;
    private int attack;
    private int health;
    private boolean fainted;
    private int level;
    private int experience;
    private boolean isCollected;
    private Effect statusEffect;

    protected Pokemon(String name, int level, int health, int attack, int defense, int speed, String evolvesInto) {
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.evolvesInto = evolvesInto;
        this.abilities = new ArrayList<>();
        this.experience = 0;
        this.fainted = false;
        this.isCollected = false;

        this.setName(name);
        this.initSprite();
    }

    protected Pokemon(String name, int health, int attack, int defense, int speed, String evolvesInto) {
        this(name, 1, health, attack, defense, speed, evolvesInto);
    }

    protected Pokemon(String name, int health, int attack, int defense, int speed) {
        this(name, health, attack, defense, speed, null);
    }

    public void initSprite() {
        this.textureAtlas = new TextureAtlas("Atlas/Pokemons.atlas");
        if (this.textureAtlas.findRegion(this.getName()) == null) {
            System.out.println("Texture for " + this.getName() + " not found!");
        } else {
            TextureRegion textureRegion = this.textureAtlas.findRegion(this.getName());
            this.setWidth(textureRegion.getRegionWidth());
            this.setHeight(textureRegion.getRegionHeight());
            this.setOrigin(this.getX() + this.getWidth() / 2f, this.getY() + this.getHeight() / 2f);
        }
    }

    public void attack(Pokemon pokemon, OffensiveAbility skill) {
        pokemon.decreaseHP(skill.getDamage());
    }

    public Iterator<Ability> getAbilities() {
        return this.abilities.iterator();
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

    public void addAbility(Ability ability) {
        if (!this.abilities.contains(ability)) {
            this.abilities.add(ability);
        }
    }

    public void increaseHP(int hp) {
        this.health += hp;
    }

    public void increaseATT(int attack) {
        this.attack += attack;
    }

    public void increaseDEF(int defense) {
        this.defense += defense;
    }

    public void increaseSPD(int speed) {
        this.speed += speed;
    }

    public void decreaseHP(int hp) {
        this.health -= hp;
        if (this.health <= 0) {
            this.fainted = true;
        }
    }

    public void decreaseATT(int attack) {
        this.attack -= attack;
    }

    public void decreaseDEF(int defense) {
        this.defense -= defense;
    }

    public void decreaseSPD(int speed) {
        this.speed -= speed;
    }

    public int getAttack() {
        return this.attack;
    }


    public int getHealth() {
        return this.health;
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


    public int getPowerPoints() {
        int powerPoints = 0;
        powerPoints += 1 / 5f * this.health;
        powerPoints += 1 / 5f * this.attack;
        powerPoints += 1 / 5f * this.defense;
        powerPoints += 1 / 5f * this.speed;
        powerPoints += this.level;
        return powerPoints;
    }

    public void setCollected(boolean collected) {
        this.isCollected = collected;
    }

    public int[] getStats() {
        int[] stats = new int[5];
        stats[0] = this.level;
        stats[1] = this.health;
        stats[2] = this.attack;
        stats[3] = this.defense;
        stats[4] = this.speed;
        return stats;
    }

    public void setStats(int[] pokemonStats) {
        if (pokemonStats.length != 5) {
            throw new IllegalArgumentException("Pokemon stats must be 5!");
        }
        this.level = pokemonStats[0];
        this.health = pokemonStats[1];
        this.attack = pokemonStats[2];
        this.defense = pokemonStats[3];
        this.speed = pokemonStats[4];
    }

    public void setStatusEffect(Effect effect) {
        this.statusEffect = effect;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.textureAtlas.findRegion(this.getName()), this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), this.getWidth(), this.getHeight(), this.getScaleX(), this.getScaleY(), this.getRotation());

        if (Constants.DEBUG) {
            BitmapFont font = new BitmapFont();
            String stats = "NAME: " + this.getName()
                    + "\n"
                    + " LVL: " + this.getLevel()
                    + " HP: " + this.getHealth()
                    + " ATK: " + this.getAttack()
                    + " DEF: " + this.getDefense();
            font.draw(batch, stats, this.getX(), this.getY() + this.getHeight());
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
        for (Ability ability : this.abilities) {
            sb.append(ability)
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
