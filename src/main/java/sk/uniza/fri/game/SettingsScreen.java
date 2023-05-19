package sk.uniza.fri.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen implements Screen {
    private final Stage stage;


    public SettingsScreen(GameClass game, Skin skin) {
        TextButton back = new TextButton("Back", skin);
        Slider slider = new Slider(0f, 100f, 1f, false, skin);
        this.stage = new Stage(new ScreenViewport());
        Table table = new Table(skin);

        table.add(slider);
        table.add(back);
        this.stage.addActor(table);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        this.stage.act();
        this.stage.draw();
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
