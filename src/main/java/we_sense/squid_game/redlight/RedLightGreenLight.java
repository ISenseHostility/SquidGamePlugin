package we_sense.squid_game.redlight;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import we_sense.squid_game.SquidGame;
import we_sense.squid_game.runnables.RLGLRunnable;
import we_sense.squid_game.handler.BossBarTimerHandler;
import we_sense.squid_game.utils.SquidGameUtil;

import java.util.ArrayList;

public class RedLightGreenLight {
    private final SquidGameUtil squidGameUtil = new SquidGameUtil();
    private final SquidGame squidGame = SquidGame.getInstance();
    private boolean mayMove = true;
    private boolean ongoing = false;
    private final Server server = squidGame.getServer();
    private final ArrayList<Player> activePlayers = new ArrayList<>();
    private static RedLightGreenLight instance;
    private BukkitTask task;

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
            task = new RLGLRunnable(activePlayers, server).runTaskTimer(squidGame, squidGameUtil.secondsToTicks(5), squidGameUtil.secondsToTicks(squidGameUtil.randomIntBetween(5, 12)));
            ongoing = true;
        }
        this.server = this.squidGame.getServer();
        BossBarTimerHandler bossBarTimerHandler = new BossBarTimerHandler();
        bossBarTimerHandler.startredLightGreenLightBossBar(activePlayers,120);
        runnableTaskTimer();
        ongoing = true;
    }

    public void stopRedLightGreenLight() {
        if (ongoing) {
            ongoing = false;
            activePlayers.clear();
            task.cancel();
        }
    }

    public void sendMoveTitles(boolean mayMove, ArrayList<Player> activePlayers){
    public void runnableTaskTimer() {
            server.getScheduler().runTaskAsynchronously(squidGame, () -> {
               while(ongoing) {
                runnable.run();
                try {
                    Thread.sleep(squidGameUtil.randomIntBetween(5, 12)* 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
            });
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

    public void setMayMove(boolean mayMove) {
        this.mayMove = mayMove;
    }

    public ArrayList<Player> getActivePlayers() {
        return activePlayers;
    }

    public boolean isOngoing() {
        return ongoing;
    }
}
