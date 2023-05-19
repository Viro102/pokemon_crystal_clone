package sk.uniza.fri.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import sk.uniza.fri.character.Player;

public class GameController {
    private final Player player;
    private final Array<RectangleMapObject> collisionObjects;
    private final Array<RectangleMapObject> specialObjects;

    public GameController(Player player, Array<RectangleMapObject> collisionObjects, Array<RectangleMapObject> specialObjects) {
        this.player = player;
        this.collisionObjects = collisionObjects;
        this.specialObjects = specialObjects;
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.player.moveUp();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.player.moveDown();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.player.moveLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.player.moveRight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            this.player.reset();
        }
    }

    public void checkCollisions(float delta) {
        Rectangle futureX = new Rectangle(this.player.getX() + this.player.getVelocity().x * delta, this.player.getY(), this.player.getWidth(), this.player.getHeight());
        Rectangle futureY = new Rectangle(this.player.getX(), this.player.getY() + this.player.getVelocity().y * delta, this.player.getWidth(), this.player.getHeight());

        for (RectangleMapObject collisionObject : this.collisionObjects) {
            if (futureX.overlaps(collisionObject.getRectangle())) {
                this.player.getVelocity().x = 0;
            }
            if (futureY.overlaps(collisionObject.getRectangle())) {
                this.player.getVelocity().y = 0;
            }
        }

        for (RectangleMapObject specialObject : this.specialObjects) {
            if (futureX.overlaps(specialObject.getRectangle())) {
                System.out.println("Special object");
            }
            if (futureY.overlaps(specialObject.getRectangle())) {
                System.out.println("Special object");
            }
        }
    }
}
