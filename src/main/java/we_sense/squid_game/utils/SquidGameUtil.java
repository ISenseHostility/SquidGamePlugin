package we_sense.squid_game.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import we_sense.squid_game.SquidGame;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SquidGameUtil {
    private final Random random = new Random();
    private final SquidGame squidGame = SquidGame.getInstance();
    private String[] bodyParts = {"head", "leg", "arm", "neck", "foot", "hand", "chest"};

    public int secondsToTicks(int seconds) {
        return seconds * 20;
    }

    public void sendTitle(Player player, String title){
        player.sendTitle(title, null, 0, 30, 9);
    }

    public int randomIntBetween(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public NamespacedKey getKey(String name) {
        return new NamespacedKey(squidGame ,name);
    }

    public void setDeathByPlugin(Player player, int on) {
        player.getPersistentDataContainer().set(
                    getKey("wasKilledByPlugin"),
                    PersistentDataType.INTEGER,
                    on
                );
    }

    public int getDeathByPlugin(Player player) {
        if (player.getPersistentDataContainer().get(getKey("wasKilledByPlugin"), PersistentDataType.INTEGER) != null) {
            return player.getPersistentDataContainer().get(
                    getKey("wasKilledByPlugin"),
                    PersistentDataType.INTEGER
            );
        } else {
            return 0;
        }
    }

    public String getRandomBodyPart() {
        return bodyParts[randomIntBetween(0, 6)];
    }
}
