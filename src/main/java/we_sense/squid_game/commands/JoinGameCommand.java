package we_sense.squid_game.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import we_sense.squid_game.redlight.RedLightGreenLight;

public class JoinGameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equals("join_game")) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    if (sender.hasPermission("squid_game.join")) {
                        Player player = (Player) sender;
                        switch (args[0]) {
                            case "red_light_green_light":
                                RedLightGreenLight.getInstance().getActivePlayers().add(player);
                                sender.sendMessage(ChatColor.GREEN + "Joined Red Light Green Light.");
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
