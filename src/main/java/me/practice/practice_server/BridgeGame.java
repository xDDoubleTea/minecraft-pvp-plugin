package me.practice.practice_server;

import ItemMgr.BridgeItems;
import ItemMgr.BridgeKitManager;
import WorldStructures.BridgeCages;
import WorldStructures.BridgeMap;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BridgeGame implements Listener {
    private String mapName;
    private String id;
    private BridgeMap bridgeMap;
    private Player[] players;
    private byte[] scores;
    private BridgeCages[] playerCage;
    private boolean onCountDown;
    private JavaPlugin plugin;
    private HashMap<Player, Boolean> inCombatTag;
    private HashMap<Player, Boolean> inBowTag;
    private HashMap<Player, Boolean> bowOnCD;

    public BridgeGame(String mapName, String id, Player[] players) {
        this.mapName = mapName;
        this.id = id;
        this.players = players;
        this.inCombatTag = new HashMap<>();
        this.inBowTag = new HashMap<>();
        for(int i=0;i<=1;i++) {
            this.inCombatTag.put(players[i], false);
            this.inBowTag.put(players[i],false);
            this.bowOnCD.put(players[i],false);
        }
    }

    private int[] getXYZ(Location location){
        return new int[]{location.getBlockX(),location.getBlockY(),location.getBlockZ()};
    }

    private void giveItem(Player player, int color){
        BridgeKitManager kitManager = new BridgeKitManager();
        BridgeItems bridgeItems = kitManager.readKit(player);

        ArrayList<ItemStack> itemData = bridgeItems.getItemData(color);
        int[] slotsList = {
                bridgeItems.getIronSwordSlot(),
                bridgeItems.getBlockSlot()[0],
                bridgeItems.getBlockSlot()[1],
                bridgeItems.getPickaxeSlot(),
                bridgeItems.getBowSlot(),
                bridgeItems.getGoldenAppleSlot(),
                bridgeItems.getArrowSlot()
        };
        for(int j=0;j<=6;j++) {
            player.getInventory().setItem(slotsList[j], itemData.get(j));
        }
        player.getInventory().setArmorContents(bridgeItems.getArmor(color));
    }

    private BukkitTask task;
    public void countDown(boolean first, int scored, int second){
        this.onCountDown = true;
        task = Bukkit.getScheduler().runTaskTimer(this.plugin, ()-> {
            String scoreDisplay = ChatColor.BLUE + String.valueOf(this.scores[0]) + ChatColor.WHITE + "-" + ChatColor.RED + this.scores[1];
            int count;
            count = second;
            for (int i = 0; i <= 1; i++) {
                if (first) {
                    players[i].sendTitle(ChatColor.GOLD + String.valueOf(count), "");
                } else {
                    players[i].sendTitle(players[scored].getDisplayName() + " has scored!", scoreDisplay);
                }
                players[i].playNote(players[i].getLocation(), Instrument.PIANO, Note.sharp(2, Note.Tone.F));
            }
            if(count<=0){
                for(int i=0;i<=1;i++){
                    players[i].playSound(players[i].getLocation(), Sound.LEVEL_UP, 10, 1);
                    players[i].playSound(players[i].getLocation(), Sound.FIREWORK_BLAST, 10, 1);
                    players[i].sendTitle("","");
                    playerCage[i].clearCage();
                }
                this.onCountDown = false;
                task.cancel();
            }
            count--;
        }, 20, 20);
    }

    public void combatTag(Player player){
        task = Bukkit.getScheduler().runTaskTimer(this.plugin, ()-> {
            int count = 5;
            if(count<=0){
                this.inCombatTag.replace(player, false);
                task.cancel();
            }
            count--;
        }, 20, 20);
    }

    public void bowTag(Player player){
        task = Bukkit.getScheduler().runTaskTimer(this.plugin, ()-> {
            int count = 5;
            if(count<=0){
                this.inBowTag.replace(player, false);
                task.cancel();
            }
            count--;
        }, 20, 20);
    }

    public void bowCoolDown(Player player, BridgeItems item){
        this.bowOnCD.replace(player, true);
        task = Bukkit.getScheduler().runTaskTimer(this.plugin, ()-> {
            int count = 3;
            player.setLevel(count);
            if(count<=0){
                this.bowOnCD.replace(player, false);
                player.getInventory().setItem(item.getArrowSlot(), new ItemStack(Material.ARROW));
                task.cancel();
            }
            count--;
        }, 20, 20);
    }

    public void startGame(){
        this.bridgeMap.getWorld().setGameRuleValue("keepInventory", "true");
        this.bridgeMap.getWorld().setGameRuleValue("randomTickSpeed", "0");
        for(int i=0;i<=1;i++){
            this.players[i].setGameMode(GameMode.SURVIVAL);
            this.playerCage[i].putCage(this.bridgeMap.getWorld());
            if(i == 0){
                this.players[i].setPlayerListName(ChatColor.BLUE+players[i].getName());
                this.players[i].setDisplayName(ChatColor.BLUE+players[i].getName());
            }
            else{
                this.players[i].setPlayerListName(ChatColor.RED+players[i].getName());
                this.players[i].setDisplayName(ChatColor.RED+players[i].getName());
            }
            this.giveItem(players[i], i);
            players[i].teleport(bridgeMap.getSpawnLocation()[i]);
        }
        this.countDown(true,0,5);
    }

    public int getPlayerTeam(Player player){
        if(player.getName().equalsIgnoreCase(players[0].getName())){
            return 0;
        }
        return 1;
    }

    public void addScore(int team){
        scores[team] += (byte) 1;
        if(scores[team] == 5){
            this.endGame(team, false);
        }
        this.countDown(false, team,5);
    }

    public void endGame(int winner, boolean forced){
        if(!forced) {
            players[winner].setHealth(20.0);
            players[winner].removePotionEffect(PotionEffectType.ABSORPTION);
            if (winner == 0) {
                players[1].setGameMode(GameMode.SPECTATOR);
            } else {
                players[0].setGameMode(GameMode.SPECTATOR);
            }
            for (int i = 0; i <= 1; i++) {
                players[i].sendTitle(players[winner].getDisplayName() + ChatColor.GRAY + " has won!", ChatColor.BLUE + String.valueOf(scores[0]) + ChatColor.GRAY + "-" + ChatColor.RED + String.valueOf(scores[1]));
                players[i].teleport(bridgeMap.getSpawnLocation()[0]);
            }
        }
        else{
            players[winner].setHealth(20.0);
            players[winner].removePotionEffect(PotionEffectType.ABSORPTION);
            players[winner].sendTitle(players[winner].getDisplayName() + ChatColor.GRAY + " has won! Uh Oh!", ChatColor.BLUE + String.valueOf(scores[0]) + ChatColor.GRAY + "-" + ChatColor.RED + String.valueOf(scores[1]));
            players[winner].teleport(bridgeMap.getSpawnLocation()[0]);
        }
        task = Bukkit.getScheduler().runTaskTimer(this.plugin, ()-> {
            boolean isForced = forced;
            int count = 5;
            if(count<=0){
                if(isForced){
                    //teleport to lobby
                    //players[winner].teleport();
                }
                task.cancel();
            }
            count--;
        }, 20, 20);
    }

    public boolean fallInGoal(int blockX, int blockZ, int team){
        return (blockX-this.bridgeMap.getGoalLocation()[team].getBlockX()<=5 && blockZ-this.bridgeMap.getGoalLocation()[team].getBlockZ()<=5)||
                (blockX-this.bridgeMap.getGoalLocation()[team].getBlockX()>=-5 && blockZ-this.bridgeMap.getGoalLocation()[team].getBlockZ()>=-5);
    }

    @EventHandler
    public void onPlayerScroe(PlayerPortalEvent event){
        event.setCancelled(true);
        Location portal_loc = event.getFrom();
        Player player = event.getPlayer();
        int team = getPlayerTeam(event.getPlayer());
        BridgeMap map = this.bridgeMap;
        if((team == 0 && fallInGoal(portal_loc.getBlockX(), portal_loc.getBlockZ(), 0) )|| (team == 1 && fallInGoal(portal_loc.getBlockX(), portal_loc.getBlockZ(), 1))){
            player.sendMessage(ChatColor.RED+"You fell into your own goal! Enjoy the void death...");
            player.teleport(map.getSpawnLocation()[team]);
        }
        else if ((team == 0 && fallInGoal(portal_loc.getBlockX(), portal_loc.getBlockZ(), 1) )|| (team == 1 && fallInGoal(portal_loc.getBlockX(), portal_loc.getBlockZ(), 0))){
            for(int i=0;i<=1;i++){
                players[i].sendMessage(player.getDisplayName()+ChatColor.GRAY+" has scored!\n"+ ChatColor.BLUE + String.valueOf(scores[0]) + ChatColor.GRAY + "-" + ChatColor.RED + String.valueOf(scores[1]));
                players[i].playSound(players[i].getLocation(), Sound.LEVEL_UP, 10 ,10);
                this.addScore(team);
            }
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event){
        int team = this.getPlayerTeam(event.getPlayer());
        this.endGame(team, true);
    }

    @EventHandler
    public void goldenApple(PlayerItemConsumeEvent event){
        if(event.getItem().getType().equals(Material.GOLDEN_APPLE)){
            Player player = event.getPlayer();
            ItemStack gapple = event.getItem();
            gapple.setAmount(event.getItem().getAmount()-1);
            System.out.println(gapple);
            int heldItemSlot = player.getInventory().getHeldItemSlot();
            player.getInventory().setItem(heldItemSlot, gapple);
            if(!player.hasPotionEffect(PotionEffectType.ABSORPTION)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 120*20,0));
            }
            player.setHealth(20.0);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player && event.getFinalDamage()>=((Player) event.getEntity()).getHealth() && !event.getCause().name().equalsIgnoreCase("fall")){
            event.setCancelled(true);
            Player player = ((Player) event.getEntity()).getPlayer();
            player.teleport(this.bridgeMap.getSpawnLocation()[this.getPlayerTeam(player)]);
            player.setHealth(20.0);
            player.removePotionEffect(PotionEffectType.ABSORPTION);

            this.inCombatTag.replace(player, false);
            this.inBowTag.replace(player, false);

            String deathMsg = "";
            int playerTeam = this.getPlayerTeam(player);
            int opponent=0;
            if(playerTeam == 0){
                opponent = 1;
            }
            if(event.getCause().name().equalsIgnoreCase(EntityDamageEvent.DamageCause.ENTITY_ATTACK.name())){
                deathMsg = player.getDisplayName()+ ChatColor.GRAY +" was killed by "+players[opponent].getDisplayName();
            }
            else if(event.getCause().name().equalsIgnoreCase(EntityDamageEvent.DamageCause.PROJECTILE.name())){
                deathMsg = player.getDisplayName() + ChatColor.GRAY +" was shot by " + players[opponent].getDisplayName();
            }
            for(int i=0;i<=1;i++){
                players[i].playSound(players[i].getLocation(), Sound.ORB_PICKUP, 10,10);
                players[i].sendMessage(deathMsg);
            }
        }
        else if(event.getEntity() instanceof Player && event.getCause().name().equalsIgnoreCase(EntityDamageEvent.DamageCause.ENTITY_ATTACK.name())){
            Player player = ((Player) event.getEntity()).getPlayer();
            if(!this.inCombatTag.get(player)){
                this.inCombatTag.replace(player, true);
                this.combatTag(player);
            }
        }
        else if (event.getEntity() instanceof Player && event.getCause().name().equalsIgnoreCase(EntityDamageEvent.DamageCause.PROJECTILE.name())) {
            Player player = ((Player) event.getEntity()).getPlayer();
            if(!this.inBowTag.get(player)){
                this.inBowTag.replace(player, true);
                this.bowTag(player);
            }
        }
    }

    @EventHandler
    public void onPlayerFallInVoid(PlayerMoveEvent event){
        if(event.getPlayer().getLocation().getY()<=79.0){
            Player player = event.getPlayer();
            player.teleport(this.bridgeMap.getSpawnLocation()[this.getPlayerTeam(player)]);
            player.setHealth(20.0);
            player.removePotionEffect(PotionEffectType.ABSORPTION);
            String voidMessage = "";
            int team = this.getPlayerTeam(player);
            int opponent=0;
            if(team == 0){
                opponent=1;
            }
            if(this.inCombatTag.get(players[opponent]) || this.inBowTag.get(players[opponent])){
                for (int i = 0; i <= 1; i++) {
                    players[i].playSound(players[i].getLocation(), Sound.ORB_PICKUP, 10, 10);
                    players[i].sendMessage(player.getDisplayName()+ ChatColor.GRAY + " was hit into void by " +players[opponent].getDisplayName());
                }
            }
            else {
                for (int i = 0; i <= 1; i++) {
                    players[i].playSound(players[i].getLocation(), Sound.ORB_PICKUP, 10, 10);
                    players[i].sendMessage(player.getDisplayName() + ChatColor.GRAY + " fell into the void.");
                }
            }
        }
    }

    @EventHandler
    public void onBowShot(EntityShootBowEvent event){
        if(event.getEntity() instanceof Player){
            Player player = ((Player) event.getEntity()).getPlayer();
            BridgeItems item = new BridgeItems();
            if(this.onCountDown){
                event.setCancelled(true);
                player.getInventory().setItem(item.getArrowSlot(), new ItemStack(Material.ARROW));
                player.sendMessage(ChatColor.RED+"You cannot shoot arrow during countdown!");
            }
            else if(this.bowOnCD.get(player)){
                event.setCancelled(true);
                player.getInventory().setItem(item.getArrowSlot(), new ItemStack(Material.ARROW));
                player.sendMessage(ChatColor.RED+"Bow is on cooldown!");
            }
            else{
                this.bowCoolDown(player,item);
            }
        }
    }
    @EventHandler
    public void dropItem(PlayerDropItemEvent event){
        event.setCancelled(true);
        event.getPlayer().sendMessage(ChatColor.RED+"You cannot drop items while in game!");
    }

    @EventHandler
    public void arrowInGround(ProjectileHitEvent event){
        event.getEntity().remove();
    }
}
