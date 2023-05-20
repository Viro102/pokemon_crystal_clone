package sk.uniza.fri.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.pokemon.Pokemon;

public class BattleScreen implements Screen {
    private final GameScreen gameScreen;
    private final GameClass game;
    private final Skin skin;
    private final Table table;
    private final Stage stage;
    private final Player player;
    private final ButtonGroup<TextButton> buttonGroup;
    private final Pokemon enemyPokemon;
    private Pokemon selectedPokemon;

    // TODO proper battle screen
    public BattleScreen(GameClass game, GameScreen gameScreen, Skin skin, Player player, Pokemon enemyPokemon) {
        this.gameScreen = gameScreen;
        this.game = game;
        this.skin = skin;
        this.player = player;
        this.enemyPokemon = enemyPokemon;

        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this.stage);

        this.table = new Table();
        this.table.setFillParent(true);
        this.stage.addActor(this.table);

//        OrthographicCamera cam = (OrthographicCamera) this.stage.getCamera();
//        cam.zoom = 2;
//        cam.update();

        TextButton attack = new TextButton("Fight", this.skin);
        TextButton bag = new TextButton("Bag", this.skin);
        TextButton switchPokemon = new TextButton("Pokemon", this.skin);
        TextButton run = new TextButton("Run", this.skin);

        attack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BattleScreen.this.attackUI();
            }
        });

        bag.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BattleScreen.this.bagUI();
            }
        });

        switchPokemon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BattleScreen.this.switchPokemonUI();
            }
        });

        run.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BattleScreen.this.game.setScreen(BattleScreen.this.gameScreen);
            }
        });

        this.buttonGroup = new ButtonGroup<>(attack, bag, switchPokemon, run);

        this.selectedPokemon = this.player.getFirstPokemon();

        this.enemyPokemon.scaleBy(3);

        this.switchMainUI();
    }

    private void attackUI() {
        this.table.clearChildren();
        TextButton backButton = new TextButton("Back", this.skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BattleScreen.this.switchMainUI();
            }
        });
        this.table.add(backButton).row();
    }

    private void bagUI() {
        this.table.clearChildren();
        TextButton backButton = new TextButton("Back", this.skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BattleScreen.this.switchMainUI();
            }
        });
        this.table.add(backButton).row();
    }

    private void switchMainUI() {
        this.table.clearChildren();
        this.table.defaults().uniform().fill();
        this.table.bottom().right();
        for (Button button : this.buttonGroup.getButtons()) {
            this.table.add(button);
            this.table.row();
        }

        this.selectedPokemon.setPosition(150, 150);
        this.enemyPokemon.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 200);

        this.selectedPokemon.scaleBy(3);
        this.stage.addActor(this.selectedPokemon);
        this.stage.addActor(this.enemyPokemon);
    }

    private void switchPokemonUI() {
        this.table.clearChildren();
        for (Pokemon pokemon : this.player.getParty()) {
            TextButton button = new TextButton(pokemon.getName(), this.skin);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Switching to " + pokemon.getName());
                    BattleScreen.this.selectedPokemon.remove();
                    BattleScreen.this.selectedPokemon = pokemon;
                    BattleScreen.this.switchMainUI();
                }
            });
            this.table.add(button).row();
        }
        TextButton backButton = new TextButton("Back", this.skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BattleScreen.this.switchMainUI();
            }
        });
        this.table.add(backButton).row();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);

        Gdx.app.log("BattleScreen", String.valueOf(((OrthographicCamera) this.stage.getCamera()).zoom));
        this.stage.getViewport().apply();
        this.stage.act(delta);
        this.stage.draw();
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
        Gdx.input.setInputProcessor(this.stage);
    }
}