package we_sense.squid_game.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import we_sense.squid_game.rlgl.RedLightGreenLight;
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
                        redLightGreenLight.getActivePlayers().remove(player);
                        player.setHealth(0);
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
                event.setDeathMessage(event.getEntity().getDisplayName() + " was shot in the " + squidGameUtil.getRandomBodyPart() + ".");
            }
        }
    }
}
