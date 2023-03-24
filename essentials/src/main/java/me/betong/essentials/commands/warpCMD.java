package me.betong.essentials.commands;

import me.betong.essentials.App;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.io.File;
import java.util.*;

public class warpCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public warpCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("warp").setExecutor(this);
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
		File file = new File(plugin.getDataFolder(), fileName);

		String warp_name = args[0].toLowerCase();

		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

		List<String> warps = config.getStringList("warps");

		//Check if warp exists
		boolean warp_exists = false;
		for (String warp : warps) {
			if (warp_name.equalsIgnoreCase(warp)) {
				warp_exists = true;
			}
		}

		if (!warp_exists) {
			p.sendMessage(ChatColor.RED + "That warp is not available! do /warps for all available warps!");
			return true;
		}
		
        //World world = p.getLocation().getWorld();
        String prefix = warp_name;
        String warp_world_name = config.getString(prefix + ".world");

        World world = Bukkit.getWorld(warp_world_name);

        double world_X = config.getDouble(prefix + ".x");
        double world_Y = config.getDouble(prefix + ".y");
        double world_Z = config.getDouble(prefix + ".z");

        double world_yaw = config.getDouble(prefix + ".yaw");
        double world_pitch = config.getDouble(prefix + ".pitch");

		float warp_yaw = (float) world_yaw;
		float warp_pitch = (float) world_pitch;
	
        Location warp_location = new Location(world, world_X, world_Y, world_Z, warp_yaw, warp_pitch);

        p.sendMessage(ChatColor.GOLD + "Teleporting to " + ChatColor.RED + warp_name);
        p.teleport(warp_location);
        
		return true;
	}
}
