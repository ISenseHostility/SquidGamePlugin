package we_sense.squid_game.rlgl;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import we_sense.squid_game.SquidGame;
import we_sense.squid_game.handler.BossBarTimerHandler;
import we_sense.squid_game.utils.SquidGameUtil;

import java.util.ArrayList;

public class RedLightGreenLight {
    private final SquidGameUtil squidGameUtil = new SquidGameUtil();
    private final SquidGame squidGame = JavaPlugin.getPlugin(SquidGame.class);
    private boolean mayMove = false;
    private boolean ongoing = false;
    private final BossBarTimerHandler bossBarTimerHandler = new BossBarTimerHandler();
    private final Server server = squidGame.getServer();
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
        if (!ongoing) {
            ongoing = true;
            runnableTaskTimer();
            bossBarTimerHandler.startRedLightGreenLightBossBar(activePlayers,120);
        }
    }

    public void stopRedLightGreenLight() {
        if (ongoing) {
            ongoing = false;
            activePlayers.clear();
            bossBarTimerHandler.getBossBar().removeAll();
            bossBarTimerHandler.getBossBarUpdateRedLightGreenLightRunnable().cancel();
        }
    }

    public void runnableTaskTimer() {
        server.getScheduler().runTaskAsynchronously(squidGame, () -> {
            while(ongoing) {
                toggleMove();
                try {
                    Thread.sleep(squidGameUtil.randomIntBetween(5, 12) * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void toggleMove() {
        if (activePlayers.size() != 0) {
            mayMove = !mayMove;
            sendMoveTitles(mayMove, activePlayers);
        }
    }

    private void sendMoveTitles(boolean mayMove, ArrayList<Player> activePlayers){
        for (Player player : activePlayers) {
            if (mayMove) {
                squidGameUtil.sendTitle(player, ChatColor.GREEN + "" + ChatColor.BOLD + "MOVE");
            } else {
                squidGameUtil.sendTitle(player,ChatColor.RED + "" + ChatColor.BOLD + "STOP");
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

    public BossBarTimerHandler getBossBarTimerHandler() {
        return bossBarTimerHandler;
    }
}
