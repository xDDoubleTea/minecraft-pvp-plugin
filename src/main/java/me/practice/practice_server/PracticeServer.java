package me.practice.practice_server;

import org.bukkit.plugin.java.JavaPlugin;

public final class PracticeServer extends JavaPlugin {

  @Override
  public void onEnable() {
    // Plugin startup logic
    getLogger().info("Practice Server Plugin Enabled");
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
    getLogger().info("Practice Server Plugin Disabled");
  }

}
