package ai.chench.customxp.commands;

import ai.chench.customxp.CustomXp;
import ai.chench.customxp.SoulPoints;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSoulPoints implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players have Soul Points. You are not a player.");
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(ChatColor.BLUE + "You have " + SoulPoints.getPoints(player) + " Soul Points!");
        player.sendMessage(ChatColor.DARK_BLUE + "Soul Points are gained when monsters are killed, and allow you to cast skills with /skill!");
        return true;
    }
}
