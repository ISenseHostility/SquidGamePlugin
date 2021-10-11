package we_sense.squid_game.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import we_sense.squid_game.redlight.RedLightGreenLight;

import java.util.ArrayList;

public class StartGameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equals("start_game")) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    if (sender.hasPermission("squid_game.start")) {
                        switch (args[0]) {
                            case "red_light_green_light":
                                ArrayList<Player> activePlayers = RedLightGreenLight.getInstance().getActivePlayers();
                                if (activePlayers.size() != 0) {
                                    RedLightGreenLight.getInstance().startRedLightGreenLight();
                                    for (Player p : activePlayers) {
                                        p.sendMessage(ChatColor.GREEN + "Red Light Green Light has been started.");
                                    }
                                    return true;
                                }
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
