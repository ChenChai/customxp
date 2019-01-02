package ai.chench.customxp.commands;

import ai.chench.customxp.SoulPoints;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetSoulPoints implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;

        if (args.length < 2) return false;

        // no player name specified, default to the sender
        if (sender instanceof Player) {
            player = (Player) sender;
        }

        if (args.length >= 3) {
            player = Bukkit.getPlayer(args[2]);
        }

        if (!(sender instanceof  Player) && args.length == 2) {
            sender.sendMessage("Only players can have Soul Points. You are not a player.");
            return false;
        }

        if (player == null) return false;
        int numPoints;
        try{
            numPoints = Integer.parseInt(args[1]);
        } catch (NumberFormatException e){
            sender.sendMessage("Soul points must be an integer!");
            return false;
        }

        if (args[0].equals("add")) {
            SoulPoints.addPoints(player, numPoints);
            sender.sendMessage("Added " + numPoints + " Soul Points, for a total of " + SoulPoints.getPoints(player) + " Soul Points!");
            return true;
        }

        if (args[0].equals("set")) {
            SoulPoints.setPoints(player, numPoints);
            sender.sendMessage("Set to " + numPoints + " Soul Points!");
            return true;
        }

        // invalid arguments
        return false;
    }
}
