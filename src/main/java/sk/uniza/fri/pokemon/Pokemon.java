package sk.uniza.fri.pokemon;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import sk.uniza.fri.ability.Ability;
import sk.uniza.fri.game.Constants;
import sk.uniza.fri.item.Effect;

import java.util.ArrayList;

public abstract class Pokemon extends Actor {

    private static final int LVL_EVOLUTION_TO_TIER_2 = 16;
    private static final int LVL_EVOLUTION_TO_TIER_3 = 36;
    private final String evolvesInto;
    private final ArrayList<Effect> statusEffects;
    private final ArrayList<Ability> abilities;
    private final String type;
    private int tier;
    private int maxHealth;
    private int defense;
    private int speed;
    private TextureAtlas textureAtlas;
    private int attack;
    private int health;
    private boolean fainted;
    private int level;
    private int experience;
    private boolean isCollected;

    protected Pokemon(String name, int tier, int level, int health, int attack, int defense, int speed, String evolvesInto) {
        this.tier = tier;
        this.level = level;
        this.health = health;
        this.maxHealth = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.evolvesInto = evolvesInto;
        this.abilities = new ArrayList<>();
        this.statusEffects = new ArrayList<>();
        this.experience = 0;
        this.fainted = false;
        this.isCollected = false;
        this.type = "normal";

        this.setName(name);
        this.initSprite();
    }

    protected Pokemon(String name, int tier, int health, int attack, int defense, int speed, String evolvesInto) {
        this(name, tier, 1, health, attack, defense, speed, evolvesInto);
    }

    protected Pokemon(Pokemon original) {
        this(original.getName(), original.getTier(), original.getLevel(), original.getHealth(), original.getAttack(), original.getDefense(), original.getSpeed(), original.getEvolvesInto());
        this.textureAtlas = original.textureAtlas;
        for (Ability ability : original.getAbilities()) {
            this.abilities.add(ability.copy());
        }
    }

    public abstract String getType();

    public abstract double getEffectiveness(Pokemon otherPokemon);

    public abstract Pokemon copy();

    private String getEvolvesInto() {
        return this.evolvesInto;
    }

    private int getTier() {
        return this.tier;
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

    public void gainExp(int exp) {
        this.experience += exp;
        if (this.experience >= 100) {
            this.levelUp();
        }
    }

    public void levelUp() {
        this.level++;
        if (this.level >= LVL_EVOLUTION_TO_TIER_2 && this.tier == 1 && this.evolvesInto != null) {
            this.evolve();
        }
        if (this.level >= LVL_EVOLUTION_TO_TIER_3 && this.tier == 2 && this.evolvesInto != null) {
            this.evolve();
        }
        this.experience = 0;
        this.setMaxHealth((int) (this.getMaxHealth() + 1 / 50f * this.getMaxHealth()));
        this.attack += 1 / 50f * this.attack;
        this.defense += 1 / 50f * this.defense;
        this.speed += 1 / 50f * this.speed;
    }

    public int getLevel() {
        return this.level;
    }


    public void evolve() {
        this.tier++;
    }

    public void addAbility(Ability ability) {
        if (!this.abilities.contains(ability)) {
            this.abilities.add(ability);
        }
    }

    public void increaseHP(int hp) {
        if (this.health + hp <= this.maxHealth) {
            this.health += hp;
        } else {
            this.health = this.maxHealth;
        }
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
            this.health = 0;
        }
    }

    //TODO implement
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

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(int health) {
        this.maxHealth = health;
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

    public Iterable<Effect> getStatusEffects() {
        return this.statusEffects;
    }

    public void addStatusEffect(Effect effect) {
        this.statusEffects.add(effect);
    }

    public Ability getAbility(int index) {
        return this.abilities.get(index);
    }

    public Iterable<Ability> getAbilities() {
        return this.abilities;
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
        if (pokemonStats[0] >= 1) {
            for (int i = 0; i < pokemonStats[0]; i++) {
                this.levelUp();
            }
            this.setMaxHealth(pokemonStats[1]);
            this.setAttack(pokemonStats[2]);
            this.setDefense(pokemonStats[3]);
            this.setSpeed(pokemonStats[4]);
        }
    }

    public int getNumOfAbilities() {
        return this.abilities.size();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.textureAtlas.findRegion(this.getName()), this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), this.getWidth(), this.getHeight(), this.getScaleX(), this.getScaleY(), this.getRotation());

        if (Constants.DEBUG) {
            BitmapFont font = new BitmapFont();
            font.draw(batch, this.toString(), this.getX() - this.getWidth() - 100, this.getY());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NAME: ").append(this.getName())
                .append(" LVL : ").append(this.getLevel())
                .append(" EXP: ").append(this.experience)
                .append(" HP: ").append(this.getHealth())
                .append(" ATT: ").append(this.getAttack())
                .append(" DEF: ").append(this.getDefense())
                .append(" SPD: ").append(this.getSpeed())
                .append(" PP: ").append(this.getPowerPoints()).append(System.lineSeparator())
                .append("FAINTED: ").append(this.hasFainted())
                .append(" COLLECTED: ").append(this.isCollected)
                .append(" ABILITIES: ").append(this.getAbilities().spliterator().getExactSizeIfKnown())
                .append(" STATUS EFFECTS: ").append(this.getStatusEffects().spliterator().getExactSizeIfKnown())
                .append(" EVOLVES INTO: ").append(this.evolvesInto)
                .append(" TIER: ").append(this.tier)
                .append(System.lineSeparator());

        for (Ability ability : this.getAbilities()) {
            sb.append(ability).append(System.lineSeparator());
        }

        for (Effect statusEffect : this.getStatusEffects()) {
            sb.append(statusEffect).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
