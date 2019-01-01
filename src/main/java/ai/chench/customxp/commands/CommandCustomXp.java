package ai.chench.customxp.commands;

import ai.chench.customxp.CustomXp;
import ai.chench.customxp.SoulPoints;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCustomXp implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /*
        sender.sendMessage("command: " + command.toString());
        sender.sendMessage("label: " + label);
        sender.sendMessage("args:");
        for(String s: args){
            sender.sendMessage(s);
        }
        */
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players have Soul Points. You are not a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0 || args[0].equals("view")) {
            player.sendMessage("You have " + SoulPoints.getPoints(player) + " Soul Points!");
            return true;
        }

        if (args.length >= 2) {
            int numPoints;
            try{
                numPoints = Integer.parseInt(args[1]);
            } catch (NumberFormatException e){
                player.sendMessage("Soul points must be an integer!");
                return false;
            }


            if (args[0].equals("add")) {
                    SoulPoints.addPoints(player, numPoints);
                    player.sendMessage("Added " + numPoints + " Soul Points!");
                    return true;
            }

            if (args[0].equals("set")) {
                SoulPoints.setPoints(player, numPoints);
                player.sendMessage("Set to " + numPoints + " Soul Points!");
                return true;
            }

        }

        return false;
    }
}
