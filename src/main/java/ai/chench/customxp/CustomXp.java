package ai.chench.customxp;

import ai.chench.customxp.commands.CommandCustomXp;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomXp extends JavaPlugin {

    private static CustomXp singletonInstance = null;

    @Override
    public void onEnable() {
        singletonInstance = this;

        this.saveDefaultConfig(); // will not overwrite an existing config file.
        getServer().getPluginManager().registerEvents(new MyListener(this), this);

        this.getCommand("customxp").setExecutor(new CommandCustomXp());

    }
    @Override
    public void onDisable() {

    }

    public static CustomXp getInstance() {
        return singletonInstance;
    }
}
