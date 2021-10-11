package we_sense.squid_game;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import we_sense.squid_game.redlight.RedLightGreenLight;

import java.util.concurrent.ThreadLocalRandom;

public final class SquidGame extends JavaPlugin {
    private static SquidGame instance;
    private Server server;
    private final JavaPlugin javaPlugin = this;
    public SquidGame(){}

    public static SquidGame getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.server = this.getServer();
        RedLightGreenLight.getInstance().startRedLightGreenLight();
    }

    @Override
    public void onDisable() {
    }

    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }
}
