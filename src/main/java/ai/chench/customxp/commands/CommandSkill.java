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
import org.bukkit.util.RayTraceResult;

public class CommandSkill implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) return false;
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can use skills. You are not a player.");
            return true;
        }

        Player player = (Player) sender;
        Plugin plugin = CustomXp.getInstance();

        if(args[0].equals("haste")) {
            if(attemptCast(player, "haste")){
                int duration = plugin.getConfig().getInt("skills.haste.duration");
                int amplifier = plugin.getConfig().getInt("skills.haste.amplifier");
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, amplifier));
                return true;
            } else return false;
        }

        if(args[0].equals("burst")) {
            if(attemptCast(player, "burst")){
                double power = plugin.getConfig().getDouble("skills.burst.power");
                double range = plugin.getConfig().getDouble("skills.burst.range");
                boolean setFire = plugin.getConfig().getBoolean("skills.burst.setfire");

                RayTraceResult rayTraceResult = player.rayTraceBlocks(range);
                if (rayTraceResult == null) {
                    player.sendMessage(ChatColor.YELLOW + "Out of range; SP refunded.");
                    SoulPoints.addPoints(player, plugin.getConfig().getInt("skills.burst.cost"));
                    return true;
                }
                player.getWorld().createExplosion(rayTraceResult.getHitPosition().toLocation(player.getWorld()), (float) power, setFire);

                return true;
            } else return false;
        }

        return false;
    }

    // Attempt to remove soul points to cast a skill.
    // Returns true if soul points were deducted, false if insufficient soul points.
    private boolean attemptCast(Player player, String skill) {
        Plugin plugin = CustomXp.getInstance();

        int cost = plugin.getConfig().getInt("skills." + skill + ".cost");

        if (SoulPoints.getPoints(player) < cost) {
            player.sendMessage(ChatColor.YELLOW + "Insufficent Soul Points to cast " + skill + "! "
                    + cost + " needed.");
            return false;
        } else {
            SoulPoints.addPoints(player, -1 * cost);
            player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Consumed "
                    + cost + " Soul Points to cast " + skill + "!");
            return true;
        }
    }

}
