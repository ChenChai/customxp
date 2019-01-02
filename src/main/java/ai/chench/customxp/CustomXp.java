package ai.chench.customxp;

import ai.chench.customxp.commands.CommandSetSoulPoints;
import ai.chench.customxp.commands.CommandSoulPoints;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomXp extends JavaPlugin {

    private static CustomXp singletonInstance = null;

    @Override
    public void onEnable() {
        singletonInstance = this;

        this.saveDefaultConfig(); // will not overwrite an existing config file.
        getServer().getPluginManager().registerEvents(new ai.chench.customxp.listeners.MyListener(this), this);

        this.getCommand("soulpoints").setExecutor(new CommandSoulPoints());
        this.getCommand("setsoulpoints").setExecutor(new CommandSetSoulPoints());
        // remove all old monsters, as a server restart will have removed their metadata
        // so they will no longer give Soul Points.
        for (World world : Bukkit.getWorlds()) {
            for (LivingEntity livingEntity : world.getLivingEntities()) {
                if (livingEntity instanceof Monster) {
                    getLogger().info("Removing: " + livingEntity.getName());
                    livingEntity.remove();
                }
            }
        }
    }
    @Override
    public void onDisable() {

    }

    public static CustomXp getInstance() {
        return singletonInstance;
    }
}
