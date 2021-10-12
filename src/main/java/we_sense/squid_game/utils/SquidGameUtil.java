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

    public void setPlayerData(Player player, String dataName, PersistentDataType dataType, Object value) {
        player.getPersistentDataContainer().set(
                getKey(dataName),
                dataType,
                value
        );
    }

    public Object getPlayerData(Player player, String dataName, PersistentDataType dataType) {
        return player.getPersistentDataContainer().get(
                getKey(dataName),
                dataType
        );
    }

    public void setDeathByPlugin(Player player, int on) {
        setPlayerData(player, "waKilledByPlugin", PersistentDataType.INTEGER, on);
    }

    public int getDeathByPlugin(Player player) {
        if (getPlayerData(player, "wasKilledByPlugin", PersistentDataType.INTEGER) != null) {
            return (int) getPlayerData(player, "wasKilledByPlugin", PersistentDataType.INTEGER);
        } else {
            return 0;
        }
    }

    public String getRandomBodyPart() {
        return bodyParts[randomIntBetween(0, 6)];
    }
}
