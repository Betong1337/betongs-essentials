package me.betong.essentials.commands;

import me.betong.essentials.App;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.io.File;
import java.util.*;

public class homesCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public homesCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("homes").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		
		if (!p.hasPermission(cmd.getPermission())) {
			p.sendMessage(cmd.getPermissionMessage());
			return true;
		}

        String fileName = "homes.yml";
		File file = new File(plugin.getDataFolder(), fileName);

        String player_uuid = p.getUniqueId().toString();
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

		List<String> player_homes = config.getStringList(player_uuid + ".homes");

		String homes = player_homes.toString();

		homes = homes.substring(1, homes.length() - 1);
		
        p.sendMessage(ChatColor.GOLD + "Homes: " + ChatColor.RED + homes);
        
		return true;
	}
}
