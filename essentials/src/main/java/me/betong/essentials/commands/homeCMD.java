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

public class homeCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public homeCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("home").setExecutor(this);
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

		String home_name = "";
        String player_uuid = p.getUniqueId().toString();
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

		List<String> player_homes = config.getStringList(player_uuid + ".homes");
        boolean IsSingleHomeNameHome = false;

		if (args.length < 1) {
            int playerhomeslen = player_homes.size();
            if (playerhomeslen == 1 && player_homes.get(0).equalsIgnoreCase("home")) {
                IsSingleHomeNameHome = true;
                home_name = "home";
            } else {
                p.sendMessage(cmd.getUsage());
			    return true;
            }
		}

        if (!IsSingleHomeNameHome) {
            home_name = args[0].toLowerCase();
        }

		//Check if home exists
		boolean home_exists = false;
		for (String home : player_homes) {
			if (home_name.equalsIgnoreCase(home) || IsSingleHomeNameHome) {
				home_exists = true;
			}
		}

		if (!home_exists) {
			p.sendMessage(ChatColor.RED + "You do not have a home called " + ChatColor.DARK_RED + home_name);
			return true;
		}
		
        String prefix = player_uuid + "." + home_name;
        String warp_world_name = config.getString(prefix + ".world");

        World world = Bukkit.getWorld(warp_world_name);

        if (p.getWorld() != world) {
            p.sendMessage(ChatColor.DARK_RED + "You are in the wrong world!");
            return true;
        }

        double world_X = config.getDouble(prefix + ".x");
        double world_Y = config.getDouble(prefix + ".y");
        double world_Z = config.getDouble(prefix + ".z");

        double world_yaw = config.getDouble(prefix + ".yaw");
        double world_pitch = config.getDouble(prefix + ".pitch");

		float warp_yaw = (float) world_yaw;
		float warp_pitch = (float) world_pitch;
	
        Location home_location = new Location(world, world_X, world_Y, world_Z, warp_yaw, warp_pitch);

        p.sendMessage(ChatColor.GOLD + "Teleporting to " + ChatColor.RED + home_name);
        p.teleport(home_location);
        
		return true;
	}
}
