package me.betong.essentials.commands;

import me.betong.essentials.App;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class invseeCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public invseeCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("invsee").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		String permission = ChatColor.RED + "You don't have permission execute this command!";
		
		if (!p.hasPermission("betong.essentials.invsee.use")) {
			p.sendMessage(permission);
			return true;
		}

        if (args.length < 1) {
            p.sendMessage(ChatColor.RED + "Usage: /invsee <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        p.sendMessage(ChatColor.GOLD + "Opening " + ChatColor.GREEN + target.getDisplayName() + ChatColor.GOLD + "'s inventory");
        p.openInventory(target.getInventory());

		return true;
	}
}
