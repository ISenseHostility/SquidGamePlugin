package we_sense.squid_game.handler;

import org.bukkit.Server;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import we_sense.squid_game.SquidGame;

import java.util.ArrayList;

public class BossBarTimerHandler {
    SquidGame squidGame = SquidGame.getInstance();

    private BossBar bossBar;
    private final ArrayList<Player> players;
    private double currentTime;
    private double time;
    public BossBarTimerHandler(ArrayList<Player> players, int time){
        this.players = players;
        this.time = time;
        this.currentTime = time;
        bossBar = squidGame.getServer().createBossBar(String.valueOf(time), BarColor.BLUE, BarStyle.SEGMENTED_20, BarFlag.PLAY_BOSS_MUSIC);
        bossBarUpdatRunnable.runTaskTimer(squidGame,0,20);

    }
    private BukkitRunnable bossBarUpdatRunnable= new BukkitRunnable(){

        @Override
        public void run() {

            for(Player player : players){
                bossBar.addPlayer(player);
            }

            currentTime--;
            bossBar.setTitle(String.valueOf((int)currentTime));
            double percentage = currentTime / time;
            bossBar.setProgress(percentage);
            if(currentTime <= 0){
                for(Player player : players){
                    player.setHealth(0);
                    //todo remove killed players
                    bossBar.removeAll();
                    bossBarUpdatRunnable.cancel();
                }
            }
        }
    };
}
