package me.betong.essentials.commands;
import me.betong.essentials.App;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class feedCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public feedCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("feed").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		String permission = ChatColor.RED + "You don't have permission execute this command!";
		
		if (!p.hasPermission("betong.essentials.feed.use")) {
			p.sendMessage(permission);
			return true;
		}

        if (args.length == 0) {
            p.setFoodLevel(20);
            p.sendMessage(ChatColor.GOLD + "You have been fed!");
            return true;
        }

        if (!p.hasPermission("betong.essentials.feed.player.use")) {
            p.sendMessage(permission);
            return true;
        }

		Player target = Bukkit.getPlayer(args[0]);

		if (target == null) {
			p.sendMessage(ChatColor.RED + args[0] + ChatColor.WHITE + " is not online!");
			return true;
		}

        if (p.getName() == target.getName()) {
            p.setFoodLevel(20);
            p.sendMessage(ChatColor.GOLD + "You have been fed!");
            return true;
        }

        target.setFoodLevel(20);;
        target.sendMessage(ChatColor.GOLD + "You have been fed!");
        p.sendMessage(ChatColor.GREEN + p.getDisplayName()  + ChatColor.GOLD + " Has been fed!");
		return true;
	}
}