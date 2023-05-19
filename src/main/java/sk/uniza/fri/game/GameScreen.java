package sk.uniza.fri.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sk.uniza.fri.character.Player;

public class GameScreen implements Screen {
    private final GameClass game;
    private final Stage environmentStage;
    private final Stage hudStage;
    private final Player player;
    private final Skin skin;
    private final GameController gameController;
    private Array<RectangleMapObject> collisionObjects;
    private Array<RectangleMapObject> specialObjects;
    private TiledMapRenderer mapRenderer;
    private TiledMap tiledMap;

    public GameScreen(GameClass game, Skin skin) {
        this.game = game;
        this.skin = skin;
        this.environmentStage = new Stage(new FitViewport(Constants.MAP_SIZE, Constants.MAP_SIZE));
        this.hudStage = new Stage(new ScreenViewport());

        this.environmentStage.getCamera().position.set(Constants.MAP_SIZE / 2f, Constants.MAP_SIZE / 2f, 0);
        this.environmentStage.getCamera().update();

        this.tiledMap = new TmxMapLoader().load("tmx/starter_town.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap, this.environmentStage.getBatch());

        this.initZone();

        this.player = new Player("Ash");
        this.environmentStage.addActor(this.player);

        this.gameController = new GameController(this.player, this);

        // Add HUD actors to the hudStage
        // Example:
        ImageButton inventory = new ImageButton(skin);
        inventory.setSize(100, 100);
        this.hudStage.addActor(inventory);
    }

    public void initZone() {
        if (this.collisionObjects != null) {
            this.collisionObjects.clear();
        } else if (this.specialObjects != null) {
            this.specialObjects.clear();
        }
        MapLayer collisionLayer = this.tiledMap.getLayers().get("Collision");
        this.collisionObjects = collisionLayer.getObjects().getByType(RectangleMapObject.class);

        if (this.tiledMap.getLayers().get("Special") != null) {
            MapLayer specialLayer = this.tiledMap.getLayers().get("Special");
            this.specialObjects = specialLayer.getObjects().getByType(RectangleMapObject.class);
            this.specialObjects.get(0).setName("starter_forest");
        }
    }

    public void switchZone(String nextZone) {
        this.tiledMap.dispose();

        this.tiledMap = new TmxMapLoader().load("tmx/" + nextZone + ".tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap, this.environmentStage.getBatch());

        this.initZone();
        this.player.setPosition(55, 462);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        this.gameController.handleInput();
        this.gameController.checkCollisions(delta, this.collisionObjects, this.specialObjects);
        this.player.update(delta);
        this.environmentStage.getCamera().update();

        this.mapRenderer.setView((OrthographicCamera) this.environmentStage.getCamera());
        this.mapRenderer.render();

        this.environmentStage.act(delta);
        this.environmentStage.draw();

//        this.hudStage.act(delta);
//        this.hudStage.draw();

        System.out.println("x: " + this.player.getX() + ", y: " + this.player.getY() + ", W: " + this.player.getWidth() + ", H: " + this.player.getHeight());

        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            this.game.setScreen(new BattleScreen(this.game, this, this.skin));
        }
    }

    @Override
    public void resize(int width, int height) {
        this.environmentStage.getViewport().update(width, height, true);
        this.hudStage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        this.environmentStage.dispose();
        this.hudStage.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void show() {
    }
}