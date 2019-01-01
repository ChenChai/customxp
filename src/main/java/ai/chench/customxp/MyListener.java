package ai.chench.customxp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class MyListener implements Listener {

    private final Plugin plugin;
    MyListener(Plugin plugin) {
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
}
