package sk.uniza.fri.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {
    private final GameClass game;
    private final Stage menu;
    private final Table table;
    private final Skin skin;
    private ButtonGroup<TextButton> buttons;

    public MainMenuScreen(GameClass game, Skin skin) {
        this.game = game;
        this.table = new Table();
        this.skin = skin;

        this.createButtons();
        this.menu = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this.menu);
        this.menu.addActor(this.table);
    }

    private void createButtons() {
        this.table.setFillParent(true);

        TextButton playButton = new TextButton("Play the game", this.skin);
        TextButton settingsButton = new TextButton("Settings", this.skin);
        TextButton exitButton = new TextButton("Exit the game", this.skin);
        this.buttons = new ButtonGroup<>(playButton, settingsButton, exitButton);

        for (TextButton button : this.buttons.getButtons()) {
            this.table.row();
            this.table.add(button).width(190);
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        this.menu.act(delta);
        this.menu.draw();

        // 0 - play, 1 - settings, 2 - exit
        if (this.buttons.getButtons().get(0).isPressed()) {
            this.game.setScreen(new GameScreen(this.game, this.skin));
        }
        if (this.buttons.getButtons().get(1).isPressed()) {
            this.game.setScreen(new SettingsScreen(this.game, this.skin));
        }
        if (this.buttons.getButtons().get(2).isPressed()) {
            Gdx.app.log("INFO", "exiting...");
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        this.menu.getViewport().update(width, height, true);
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
        this.menu.dispose();
    }
}
