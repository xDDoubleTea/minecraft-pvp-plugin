package WorldStructures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

public class BridgeCages {
    private String cageName;

    private int[] centerOfCageBottomInCageWorld;
    private int[] centerOfCageBottom;
    private int halfWidth;
    private int halfDepth;
    private int height;
    private int[] colored;
    //if colored[0] is 0 then it means the cage is not dependent to team color
    //if it is 1 then it is dependent.
    //colored[1] means the color of the cage, 0 for blue, 1 for red
    private HashMap<int[], Material> cageScheme = new HashMap<>();



    public BridgeCages(String cageName, int[] colored, World world, int[] centerOfCageBottom) {
        this.cageName = cageName;
        this.colored = colored;
        this.setCageScheme(world);
        this.centerOfCageBottom = centerOfCageBottom;
    }

    private void setCageScheme(World world){
        for(int j=0; j<=this.height; j++){
            for(int i=0; i<=this.halfWidth;i++){
                for(int k=0; k<=this.halfDepth;j++){
                    assert centerOfCageBottomInCageWorld != null;
                    Location[] locs = {
                            new Location(world, (double) centerOfCageBottomInCageWorld[0]+i, (double) centerOfCageBottomInCageWorld[1]+j, (double) centerOfCageBottomInCageWorld[2]+k),
                            new Location(world, (double) centerOfCageBottomInCageWorld[0]-i, (double) centerOfCageBottomInCageWorld[1]+j, (double) centerOfCageBottomInCageWorld[2]+k),
                            new Location(world, (double) centerOfCageBottomInCageWorld[0]-i, (double) centerOfCageBottomInCageWorld[1]+j, (double) centerOfCageBottomInCageWorld[2]-k),
                            new Location(world, (double) centerOfCageBottomInCageWorld[0]+i, (double) centerOfCageBottomInCageWorld[1]+j, (double) centerOfCageBottomInCageWorld[2]-k)
                    };
                    for(int amogus=0;amogus<=3;amogus++){
                        int[] relativeCoords = {locs[amogus].getBlockX()-this.centerOfCageBottomInCageWorld[0], locs[amogus].getBlockY()-this.centerOfCageBottomInCageWorld[1], locs[amogus].getBlockZ()-this.centerOfCageBottomInCageWorld[2]};
                        cageScheme.put(relativeCoords, world.getBlockAt(locs[amogus]).getType());
                    }
                }
            }
        }
    }

    public void putCage(World world){
        for(int j=0; j<=this.height; j++){
            for(int i=0; i<=this.halfWidth;i++){
                for(int k=0; k<=this.halfDepth;j++){
                    Location[] locs = {
                            new Location(world, (double) this.centerOfCageBottom[0]+i, (double) this.centerOfCageBottom[1]+j, (double) this.centerOfCageBottom[2]+k),
                            new Location(world, (double) this.centerOfCageBottom[0]-i, (double) this.centerOfCageBottom[1]+j, (double) this.centerOfCageBottom[2]+k),
                            new Location(world, (double) this.centerOfCageBottom[0]-i, (double) this.centerOfCageBottom[1]+j, (double) this.centerOfCageBottom[2]-k),
                            new Location(world, (double) this.centerOfCageBottom[0]+i, (double) this.centerOfCageBottom[1]+j, (double) this.centerOfCageBottom[2]-k)
                    };
                }
            }
        }
        if(this.colored[0] == 1){

        }
    }

    public void clearCage(){
        //clears cage
    }

}
