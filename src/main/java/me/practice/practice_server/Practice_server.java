package me.practice.practice_server;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Practice_server extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("The practice server plugin is on!");
        getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("The practice server plugin is off!");
    }

    @EventHandler
    public void noFallDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player && event.getCause().name().equalsIgnoreCase("fall")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void noHunger(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

}
