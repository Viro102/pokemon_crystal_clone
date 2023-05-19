package sk.uniza.fri.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import sk.uniza.fri.character.Player;

public class GameController {
    private final Player player;
    private final GameScreen gameScreen;

    public GameController(Player player, GameScreen gameScreen) {
        this.player = player;
        this.gameScreen = gameScreen;
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            System.out.println("X: " + this.player.getX() + ", Y: " + this.player.getY());
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (!this.gameScreen.isPaused()) {
                this.gameScreen.pauseGame();
            } else {
                this.gameScreen.resumeGame();
            }
        }
    }

    public void checkCollisions(float delta, Array<RectangleMapObject> collisionObjects, Array<RectangleMapObject> specialObjects) {
        Rectangle futureX = new Rectangle(this.player.getX() + this.player.getVelocity().x * delta, this.player.getY(), this.player.getWidth(), this.player.getHeight());
        Rectangle futureY = new Rectangle(this.player.getX(), this.player.getY() + this.player.getVelocity().y * delta, this.player.getWidth(), this.player.getHeight());

        for (RectangleMapObject collisionObject : collisionObjects) {
            if (futureX.overlaps(collisionObject.getRectangle())) {
                this.player.getVelocity().x = 0;
            }
            if (futureY.overlaps(collisionObject.getRectangle())) {
                this.player.getVelocity().y = 0;
            }
        }

        for (RectangleMapObject specialObject : specialObjects) {
            if (futureX.overlaps(specialObject.getRectangle()) || futureY.overlaps(specialObject.getRectangle())) {
                String nextZone = specialObject.getName();
                this.gameScreen.switchZone(nextZone);
            }
        }
    }
}
