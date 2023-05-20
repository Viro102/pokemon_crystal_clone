package sk.uniza.fri.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import sk.uniza.fri.pokemon.Pokedex;

public class GameClass extends Game {
    @Override
    public void create() {
        Skin skin = new Skin(Gdx.files.internal("skin\\skin.json"), new TextureAtlas("skin\\skin.atlas"));
        Pokedex pokedex = new Pokedex();
        this.screen = new MainMenuScreen(this, skin, pokedex);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.screen.dispose();
    }
}