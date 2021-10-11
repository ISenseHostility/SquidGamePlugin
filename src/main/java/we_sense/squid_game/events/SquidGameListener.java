package we_sense.squid_game.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import we_sense.squid_game.SquidGame;

public class SquidGameListener implements Listener {

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        if (event.getFrom().getZ() != event.getTo().getZ() || event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY()) {
            boolean mayMove = SquidGame.getInstance().isMayMove();

            if (!mayMove) {
                event.getPlayer().damage(Integer.MAX_VALUE);
            }
        }
    }
}
