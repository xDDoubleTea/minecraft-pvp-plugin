package WorldStructures;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;

public class BridgeMap {
    private World world;
    private String mapName;
    private Location[] spawnLocation;
    private Location[] goalLocation;
    private int[][] blockChangeAllowedArea;
    //{{lower_boundx,upper_boundx}.{lower_boundy,upper_boundy},[lower_boundz,upper_boundz]}

    public BridgeMap(World world, String mapName) {
        this.world = world;
        this.mapName = mapName;
        //Gets data of spawn locations and goal location of the given map name.
    }

    public Location[] getSpawnLocation() {
        return spawnLocation;
    }

    public World getWorld() {
        return world;
    }

    public Location[] getGoalLocation() {
        return goalLocation;
    }

    public int[][] getBlockChangeAllowedArea() {
        return blockChangeAllowedArea;
    }
}
