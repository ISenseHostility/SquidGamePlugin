package we_sense.squid_game.handler;

import org.apache.commons.lang.time.DateFormatUtils;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import we_sense.squid_game.SquidGame;
import we_sense.squid_game.utils.SquidGameUtil;

import java.util.ArrayList;

public class BossBarTimerHandler {
    private final SquidGame squidGame = JavaPlugin.getPlugin(SquidGame.class);
    private final SquidGameUtil squidGameUtil = new SquidGameUtil();
    private BossBar bossBar;
    private ArrayList<Player> players;
    private double currentTime;
    private double time;

    public BossBarTimerHandler() {

    }

    public void startRedLightGreenLightBossBar(ArrayList<Player> players, int time) {
        this.players = players;
        this.time = time;
        this.currentTime = time;
        bossBar = squidGame.getServer().createBossBar(String.valueOf(time), BarColor.GREEN, BarStyle.SEGMENTED_20, BarFlag.PLAY_BOSS_MUSIC);
        bossBarUpdateRedLightGreenLightRunnable.runTaskTimer(squidGame,0, squidGameUtil.secondsToTicks(1));
    }

    private final BukkitRunnable bossBarUpdateRedLightGreenLightRunnable = new BukkitRunnable(){

        @Override
        public void run() {
            for(Player player : players){
                bossBar.addPlayer(player);
            }

            currentTime--;
            String timeInMinutes = DateFormatUtils.format((long) (currentTime * 1000), "mm:ss");
            bossBar.setTitle(timeInMinutes);
            double percentage = currentTime / time;
            bossBar.setProgress(percentage);
            switch ((int) currentTime){
                case 80:
                    bossBar.setColor(BarColor.BLUE);
                    break;
                case 40:
                    bossBar.setColor(BarColor.YELLOW);
                    break;
                case 20:
                    bossBar.setColor(BarColor.RED);
                    break;
                case 0:
                    for(Player player : players){
                        player.setHealth(0);
                    }

                    bossBarUpdateRedLightGreenLightRunnable.cancel();
                    break;
            }
        }
    };

    public BossBar getBossBar() {
        return bossBar;
    }

    public BukkitRunnable getBossBarUpdateRedLightGreenLightRunnable() {
        return bossBarUpdateRedLightGreenLightRunnable;
    }
}
