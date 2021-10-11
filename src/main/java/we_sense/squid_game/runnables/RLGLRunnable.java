package we_sense.squid_game.runnables;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import we_sense.squid_game.redlight.RedLightGreenLight;

import java.util.ArrayList;

public class RLGLRunnable extends BukkitRunnable {
    RedLightGreenLight redLightGreenLight = RedLightGreenLight.getInstance();
    ArrayList<Player> activePlayers;
    Server server;

    public RLGLRunnable(ArrayList<Player> activePlayers, Server server) {
        this.activePlayers = activePlayers;
        this.server = server;
    }

    @Override
    public void run() {
        if (activePlayers.size() != 0) {
            server.getConsoleSender().sendMessage("IK STA AAN");
            if (redLightGreenLight.isMayMove()) {
                redLightGreenLight.setMayMove(false);
            } else {
                redLightGreenLight.setMayMove(true);
            }
            redLightGreenLight.sendMoveTitles(redLightGreenLight.isMayMove(), activePlayers);
        }
    }
}
