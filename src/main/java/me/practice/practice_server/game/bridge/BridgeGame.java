package me.practice.practice_server.game.bridge;

import me.practice.practice_server.model.Team;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import java.util.HashMap;

public class BridgeGame {
  private final JavaPlugin plugin;

  private BukkitTask task;
  private HashMap<String, Team> teams = new HashMap<>();
  private HashMap<String, Team> playersInGame = new HashMap<>();

  public BridgeGame(JavaPlugin plugin, String mapName, String id, Player[] players) {
    this.plugin = plugin;
    teams.put("Blue", new Team(org.bukkit.Color.BLUE, "Blue"));
    teams.put("Red", new Team(org.bukkit.Color.RED, "Red"));

    // this.bridgeMap = new BridgeMap(mapName, id);
    // this.players = players;
    // this.scores = new byte[] { 0, 0 };
    // this.playerCage = new BridgeCages[] {
    // new BridgeCages(players[0].getLocation()),
    // new BridgeCages(players[1].getLocation())
    // };
    // this.inCombatTag = new HashMap<Player, Boolean>() {
    // {
    // put(players[0], false);
    // put(players[1], false);
    // }
    // };
    // this.inBowTag = new HashMap<Player, Boolean>() {
    // {
    // put(players[0], false);
    // put(players[1], false);
    // }
    // };
    // this.onCountDown = false;
  }

  private void giveItem(Player player, int color) {
    // BridgeKitManager kitManager = new BridgeKitManager();
    // BridgeItems bridgeItems = kitManager.readKit(player);
    //
    // ArrayList<ItemStack> itemData = bridgeItems.getItemData(color);
    // int[] slotsList = {
    // bridgeItems.getIronSwordSlot(),
    // bridgeItems.getBlockSlot()[0],
    // bridgeItems.getBlockSlot()[1],
    // bridgeItems.getPickaxeSlot(),
    // bridgeItems.getBowSlot(),
    // bridgeItems.getGoldenAppleSlot(),
    // bridgeItems.getArrowSlot()
    // };
    // for (int j = 0; j <= 6; j++) {
    // player.getInventory().setItem(slotsList[j], itemData.get(j));
    // }
    // player.getInventory().setArmorContents(bridgeItems.getArmor(color));
  }

  public void countDown(boolean first, int scored, int second) {
    // this.onCountDown = true;
    // task = Bukkit.getScheduler().runTaskTimer(this.plugin, () -> {
    // String scoreDisplay = ChatColor.BLUE + String.valueOf(this.scores[0]) +
    // ChatColor.WHITE + "-" + ChatColor.RED
    // + this.scores[1];
    // int count;
    // count = second;
    // for (int i = 0; i <= 1; i++) {
    // if (first) {
    // players[i].sendTitle(ChatColor.GOLD + String.valueOf(count), "");
    // } else {
    // players[i].sendTitle(players[scored].getDisplayName() + " has scored!",
    // scoreDisplay);
    // }
    // players[i].playNote(players[i].getLocation(), Instrument.PIANO, Note.sharp(2,
    // Note.Tone.F));
    // }
    // if (count <= 0) {
    // for (int i = 0; i <= 1; i++) {
    // players[i].playSound(players[i].getLocation(), Sound.LEVEL_UP, 10, 1);
    // players[i].playSound(players[i].getLocation(), Sound.FIREWORK_BLAST, 10, 1);
    // players[i].sendTitle("", "");
    // playerCage[i].clearCage();
    // }
    // this.onCountDown = false;
    // task.cancel();
    // }
    // count--;
    // }, 20, 20);
  }

  public void combatTag(Player player) {
    // task = Bukkit.getScheduler().runTaskTimer(this.plugin, () -> {
    // int count = 5;
    // if (count <= 0) {
    // this.inCombatTag.replace(player, false);
    // task.cancel();
    // }
    // count--;
    // }, 20, 20);
  }

