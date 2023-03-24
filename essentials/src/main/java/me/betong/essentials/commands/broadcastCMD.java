package me.betong.essentials.commands;

import me.betong.essentials.App;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public class broadcastCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public broadcastCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("broadcast").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		String permission = ChatColor.RED + "You don't have permission execute this command!";
        FileConfiguration config = plugin.getConfig();
		
		if (!p.hasPermission("betong.essentials.broadcast.use")) {
			p.sendMessage(permission);
			return true;
		}

        if (args.length < 1) {
            p.sendMessage(ChatColor.RED + "Usage: /broadcast <message>");
            return true;
        }

        String message = "";
        ChatColor prefix_color = ChatColor.GOLD;
        ChatColor prefix_brackets_color = ChatColor.DARK_RED;
        String prefix = "";

        try {
            prefix_color = ChatColor.valueOf(config.getString("prefix_color").toUpperCase());
            prefix_brackets_color = ChatColor.valueOf(config.getString("prefix_brackets_color"));
            prefix = config.getString("prefix");
        } catch (Exception e) {
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Something is wrong with the config! Logging the full error in console!");
            plugin.getLogger().info("ESSENTIALS CONFIG ERROR: broadcast: prefix_color, prefix_brackets_color or prefix variables in config.yml is wrong!");
        }
        for (int index=0; index < args.length; index++) {
            message = message + " " + args[index];
        }

        prefix = prefix_brackets_color + "[" + prefix_color + prefix + prefix_brackets_color + "]" + ChatColor.WHITE;
        String broadcast = prefix + message;
        Bukkit.broadcastMessage(broadcast);

		return true;
	}
}
