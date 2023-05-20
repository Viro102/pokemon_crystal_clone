package sk.uniza.fri.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.pokemon.*;

public class MainMenuScreen implements Screen {
    private final GameClass game;
    private final Skin skin;
    private final Pokedex pokedex;
    private final SettingsScreen settingsScreen;
    private final Stage menu;
    private final Table table;
    private ButtonGroup<TextButton> buttons;
    private boolean isPlayClicked;

    public MainMenuScreen(GameClass game, Skin skin, Pokedex pokedex) {
        this.game = game;
        this.skin = skin;
        this.pokedex = pokedex;
        this.isPlayClicked = false;
        this.settingsScreen = new SettingsScreen(this.game, this.skin, this);

        this.table = new Table();
        this.createButtons();
        this.menu = new Stage(new ScreenViewport());
        this.menu.addActor(this.table);

        Gdx.input.setInputProcessor(this.menu);
    }

    private void createButtons() {
        this.table.setFillParent(true);

        TextButton playButton = new TextButton("Play the game", this.skin);
        TextButton settingsButton = new TextButton("Settings", this.skin);
        TextButton exitButton = new TextButton("Exit the game", this.skin);
        this.buttons = new ButtonGroup<>(playButton, settingsButton, exitButton);

        for (TextButton button : this.buttons.getButtons()) {
            this.table.add(button).width(200);
            this.table.row().uniform();
        }
    }

    private void createEnterNameDialog() {
        Label welcomeLabel = new Label("Hello, welcome to the world of Johto\n, please enter your name: ", this.skin);

        this.table.add(welcomeLabel);
        this.table.row().uniform();

        TextField nameField = new TextField("", this.skin);
        nameField.setMessageText("Enter your name");
        this.table.add(nameField).width(200);
        this.table.row().uniform();

        TextButton continueButton = new TextButton("Continue", this.skin);
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Player player = new Player(nameField.getText(), MainMenuScreen.this.pokedex);
                player.addPokemonToParty(new WaterPokemon("totodile", 100, 10, 10, 10));
                MainMenuScreen.this.game.setScreen(new GameScreen(MainMenuScreen.this.game, MainMenuScreen.this.skin, player, MainMenuScreen.this.pokedex, MainMenuScreen.this.settingsScreen));
            }
        });
        this.table.add(continueButton).width(200);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.53f, 0.88f, 1, 1);
        this.menu.getViewport().apply();
        this.menu.act(delta);
        this.menu.draw();

        // 0 - play, 1 - settings, 2 - exit
        if (!this.isPlayClicked) {
            if (this.buttons.getButtons().get(0).isPressed()) {
                this.isPlayClicked = true;
                this.buttons.clear();
                this.table.clear();
                this.createEnterNameDialog();
            } else if (this.buttons.getButtons().get(1).isPressed()) {
                this.game.setScreen(new SettingsScreen(this.game, this.skin, this));
            } else if (this.buttons.getButtons().get(2).isPressed()) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        this.menu.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        this.menu.dispose();
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
        Gdx.input.setInputProcessor(this.menu);
    }
}