  public void bowTag(Player player) {
    // task = Bukkit.getScheduler().runTaskTimer(this.plugin, () -> {
    // int count = 5;
    // if (count <= 0) {
    // this.inBowTag.replace(player, false);
    // task.cancel();
    // }
    // count--;
    // }, 20, 20);
  }

  public void startGame() {
    // this.bridgeMap.getWorld().setGameRuleValue("keepInventory", "true");
    // this.bridgeMap.getWorld().setGameRuleValue("randomTickSpeed", "0");
    // for (int i = 0; i <= 1; i++) {
    // this.players[i].setGameMode(GameMode.SURVIVAL);
    // this.playerCage[i].putCage(this.bridgeMap.getWorld());
    // if (i == 0) {
    // this.players[i].setPlayerListName(ChatColor.BLUE + players[i].getName());
    // this.players[i].setDisplayName(ChatColor.BLUE + players[i].getName());
    // } else {
    // this.players[i].setPlayerListName(ChatColor.RED + players[i].getName());
    // this.players[i].setDisplayName(ChatColor.RED + players[i].getName());
    // }
    // this.giveItem(players[i], i);
    // players[i].teleport(bridgeMap.getSpawnLocation()[i]);
    // }
    // this.countDown(true, 0, 5);
  }

  public int getPlayerTeam(Player player) {
    // if (player.getName().equalsIgnoreCase(players[0].getName())) {
    // return 0;
    // }
    // return 1;
    return 1;
  }

  public void addScore(int team) {
    // scores[team] += (byte) 1;
    // if (scores[team] == 5) {
    // this.endGame(team, false);
    // }
    // this.countDown(false, team, 5);
  }

  public void endGame(int winner, boolean forced) {
    // if (!forced) {
    // players[winner].setHealth(20.0);
    // players[winner].removePotionEffect(PotionEffectType.ABSORPTION);
    // if (winner == 0) {
    // players[1].setGameMode(GameMode.SPECTATOR);
    // } else {
    // players[0].setGameMode(GameMode.SPECTATOR);
    // }
    // for (int i = 0; i <= 1; i++) {
    // players[i].sendTitle(players[winner].getDisplayName() + ChatColor.GRAY + "
    // has won!", ChatColor.BLUE
    // + String.valueOf(scores[0]) + ChatColor.GRAY + "-" + ChatColor.RED +
    // String.valueOf(scores[1]));
    // players[i].teleport(bridgeMap.getSpawnLocation()[0]);
    // }
    // } else {
    // players[winner].setHealth(20.0);
    // players[winner].removePotionEffect(PotionEffectType.ABSORPTION);
    // players[winner].sendTitle(players[winner].getDisplayName() + ChatColor.GRAY +
    // " has won! Uh Oh!", ChatColor.BLUE
    // + String.valueOf(scores[0]) + ChatColor.GRAY + "-" + ChatColor.RED +
    // String.valueOf(scores[1]));
    // players[winner].teleport(bridgeMap.getSpawnLocation()[0]);
    // }
    // task = Bukkit.getScheduler().runTaskTimer(this.plugin, () -> {
    // boolean isForced = forced;
    // int count = 5;
    // if (count <= 0) {
    // if (isForced) {
    // // teleport to lobby
    // // players[winner].teleport();
    // }
    // task.cancel();
    // }
    // count--;
    // }, 20, 20);
  }

  public boolean fallInGoal(int blockX, int blockZ, int team) {
    // return (blockX - this.bridgeMap.getGoalLocation()[team].getBlockX() <= 5
    // && blockZ - this.bridgeMap.getGoalLocation()[team].getBlockZ() <= 5) ||
    // (blockX - this.bridgeMap.getGoalLocation()[team].getBlockX() >= -5
    // && blockZ - this.bridgeMap.getGoalLocation()[team].getBlockZ() >= -5);
    return false;
  }

}
