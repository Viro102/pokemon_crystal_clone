package sk.uniza.fri.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] arg) {
//        Player player = new Player("Ash");
//        System.out.println(player);
//        Potion attPot = new Potion("Attack Potion", "Buffs attack", Effect.BUFF_ATTACK);
//        Pokemon charizard = new FirePokemon("Charizard", 10, 1, 1, 1);
//        Pokemon squirtle = new WaterPokemon("Squirtle", 5, 2, 2, 10);
//        System.out.println(charizard);
//        System.out.println(squirtle);
//        player.takeItem(attPot);
//        System.out.println(player);
//        player.collectPokemon(charizard);
//        System.out.println(player);
//        player.useItem(attPot, charizard.getName());
//
//
//        for (Pokemon p : player.getPokedex()) {
//            if (p instanceof FirePokemon) {
//                System.out.println(squirtle);
//                p.attack(squirtle, (AttackSkill) p.getSkills().next());
//                System.out.println(squirtle);
//            }
//        }

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setResizable(true);
        config.setTitle("Pokemon Crystal");
        config.useVsync(true);
        config.setMaximized(true);
        config.setWindowSizeLimits(Constants.MAP_SIZE, Constants.MAP_SIZE, -1, -1);
        new Lwjgl3Application(new GameClass(), config);
    }
}
