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

public class delhomeCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public delhomeCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("delhome").setExecutor(this);
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
        String home_name = args[0].toLowerCase();
		String fileName = "homes.yml";
		File file = new File(plugin.getDataFolder(), fileName);

		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        String getListFromConfig = p.getUniqueId().toString() + ".homes";
        List<String> home_list = config.getStringList(getListFromConfig);

		boolean home_exists = false;
		for (String warp : home_list) {
			if (home_name.equalsIgnoreCase(warp)) {
				home_exists = true;
			}
		}

		if (!home_exists) {
			p.sendMessage(ChatColor.RED + "That home don't exist!");
			return true;
		}
        home_list.remove(home_name);
        config.set(getListFromConfig, home_list);

        try {
            config.save(file);
            p.sendMessage(ChatColor.GOLD + "Home " + ChatColor.RED + home_name + ChatColor.GOLD + " has been deleted!");
        } catch (IOException e) {
            e.printStackTrace();
        }

		return true;
	}
}
