package ai.chench.customxp.commands;

import ai.chench.customxp.CustomXp;
import ai.chench.customxp.SoulPoints;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandSkill implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) return false;
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can use skills. You are not a player.");
        }

        Player player = (Player) sender;
        Plugin plugin = CustomXp.getInstance();

        if(args[0].equals("haste")) {

            int cost = plugin.getConfig().getInt("skills.haste.cost");
            int duration = plugin.getConfig().getInt("skills.haste.duration");
            int amplifier = plugin.getConfig().getInt("skills.haste.amplifier");
            if (SoulPoints.getPoints(player) < cost) {
                player.sendMessage(ChatColor.YELLOW + "Insufficent Soul Points to cast Haste! " + cost + " needed.");
            } else {
                SoulPoints.addPoints(player, -1 * cost);
                player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Consumed " + cost + " Soul Points to cast Haste!");
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, amplifier));
            }
            return true;
        }
        return false;
    }
}
