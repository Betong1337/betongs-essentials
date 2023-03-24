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

public class sethomeCMD implements CommandExecutor {
	@SuppressWarnings("unused")
	private App plugin;
	
	public sethomeCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("sethome").setExecutor(this);
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

        String home_name = "home";

        File file = new File(plugin.getDataFolder(), fileName);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        String player_uuid = p.getUniqueId().toString();
        List<String> player_homes = config.getStringList(player_uuid + ".homes");
        int playerhomelen = player_homes.size();
        boolean doPlayerOnlyHaveOneHome = false;
        int amountOfHomesUserAllowed = 3;
        boolean DoUserHaveUnlimited = false;

        if (p.hasPermission("betong.essentials.home.vip")) {
            amountOfHomesUserAllowed = plugin.getConfig().getInt("vip_homes");
        } else if (p.hasPermission("betong.essentials.home.vipplus")) {
            amountOfHomesUserAllowed = plugin.getConfig().getInt("vipplus_homes");
        } else if (p.hasPermission("betong.essentials.*") || p.hasPermission("betong.essentials.home.*") || p.isOp()) {
            DoUserHaveUnlimited = true;
        }

        if (args.length < 1) {

            if (playerhomelen == 0) {
                doPlayerOnlyHaveOneHome = true;
            } else {
                p.sendMessage(cmd.getUsage());
                return true;
            }
        }

        if (!doPlayerOnlyHaveOneHome) {
            home_name = args[0].toLowerCase();
        }

        if(player_homes.isEmpty()) {
            config.set(player_uuid + ".homes", "");
        }

        Boolean doHomeExist = false;
        int homes = 0;

        for (String home : player_homes) {
            homes++;
            if (home.equalsIgnoreCase(home_name)) {
                doHomeExist = true;
            }
        }

        if (homes > amountOfHomesUserAllowed - 1) {
            p.sendMessage(ChatColor.RED + "You already have maximum amount of homes! /delhome to delete homes!");
            return true;
        } else if (DoUserHaveUnlimited) {
            
        }

        if (doHomeExist) {
            p.sendMessage(ChatColor.RED + "That home already exists!");
            return true;
        }

        player_homes.add(home_name);

        String world_name = p.getLocation().getWorld().getName();
        double world_X = p.getLocation().getX();
        double world_Y = p.getLocation().getY();
        double world_Z = p.getLocation().getZ();
        float world_yaw = p.getLocation().getYaw();
        float world_pitch = p.getLocation().getPitch();

        config.set(player_uuid + "." + home_name +".world", world_name);
        config.set(player_uuid + "." + home_name + ".x", world_X);
        config.set(player_uuid + "." + home_name + ".y", world_Y);
        config.set(player_uuid + "." + home_name +".z", world_Z);
        config.set(player_uuid + "." + home_name + ".yaw", world_yaw);
        config.set(player_uuid + "." + home_name + ".pitch", world_pitch);
        config.set(player_uuid + "." + "homes", player_homes);

        try {
            config.save(file);
            p.sendMessage(ChatColor.GOLD + "Home " + ChatColor.RED + home_name + ChatColor.GOLD + " has been set!");
        } catch (IOException e) {
            e.printStackTrace();
        }

		return true;
	}
}