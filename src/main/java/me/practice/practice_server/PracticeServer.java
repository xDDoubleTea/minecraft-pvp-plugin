package me.practice.practice_server;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public final class PracticeServer extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    // Plugin startup logic
    getLogger().info("Practice Server Plugin Enabled");
    getServer().getPluginManager().registerEvents(this, this);
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
    getLogger().info("Practice Server Plugin Disabled");
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    event.getPlayer().sendMessage(
        String.format("%sWelcome to the practice server, %s", ChatColor.AQUA, event.getPlayer().getName()));
    getLogger().info(String.format("Sent welcome message to player %s", event.getPlayer().getName()));
  }

  @EventHandler
  public void onPlayerDropItem(PlayerDropItemEvent event) {
    event.setCancelled(true);
  }
}
