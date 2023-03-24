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

public class warpsCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public warpsCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("warps").setExecutor(this);
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

		String fileName = "warps.yml";
		File file = new File(plugin.getDataFolder(), fileName);

		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        List<String> warp_list= config.getStringList("warps");

		String warps = warp_list.toString();

		warps = warps.substring(1, warps.length() - 1);

        String warps_available = ChatColor.GOLD + "Warps: " + ChatColor.RED + warps;
        p.sendMessage(warps_available);
		return true;
	}
}
