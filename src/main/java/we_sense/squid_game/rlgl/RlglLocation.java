package we_sense.squid_game.rlgl;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import we_sense.squid_game.SquidGame;

public class RlglLocation {
    private Location lobbylocation;
    private Location spectaterLocation;
    private final SquidGame squidGame = JavaPlugin.getPlugin(SquidGame.class);
    private final World world = squidGame.getServer().getWorld("world");

    public RlglLocation(){
        makeLocations();
    }
    public void makeLocations(){
        this.lobbylocation = new Location(world,45,101,-94,-180,0);
        this.spectaterLocation = new Location(world,36,103,-106,-90,0);
    }

    public Location getLobbylocation() {
        return lobbylocation;
    }

    public void setLobbylocation(Location lobbylocation) {
        this.lobbylocation = lobbylocation;
    }

    public Location getSpectaterLocation() {
        return spectaterLocation;
    }

    public void setSpectaterLocation(Location spectaterLocation) {
        this.spectaterLocation = spectaterLocation;
    }
}
