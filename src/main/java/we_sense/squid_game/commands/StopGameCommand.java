package we_sense.squid_game.commands;

import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import we_sense.squid_game.rlgl.RedLightGreenLight;

import java.util.ArrayList;

public class StopGameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                if (sender.hasPermission("squid_game.stop")) {
                    RedLightGreenLight RLGL = RedLightGreenLight.getInstance();

                    switch (args[0]) {
                        case "1":
                            RLGL.stopRedLightGreenLight();
                            for (Player p : RLGL.getActivePlayers()) {
                                p.sendMessage(ChatColor.GREEN + "Red Light Green Light has been stopped.");
                            }
                            if (!RLGL.getActivePlayers().contains(sender)) {
                                sender.sendMessage(ChatColor.GREEN + "Red Light Green Light has been stopped.");
                            }
                            RLGL.getBossBarTimerHandler().getBossBar().removeAll();
                            RLGL.getBossBarTimerHandler().getBossBarUpdateRedLightGreenLightRunnable().cancel();
                            return true;
                        default:
                            sender.sendMessage(ChatColor.RED + "That game does not exist.");
                            return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to stop games.");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "This command only accepts a single argument.");
                return false;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }
    }

    private void stopGame(BossBar timer, ArrayList<Player> players) {

    }

}
