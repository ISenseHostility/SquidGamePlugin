package we_sense.squid_game.redlight;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import we_sense.squid_game.SquidGame;
import we_sense.squid_game.utils.SquidGameUtil;

import java.util.ArrayList;

public class RedLightGreenLight {
    private final SquidGameUtil squidGameUtil = new SquidGameUtil();
    private final SquidGame squidGame = SquidGame.getInstance();
    private boolean mayMove = true;
    private boolean ongoing = false;
    private Server server;
    private final ArrayList<Player> activePlayers = new ArrayList<>();
    private static RedLightGreenLight instance;

    public static RedLightGreenLight getInstance() {
        if (instance == null) {
            instance = new RedLightGreenLight();
        }
        return instance;
    }

    private RedLightGreenLight() {
    }

    public void startRedLightGreenLight() {
        runnable.runTaskTimer(squidGame, squidGameUtil.secondsToTicks(5), squidGameUtil.secondsToTicks(squidGameUtil.randomIntBetween(5, 12)));
        ongoing = true;
        this.server = this.squidGame.getServer();
    }

    public void stopRedLightGreenLight() {
        ongoing = false;
        activePlayers.clear();
        runnable.cancel();
    }

    BukkitRunnable runnable = new BukkitRunnable() {
        @Override
        public void run() {
            if (activePlayers.size() != 0) {
                mayMove = !mayMove;
                sendMoveTitles(mayMove, activePlayers);
            }
        }
    };

    private void sendMoveTitles(boolean mayMove, ArrayList<Player> activePlayers){
        for (Player player : activePlayers) {
            if (mayMove) {
                squidGameUtil.sendTitle(player, ChatColor.RED + "" + ChatColor.BOLD + "STOP");
            } else {
                squidGameUtil.sendTitle(player,ChatColor.GREEN + "" + ChatColor.BOLD + "MOVE");
            }
        }
    }

    public boolean isMayMove() {
        return mayMove;
    }

    public ArrayList<Player> getActivePlayers() {
        return activePlayers;
    }

    public boolean isOngoing() {
        return ongoing;
    }
}
