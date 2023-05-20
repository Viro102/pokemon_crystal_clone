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
    private final ArrayList<Move> moves;
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

    protected Pokemon(String name, int level, int health, int attack, int defense, int speed, String evolvesInto) {
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.evolvesInto = evolvesInto;
        this.moves = new ArrayList<>();
        this.experience = 0;
        this.fainted = false;
        this.isCollected = false;

        this.setName(name);
        this.initSprite();
    }

    protected Pokemon(String name, int health, int attack, int defense, int speed, String evolvesInto) {
        this.defense = defense;
        this.speed = speed;
        this.attack = attack;
        this.health = health;
        this.evolvesInto = evolvesInto;
        this.moves = new ArrayList<>();
        this.level = 1;
        this.experience = 0;
        this.fainted = false;
        this.isCollected = false;

        this.setName(name);
        this.initSprite();
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

    public void attack(Pokemon pokemon, AttackMove skill) {
        pokemon.takeDamage(skill.getDamage());
    }

    public void defend(Pokemon pokemon, Move move) {
    }

    public Iterator<Move> getMoves() {
        return this.moves.iterator();
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

    public void addSkill(Move move) {
        if (!this.moves.contains(move)) {
            this.moves.add(move);
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

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
        for (Move move : this.moves) {
            sb.append(move)
                    .append(System.lineSeparator());
        }

        return sb.toString();
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
}
