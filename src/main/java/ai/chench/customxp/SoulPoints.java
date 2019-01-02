package ai.chench.customxp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SoulPoints {

    // returns the amount of soul points a player has
    public static int getPoints(Player player) {
        int num;

        Plugin plugin = CustomXp.getInstance();
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

    // returns what level a player is based on their soul points.
    public static int getLevel(int points) {
        int level = 1;
        Plugin plugin = CustomXp.getInstance();
        ArrayList<Integer> levelList = (ArrayList<Integer>) plugin.getConfig().getIntegerList("level");
        
        // TODO: convert to binary search?
        while(level < levelList.size() && levelList.get(level) <= points) level++;

        return level;
    }

    // sets the number of soul points a player has to an integer value
    public static void setPoints(Player player, int points) {
        Plugin plugin = CustomXp.getInstance();
        plugin.getConfig().set("player." + player.getUniqueId().toString() + ".sp", points);
        plugin.saveConfig();
    }

    // adds a number of points to the player's soul point total
    public static void addPoints(Player player, int points) {
        setPoints(player, points + getPoints(player));
    }
}
