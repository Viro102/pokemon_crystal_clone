package sk.uniza.fri.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen implements Screen {
    private final MainMenuScreen mainMenu;
    private final Skin skin;
    private final GameClass game;
    private final Stage stage;

    public SettingsScreen(GameClass game, Skin skin, MainMenuScreen mainMenu) {
        this.game = game;
        this.mainMenu = mainMenu;
        this.skin = skin;

        TextButton back = new TextButton("Back", this.skin);
        Slider slider = new Slider(0f, 100f, 1f, false, this.skin);
        this.stage = new Stage(new ScreenViewport());
        Table table = new Table(this.skin);


        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SettingsScreen.this.game.setScreen(SettingsScreen.this.mainMenu);
            }
        });

        table.setFillParent(true);
        table.add(slider).padBottom(150).row();
        table.add(back);
        this.stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.53f, 0.88f, 1, 1);

        this.stage.getViewport().apply();
        this.stage.act();
        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
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
        this.skin.dispose();
        this.stage.dispose();
    }
}
