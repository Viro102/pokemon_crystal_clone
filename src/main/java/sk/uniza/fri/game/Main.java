package sk.uniza.fri.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.item.Effect;
import sk.uniza.fri.item.Potion;
import sk.uniza.fri.pokemon.FirePokemon;
import sk.uniza.fri.pokemon.Pokemon;
import sk.uniza.fri.pokemon.WaterPokemon;

public class Main {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setResizable(true);
        config.setTitle("Pokemon Crystal");
        config.useVsync(true);
        config.setWindowSizeLimits(Constants.MAP_SIZE, Constants.MAP_SIZE, -1, -1);
        new Lwjgl3Application(new GameClass(), config);

        Player player = new Player("Ash");
        Potion attPot = new Potion("Attack Potion", "Buffs attack", Effect.BUFF_ATTACK);
        Pokemon charizard = new FirePokemon("charizard", 10, 1, 1, 1);
        Pokemon squirtle = new WaterPokemon("squirtle", 5, 2, 2, 10);


        System.out.println(player);
        System.out.println(charizard);
        System.out.println(squirtle);
        player.takeItem(attPot);
        System.out.println(player);
        player.collectPokemon(charizard);
        System.out.println(player);
        player.useItem(attPot, charizard.getName());
    }
}
