package sk.uniza.fri.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class BattleScreen implements Screen {
    private final GameScreen gameScreen;
    private final GameClass game;
    private final Skin skin;
    private Table table;
    private Stage stage;
    private ButtonGroup<TextButton> buttonGroup;

    // TODO proper battle screen
    public BattleScreen(GameClass game, GameScreen gameScreen, Skin skin) {
        this.gameScreen = gameScreen;
        this.game = game;
        this.skin = skin;
        this.create();
    }

    public void create() {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);

        this.table = new Table();
        this.table.setFillParent(true);
        this.stage.addActor(this.table);

        TextButton attack = new TextButton("Fight", this.skin);
        TextButton bag = new TextButton("Bag", this.skin);
        TextButton switchPokemon = new TextButton("Pokémon", this.skin);
        TextButton run = new TextButton("Run", this.skin);

        this.buttonGroup = new ButtonGroup<>(attack, bag, switchPokemon, run);

        this.table.defaults().uniform().fill();
        this.table.bottom().right();
        for (Button button : this.buttonGroup.getButtons()) {
            this.table.add(button);
            this.table.row();
        }
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        this.stage.act(delta);
        this.stage.draw();

        for (TextButton button : this.buttonGroup.getButtons()) {
            if (button.isPressed()) {
                System.out.println("clicked " + button);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            this.game.setScreen(this.gameScreen);
        }
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.skin.dispose();
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