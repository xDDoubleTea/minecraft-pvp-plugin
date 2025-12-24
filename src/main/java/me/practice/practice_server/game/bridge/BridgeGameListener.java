package me.practice.practice_server.game.bridge;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.Location;

public class BridgeGameListener implements Listener {
  private BridgeGame bridgeGame;

  public BridgeGameListener(BridgeGame bridgeGame) {
    this.bridgeGame = bridgeGame;
  }

  @EventHandler
  public void onPlayerScroe(PlayerPortalEvent event) {
    event.setCancelled(true);
    Location portal_loc = event.getFrom();
    Player player = event.getPlayer();
    // int team = getPlayerTeam(event.getPlayer());
    // if ((team == 0 && fallInGoal(portal_loc.getBlockX(), portal_loc.getBlockZ(),
    // 0))
    // || (team == 1 && fallInGoal(portal_loc.getBlockX(), portal_loc.getBlockZ(),
    // 1))) {
    // player.sendMessage(ChatColor.RED + "You fell into your own goal! Enjoy the
    // void death...");
    // player.teleport(map.getSpawnLocation()[team]);
    // } else if ((team == 0 && fallInGoal(portal_loc.getBlockX(),
    // portal_loc.getBlockZ(), 1))
    // || (team == 1 && fallInGoal(portal_loc.getBlockX(), portal_loc.getBlockZ(),
    // 0))) {
    // for (int i = 0; i <= 1; i++) {
    // players[i].sendMessage(player.getDisplayName() + ChatColor.GRAY + " has
    // scored!\n" + ChatColor.BLUE
    // + String.valueOf(scores[0]) + ChatColor.GRAY + "-" + ChatColor.RED +
    // String.valueOf(scores[1]));
    // players[i].playSound(players[i].getLocation(), Sound.LEVEL_UP, 10, 10);
    // this.addScore(team);
    // }
    // }
  }

  @EventHandler
  public void onPlayerDisconnect(PlayerQuitEvent event) {
    // int team = this.getPlayerTeam(event.getPlayer());
    // this.endGame(team, true);
  }

  @EventHandler
  public void goldenApple(PlayerItemConsumeEvent event) {
    // if (event.getItem().getType().equals(Material.GOLDEN_APPLE)) {
    // Player player = event.getPlayer();
    // ItemStack gapple = event.getItem();
    // gapple.setAmount(event.getItem().getAmount() - 1);
    // System.out.println(gapple);
    // int heldItemSlot = player.getInventory().getHeldItemSlot();
    // player.getInventory().setItem(heldItemSlot, gapple);
    // if (!player.hasPotionEffect(PotionEffectType.ABSORPTION)) {
    // player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 120 *
    // 20, 0));
    // }
    // player.setHealth(20.0);
    // }
  }

