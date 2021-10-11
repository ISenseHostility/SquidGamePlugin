package we_sense.squid_game.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import we_sense.squid_game.redlight.RedLightGreenLight;

public class StopGameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equals("stop_game")) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    if (sender.hasPermission("squid_game.stop")) {
                        switch (args[0]) {
                            case "red_light_green_light":
                                RedLightGreenLight.getInstance().stopRedLightGreenLight();
                                for (Player p : RedLightGreenLight.getInstance().getActivePlayers()) {
                                    p.sendMessage(ChatColor.GREEN + "Red Light Green Light has been started.");
                                }
                                if (!RedLightGreenLight.getInstance().getActivePlayers().contains(sender)) {
                                    sender.sendMessage(ChatColor.GREEN + "Red Light Green Light has been stopped.");
                                }
                                return true;
                            case "2":
                                return false;
                        }
                    }
                }
            }
        }
        return false;
    }
}
