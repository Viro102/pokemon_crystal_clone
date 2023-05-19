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

        TextButton attack = new TextButton("attack", this.skin);
        TextButton inventory = new TextButton("inventory", this.skin);
        TextButton defend = new TextButton("defend", this.skin);

        this.buttonGroup = new ButtonGroup<>(attack, inventory, defend);

        // Set table
        this.table.defaults().uniform().fill();
        this.table.bottom().right();
        for (Button button : this.buttonGroup.getButtons()) {
            this.table.add(button);
            this.table.row();
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, .2f, 1);
        this.stage.act(Gdx.graphics.getDeltaTime());
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
    public void dispose() {
    }
}