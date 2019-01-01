package ai.chench.customxp;

import org.bukkit.plugin.java.JavaPlugin;

public class CustomXp extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig(); // will not overwrite an existing config file.
        getServer().getPluginManager().registerEvents(new MyListener(this), this);

        // delete old monsters since their metadata is destroyed.

    }
    @Override
    public void onDisable() {

    }
}
