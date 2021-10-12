package we_sense.squid_game.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import we_sense.squid_game.rlgl.RedLightGreenLight;
import we_sense.squid_game.rlgl.RlglLocation;
import we_sense.squid_game.utils.SquidGameUtil;

public class SquidGameListener implements Listener {
    private final SquidGameUtil squidGameUtil = new SquidGameUtil();

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        RedLightGreenLight redLightGreenLight = RedLightGreenLight.getInstance();
        if (redLightGreenLight.isOngoing()) {
            if (event.getFrom().getZ() != event.getTo().getZ() || event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY()) {
                boolean mayMove = redLightGreenLight.isMayMove();
                Player player = event.getPlayer();

                if (!mayMove) {
                    if (redLightGreenLight.getActivePlayers().contains(player)) {
                        squidGameUtil.setDeathByPlugin(player, 1);
                        player.setHealth(0);
                        redLightGreenLight.getActivePlayers().remove(player);
                        squidGameUtil.setDeathByPlugin(player, 0);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        if (player != null) {
            if (squidGameUtil.getDeathByPlugin(player) == 1) {
                event.setDeathMessage(player.getDisplayName() + " was shot in the " + squidGameUtil.getRandomBodyPart() + ".");
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent event){
        RlglLocation rlglLocation = new RlglLocation();
       event.setRespawnLocation(rlglLocation.getSpectaterLocation());
    }
}
