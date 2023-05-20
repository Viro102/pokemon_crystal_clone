package sk.uniza.fri.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import sk.uniza.fri.game.Party;
import sk.uniza.fri.item.Inventory;
import sk.uniza.fri.item.Item;
import sk.uniza.fri.item.UsableItem;
import sk.uniza.fri.pokemon.Pokedex;
import sk.uniza.fri.pokemon.Pokemon;

public class Player extends Actor {
    private final Party party;
    private final Inventory inventory;
    private final Vector2 velocity;
    private final Pokedex pokedex;
    private final float speed;
    private final TextureAtlas textureAtlas;
    private final int gold;

    public Player(String name, Pokedex pokedex) {
        super();
        this.pokedex = pokedex;
        this.textureAtlas = new TextureAtlas("Atlas/Trainer.atlas");
        this.inventory = new Inventory();
        this.party = new Party();
        this.velocity = new Vector2();
        this.speed = 250;
        this.gold = 0;

        this.setPosition(415, 198);
        this.setWidth(16);
        this.setHeight(16);
        this.setName(name);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.textureAtlas.findRegion("player"), this.getX(), this.getY());
    }

    public int getMaxLvlOfParty() {
        return this.party.getMaxLevel();
    }

    public Vector2 getVelocity() {
        return this.velocity;
    }

    public void moveUp() {
        this.velocity.y += this.speed;
    }

    public void moveDown() {
        this.velocity.y -= this.speed;
    }

    public void moveLeft() {
        this.velocity.x -= this.speed;
    }

    public void moveRight() {
        this.velocity.x += this.speed;
    }


    public void update(float deltaTime) {
        this.setPosition(this.getX() + this.velocity.x * deltaTime, this.getY());
        this.velocity.x = 0;

        this.setPosition(this.getX(), this.getY() + this.velocity.y * deltaTime);
        this.velocity.y = 0;
    }

    // TODO enter pokemon combat
    public void engage() {
    }

    // TODO party management
    public void addPokemonToParty(Pokemon pokemon) {
        this.party.addPokemon(pokemon);
    }

    public void removePokemonFromParty(Pokemon pokemon) {
        this.party.removePokemon(pokemon);
    }

    public int getPartyStrength() {
        return this.party.getPowerPoints();
    }

    public void takeItem(Item item) {
        this.inventory.addItem(item);
    }

    public int getInvetorySize() {
        return this.inventory.getSize();
    }

    public void useItem(UsableItem item, String pokemon) {
        item.use(this, this.party.getPokemon(pokemon));
    }

    public void collectPokemon(Pokemon pokemon) {
        this.pokedex.getPokemon(pokemon.getName()).setCollected(true);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player: ")
                .append(this.getName())
                .append(System.lineSeparator())
                .append("Pokedex = [ ");
        for (Pokemon pokemon : this.pokedex) {
            sb.append(pokemon.getName()).append(", ");
        }
        sb.append(" ] ").append(System.lineSeparator());
        sb.append("Inventory = [ ");
        for (Item item : this.inventory) {
            sb.append(item.getName()).append(", ");
        }
        sb.append(" ] ").append(System.lineSeparator());
        return sb.toString();
    }
}
