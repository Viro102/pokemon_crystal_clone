package sk.uniza.fri.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import sk.uniza.fri.item.Inventory;
import sk.uniza.fri.item.Item;
import sk.uniza.fri.item.UsableItem;
import sk.uniza.fri.pokemon.Pokedex;
import sk.uniza.fri.pokemon.Pokemon;

public class Player extends Actor {
    private final String name;
    private final Pokedex pokedex;
    private final Inventory inventory;
    private final Vector2 velocity;
    private final float speed;
    private final TextureAtlas textureAtlas;

    public Player(String name) {
        super();
        this.name = name;
        this.textureAtlas = new TextureAtlas("Atlas/Main.atlas");
        this.pokedex = new Pokedex();
        this.inventory = new Inventory();
        this.velocity = new Vector2();
        this.speed = 150;
        this.setPosition(415, 190);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.textureAtlas.findRegion("player"), this.getX(), this.getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        System.out.println("x:" + this.getX() + " y:" + this.getY());
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

    public void reset() {
        this.setPosition(415, 190);
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

    public void takeItem(Item item) {
        this.inventory.addItem(item);
    }

    public void useItem(UsableItem item, String pokemon) {
        item.use(this, this.pokedex.getPokemon(pokemon));
    }

    public void collectPokemon(Pokemon pokemon) {
        this.pokedex.addPokemon(pokemon);
    }

    public Pokedex getPokedex() {
        return this.pokedex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player: ");
        sb.append(this.name);
        sb.append(" has: ");
        for (Pokemon pokemon : this.pokedex) {
            sb.append(pokemon.getName());
            sb.append("\n");
        }
        sb.append("Inventory: ");
        for (Item item : this.inventory) {
            sb.append(item.getName());
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
