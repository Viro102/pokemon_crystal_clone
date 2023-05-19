package sk.uniza.fri.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameClass extends Game {
    @Override
    public void create() {
        Skin skin = new Skin(Gdx.files.internal("skin\\skin.json"), new TextureAtlas("skin\\skin.atlas"));
        this.screen = new MainMenuScreen(this, skin);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.screen.dispose();
    }
}