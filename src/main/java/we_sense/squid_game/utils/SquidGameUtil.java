package we_sense.squid_game.utils;

import org.bukkit.entity.Player;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SquidGameUtil {
    private final Random random = new Random();

    public int secondsToTicks(int seconds) {
        return seconds * 20;
    }

    public void sendTitle(Player player, String title){
        player.sendTitle(title, null, 0, 30, 9);
    }

    public int randomIntBetween(int min, int max) {
        return random.nextInt(max-min)+min;

    }
}
