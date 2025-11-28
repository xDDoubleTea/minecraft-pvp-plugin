package ItemMgr;


import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class BridgeKitManager {

    private HashMap<UUID, BridgeItems> Kits;

    public HashMap<UUID, BridgeItems> getKits() {
        return Kits;
    }

    public void setKits(HashMap<UUID, BridgeItems> kits) {
        Kits = kits;
    }

    public BridgeItems getBridgeKit(UUID uuid){
        return this.getKits().getOrDefault(uuid, null);
    }

    public BridgeItems createKit(Player player){
        BridgeItems items = new BridgeItems();
        items.setItemData(0, new int[]{1,5}, 3,4,9,2);
        HashMap<UUID, BridgeItems> kit = this.getKits();
        kit.put(player.getUniqueId(), items);
        this.setKits(kit);
        return items;
    }

    public BridgeItems updateKit(Player player, BridgeItems items){
        if(this.getBridgeKit(player.getUniqueId()) == null){
            return this.createKit(player);
        }
        else{
            HashMap<UUID, BridgeItems> kit = this.getKits();
            kit.replace(player.getUniqueId(), items);
            this.setKits(kit);
            return this.getKits().get(player.getUniqueId());
        }
    }

    public void deleteKit(Player player){
        if(this.getBridgeKit(player.getUniqueId()) != null){
            HashMap<UUID, BridgeItems> kit = this.getKits();
            kit.remove(player.getUniqueId());
            this.setKits(kit);
        }
    }

    public BridgeItems readKit(Player player){
        return this.getBridgeKit(player.getUniqueId()) == null ? this.createKit(player) : this.getKits().get(player.getUniqueId());
    }
}
