package we_sense.squid_game.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import we_sense.squid_game.redlight.RedLightGreenLight;

public class SquidGameListener implements Listener {

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        RedLightGreenLight redLightGreenLight = RedLightGreenLight.getInstance();
        if (redLightGreenLight.isOngoing()) {
            if (event.getFrom().getZ() != event.getTo().getZ() || event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY()) {
                boolean mayMove = redLightGreenLight.isMayMove();
                Player player = event.getPlayer();

                if (mayMove) {
                    if (redLightGreenLight.getActivePlayers().contains(player)) {
                        player.damage(Integer.MAX_VALUE);
                        redLightGreenLight.getActivePlayers().remove(player);
                    }
                }
            }
        }
    }
}
