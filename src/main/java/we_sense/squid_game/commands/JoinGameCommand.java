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
        if (sender instanceof Player) {
            if (args.length == 1) {
                if (sender.hasPermission("squid_game.join")) {
                    Player player = (Player) sender;
                    switch (args[0]) {
                        case "1":
                            RedLightGreenLight.getInstance().getActivePlayers().add(player);
                            sender.sendMessage(ChatColor.GREEN + "Joined Red Light Green Light.");
                            return true;
                        default:
                            sender.sendMessage(ChatColor.RED + "That game does not exist.");
                            return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to join games.");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "This command only accepts a single argument.");
                return false;
            }
        }
        return false;
    }
}
