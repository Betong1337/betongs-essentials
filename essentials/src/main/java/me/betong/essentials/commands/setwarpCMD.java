package me.betong.essentials.commands;

import me.betong.essentials.App;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.io.File;
import java.util.*;
import java.io.IOException;

public class setwarpCMD implements CommandExecutor {
	@SuppressWarnings("unused")
	private App plugin;
	
	public setwarpCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("setwarp").setExecutor(this);
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
        String fileName = "warps.yml";

        String warp_name = args[0].toLowerCase();

        File file = new File(plugin.getDataFolder(), fileName);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        //Check if warp exists
        List<String> warps = config.getStringList("warps");
		boolean warp_exists = false;
		for (String warp : warps) {
			if (warp_name.equalsIgnoreCase(warp)) {
				warp_exists = true;
			}
		}

		if (warp_exists) {
			p.sendMessage(ChatColor.RED + "That warp already exists!");
			return true;
		}

        String world_name = p.getLocation().getWorld().getName();
        double world_X = p.getLocation().getX();
        double world_Y = p.getLocation().getY();
        double world_Z = p.getLocation().getZ();
        float world_yaw = p.getLocation().getYaw();
        float world_pitch = p.getLocation().getPitch();
        String owner_uuid = p.getUniqueId().toString();

        config.set(warp_name + ".world", world_name);
        config.set(warp_name + ".x", world_X);
        config.set(warp_name + ".y", world_Y);
        config.set(warp_name + ".z", world_Z);
        config.set(warp_name + ".yaw", world_yaw);
        config.set(warp_name + ".pitch", world_pitch);
        config.set(warp_name + ".owner", owner_uuid);

        warps.add(warp_name);
        config.set("warps", warps);

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        p.sendMessage(ChatColor.GOLD + "Warp " + ChatColor.RED + warp_name + ChatColor.GOLD + " has been set!");

		return true;
	}
}