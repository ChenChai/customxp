package ai.chench.customxp.listeners;

import ai.chench.customxp.SoulPoints;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.awt.*;

public class MyListener implements Listener {

    private final Plugin plugin;
    public MyListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.reloadConfig(); // make sure the config is up to date

        String uniqueId = player.getUniqueId().toString();

        // if the player is joining for the first time, assign 0 sp.
        if(plugin.getConfig().get("player." + uniqueId) == null){
            SoulPoints.setPoints(player, 0);
            Bukkit.broadcastMessage("New player " + player.getDisplayName() + " joined, set to " + SoulPoints.getPoints(player) + " Soul Points (SP)");
        } else {
            Bukkit.broadcastMessage("Player " + player.getDisplayName() + " joined, with " + SoulPoints.getPoints(player) + " SP!");
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();

        // give player soul points
        if (livingEntity.getKiller() != null) {
            Player player = livingEntity.getKiller();

            if(livingEntity.hasMetadata("sp")) {
                int points = livingEntity.getMetadata("sp").get(0).asInt();
                SoulPoints.addPoints(player, points);
                // need to put color before italic for both styles to apply, for some reason
                player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "+" + points + " Soul Points [" + livingEntity.getName() + "]");
            }
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity){
            entity.setMetadata("sp", new FixedMetadataValue(plugin, 5));
        }
    }

}
