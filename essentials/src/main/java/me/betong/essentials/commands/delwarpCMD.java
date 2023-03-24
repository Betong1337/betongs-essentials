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
import java.io.IOException;

public class delwarpCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public delwarpCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("delwarp").setExecutor(this);
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
        if (args.length < 1) {
            p.sendMessage(cmd.getUsage());
            return true;
        }
        String warp_name = args[0].toLowerCase();
		String fileName = "warps.yml";
		File file = new File(plugin.getDataFolder(), fileName);

		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        List<String> warp_list= config.getStringList("warps");

        //Check if warp exists
		boolean warp_exists = false;
		for (String warp : warp_list) {
			if (warp_name.equalsIgnoreCase(warp)) {
				warp_exists = true;
			}
		}

		if (!warp_exists) {
			p.sendMessage(ChatColor.RED + "That warp don't exist!");
			return true;
		}

        warp_list.remove(warp_name);
        config.set("warps", warp_list);

        try {
            config.save(file);
            p.sendMessage(ChatColor.GOLD + "Warp " + ChatColor.RED + warp_name + ChatColor.GOLD + " has been deleted!");
        } catch (IOException e) {
            e.printStackTrace();
        }

		return true;
	}
}