  @EventHandler
  public void onPlayerDamage(EntityDamageEvent event) {
    // if (event.getEntity() instanceof Player && event.getFinalDamage() >=
    // ((Player) event.getEntity()).getHealth()
    // && !event.getCause().name().equalsIgnoreCase("fall")) {
    // event.setCancelled(true);
    // Player player = ((Player) event.getEntity()).getPlayer();
    // player.teleport(this.bridgeMap.getSpawnLocation()[this.getPlayerTeam(player)]);
    // player.setHealth(20.0);
    // player.removePotionEffect(PotionEffectType.ABSORPTION);
    //
    // this.inCombatTag.replace(player, false);
    // this.inBowTag.replace(player, false);
    //
    // String deathMsg = "";
    // int playerTeam = this.getPlayerTeam(player);
    // int opponent = 0;
    // if (playerTeam == 0) {
    // opponent = 1;
    // }
    // if
    // (event.getCause().name().equalsIgnoreCase(EntityDamageEvent.DamageCause.ENTITY_ATTACK.name()))
    // {
    // deathMsg = player.getDisplayName() + ChatColor.GRAY + " was killed by " +
    // players[opponent].getDisplayName();
    // } else if
    // (event.getCause().name().equalsIgnoreCase(EntityDamageEvent.DamageCause.PROJECTILE.name()))
    // {
    // deathMsg = player.getDisplayName() + ChatColor.GRAY + " was shot by " +
    // players[opponent].getDisplayName();
    // }
    // for (int i = 0; i <= 1; i++) {
    // players[i].playSound(players[i].getLocation(), Sound.ORB_PICKUP, 10, 10);
    // players[i].sendMessage(deathMsg);
    // }
    // } else if (event.getEntity() instanceof Player
    // &&
    // event.getCause().name().equalsIgnoreCase(EntityDamageEvent.DamageCause.ENTITY_ATTACK.name()))
    // {
    // Player player = ((Player) event.getEntity()).getPlayer();
    // if (!this.inCombatTag.get(player)) {
    // this.inCombatTag.replace(player, true);
    // this.combatTag(player);
    // }
    // } else if (event.getEntity() instanceof Player
    // &&
    // event.getCause().name().equalsIgnoreCase(EntityDamageEvent.DamageCause.PROJECTILE.name()))
    // {
    // Player player = ((Player) event.getEntity()).getPlayer();
    // if (!this.inBowTag.get(player)) {
    // this.inBowTag.replace(player, true);
    // this.bowTag(player);
    // }
    // }
  }

  @EventHandler
  public void onPlayerFallInVoid(PlayerMoveEvent event) {
    // if (event.getPlayer().getLocation().getY() <= 79.0) {
    // Player player = event.getPlayer();
    // player.teleport(this.bridgeMap.getSpawnLocation()[this.getPlayerTeam(player)]);
    // player.setHealth(20.0);
    // player.removePotionEffect(PotionEffectType.ABSORPTION);
    // String voidMessage = "";
    // int team = this.getPlayerTeam(player);
    // int opponent = 0;
    // if (team == 0) {
    // opponent = 1;
    // }
    // if (this.inCombatTag.get(players[opponent]) ||
    // this.inBowTag.get(players[opponent])) {
    // for (int i = 0; i <= 1; i++) {
    // players[i].playSound(players[i].getLocation(), Sound.ORB_PICKUP, 10, 10);
    // players[i].sendMessage(
    // player.getDisplayName() + ChatColor.GRAY + " was hit into void by " +
    // players[opponent].getDisplayName());
    // }
    // } else {
    // for (int i = 0; i <= 1; i++) {
    // players[i].playSound(players[i].getLocation(), Sound.ORB_PICKUP, 10, 10);
    // players[i].sendMessage(player.getDisplayName() + ChatColor.GRAY + " fell into
    // the void.");
    // }
    // }
    // }
  }

  @EventHandler
  public void onBowShot(EntityShootBowEvent event) {
    // if (event.getEntity() instanceof Player) {
    // Player player = ((Player) event.getEntity()).getPlayer();
    // BridgeItems item = new BridgeItems();
    // if (this.onCountDown) {
    // event.setCancelled(true);
    // player.getInventory().setItem(item.getArrowSlot(), new
    // ItemStack(Material.ARROW));
    // player.sendMessage(ChatColor.RED + "You cannot shoot arrow during
    // countdown!");
    // } else if (this.bowOnCD.get(player)) {
    // event.setCancelled(true);
    // player.getInventory().setItem(item.getArrowSlot(), new
    // ItemStack(Material.ARROW));
    // player.sendMessage(ChatColor.RED + "Bow is on cooldown!");
    // } else {
    // this.bowCoolDown(player, item);
    // }
    // }
  }

  @EventHandler
  public void dropItem(PlayerDropItemEvent event) {
    // event.setCancelled(true);
    // event.getPlayer().sendMessage(ChatColor.RED + "You cannot drop items while in
    // game!");
  }

  @EventHandler
  public void arrowInGround(ProjectileHitEvent event) {
    // event.getEntity().remove();
  }
}
