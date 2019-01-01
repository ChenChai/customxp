package ai.chench.customxp;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SoulPoints {

    // returns the amount of soul points a player has
    public static int getPoints(Player player, Plugin plugin) {
        int num;
        try {
            num = (Integer) plugin.getConfig().get("player." + player.getUniqueId().toString() + ".sp");
            return num;
        } catch (NullPointerException e) {
            plugin.getLogger().severe("Tried to find number of sp for a player with null sp!");
            e.printStackTrace();
        } catch (ClassCastException e) {
            plugin.getLogger().severe("Tried to read number of sp for a player with invalid type sp in config!");
            e.printStackTrace();
        }
        return -1;
    }

    // sets the number of soul points a player has to an integer value
    public static void setPoints(Player player, Plugin plugin, int points) {
        plugin.getConfig().set("player." + player.getUniqueId().toString() + ".sp", points);
        plugin.saveConfig();
    }

    // adds a number of points to the player's soul point total
    public static void addPoints(Player player, Plugin plugin, int points) {
        setPoints(player, plugin, points + getPoints(player, plugin));
    }
}
