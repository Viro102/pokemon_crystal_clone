package sk.uniza.fri.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setResizable(true);
        config.setTitle("Pokemon Crystal");
        config.useVsync(true);
        config.setWindowSizeLimits(Constants.MAP_SIZE, Constants.MAP_SIZE, -1, -1);
        new Lwjgl3Application(new GameClass(), config);
    }
}
