package we_sense.squid_game;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

public final class SquidGame extends JavaPlugin {
    private Server server;
    private boolean mayMove = true;

    @Override
    public void onEnable() {
        this.server = this.getServer();
        runnable.runTaskTimer(this, secondsToTicks(5), secondsToTicks(randomIntBetween(15, 30)));
    }

    @Override
    public void onDisable() {

    }

    BukkitRunnable runnable = new BukkitRunnable() {
        @Override
        public void run() {
            if (server.getOnlinePlayers().size() != 0) {
                Player player = (Player) server.getOnlinePlayers().toArray()[0];
                if (mayMove) {
                    mayMove = false;
                    player.sendMessage("false");
                } else {
                    mayMove = true;
                    player.sendMessage("true");
                }
            }
        }
    };

    public static int randomIntBetween(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }
}
