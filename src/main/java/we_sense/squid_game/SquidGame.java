package we_sense.squid_game;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import we_sense.squid_game.commands.JoinGameCommand;
import we_sense.squid_game.commands.StartGameCommand;
import we_sense.squid_game.commands.StopGameCommand;
import we_sense.squid_game.events.SquidGameListener;

public final class SquidGame extends JavaPlugin {
    private Server server;

    @Override
    public void onEnable() {
        server = this.getServer();
        server.getPluginManager().registerEvents(new SquidGameListener(), this);
        this.getCommand("join_game").setExecutor(new JoinGameCommand());
        this.getCommand("start_game").setExecutor(new StartGameCommand());
        this.getCommand("stop_game").setExecutor(new StopGameCommand());
    }

    @Override
    public void onDisable() {
    }
}
