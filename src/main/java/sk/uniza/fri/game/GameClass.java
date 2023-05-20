package sk.uniza.fri.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.pokemon.ElectricPokemon;
import sk.uniza.fri.pokemon.FirePokemon;
import sk.uniza.fri.pokemon.GrassPokemon;
import sk.uniza.fri.pokemon.PoisonPokemon;
import sk.uniza.fri.pokemon.Pokedex;
import sk.uniza.fri.pokemon.RockPokemon;
import sk.uniza.fri.pokemon.WaterPokemon;

public class GameClass extends Game {
    @Override
    public void create() {
        Skin skin = new Skin(Gdx.files.internal("skin\\skin.json"), new TextureAtlas("skin\\skin.atlas"));
        Pokedex pokedex = new Pokedex();
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this, skin, pokedex);
        if (Constants.DEBUG) {
            Player player = new Player("Player", pokedex);
            SettingsScreen settingsScreen = new SettingsScreen(this, skin, mainMenuScreen);
            player.addPokemonToParty(new WaterPokemon("totodile", 100, 10, 15, 15));
            player.addPokemonToParty(new FirePokemon("charmander", 80, 15, 10, 15));
            player.addPokemonToParty(new GrassPokemon("chikorita", 100, 10, 20, 10));
            player.addPokemonToParty(new ElectricPokemon("pikachu", 70, 20, 10, 20));
            player.addPokemonToParty(new PoisonPokemon("grimer", 100, 30, 5, 5));
            player.addPokemonToParty(new RockPokemon("geodude", 120, 10, 20, 10));
            this.setScreen(new GameScreen(this, skin, player, pokedex, settingsScreen));
        } else {
            this.setScreen(mainMenuScreen);
        }
    }
}