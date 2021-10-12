package we_sense.squid_game.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import we_sense.squid_game.rlgl.RedLightGreenLight;
import we_sense.squid_game.rlgl.RlglLocation;

public class JoinGameCommand implements CommandExecutor {
    private final RlglLocation rlglPosition = new RlglLocation();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                if (sender.hasPermission("squid_game.join")) {
                    Player player = (Player) sender;
                    RedLightGreenLight RLGL = RedLightGreenLight.getInstance();

                    switch (args[0]) {
                        case "1":
                            if (!RLGL.isOngoing()) {
                                if (!RLGL.getActivePlayers().contains(player)) {
                                    RLGL.getActivePlayers().add(player);
                                    player.teleport(rlglPosition.getLobbylocation());
                                    sender.sendMessage(ChatColor.GREEN + "Joined Red Light Green Light.");
                                    return true;
                                } else {
                                    sender.sendMessage(ChatColor.RED + "You have already joined Red Light Green Light.");
                                    return true;
                                }
                            }  else {
                                sender.sendMessage(ChatColor.RED + "Red Light Green Light has already started.");
                                return true;
                            }
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
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }
    }
}
