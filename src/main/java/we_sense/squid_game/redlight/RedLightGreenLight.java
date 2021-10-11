package we_sense.squid_game.redlight;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import we_sense.squid_game.SquidGame;
import we_sense.squid_game.utils.SquidGameUtil;

public class RedLightGreenLight {
    private final SquidGameUtil squidGameUtil = new SquidGameUtil();
    private final SquidGame squidGame = SquidGame.getInstance();
    private boolean mayMove = true;
    private final Server server;



    public RedLightGreenLight() {
        runnable.runTaskTimer(squidGame.getJavaPlugin(), squidGameUtil.secondsToTicks(5), squidGameUtil.secondsToTicks(squidGameUtil.randomIntBetween(5, 12)));
        this.server = this.squidGame.getServer();
    }

    BukkitRunnable runnable = new BukkitRunnable() {
        @Override
        public void run() {
            int onlinePlayersSize = server.getOnlinePlayers().size();
            if (onlinePlayersSize != 0) {
                mayMove = !mayMove;
                sendMoveTitles(mayMove, server.getOnlinePlayers().toArray());
            }
        }
    };

    private void sendMoveTitles(boolean mayMove, Object[] onlineplayers){
        for (Object onlinePlayer : onlineplayers) {
            Player player = (Player) onlinePlayer;
            if (mayMove) {
                squidGameUtil.sendTitle(player,"STOP");

            } else {
                squidGameUtil.sendTitle(player,"START");
            }
        }
    }
}
