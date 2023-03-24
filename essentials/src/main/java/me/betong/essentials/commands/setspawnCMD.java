package me.betong.essentials.commands;

import me.betong.essentials.App;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class setspawnCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public setspawnCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("setspawn").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		String permission = ChatColor.RED + "You don't have permission execute this command!";
		
		if (!p.hasPermission("betong.essentials.setspawn.use")) {
			p.sendMessage(permission);
			return true;
		}

        String world_name = p.getLocation().getWorld().getName();
        double world_X = p.getLocation().getX();
        double world_Y = p.getLocation().getY();
        double world_Z = p.getLocation().getZ();
        float world_yaw = p.getLocation().getYaw();
        float world_pitch = p.getLocation().getPitch();

        plugin.getConfig().set("spawns." + world_name + ".x", world_X);
        plugin.getConfig().set("spawns." + world_name + ".y", world_Y);
        plugin.getConfig().set("spawns." + world_name + ".z", world_Z);
        plugin.getConfig().set("spawns." + world_name + ".yaw", world_yaw);
        plugin.getConfig().set("spawns." + world_name + ".pitch", world_pitch);
        plugin.saveConfig();
        World world = p.getLocation().getWorld();
        world.setSpawnLocation(p.getLocation());
        p.sendMessage(ChatColor.GOLD + "Spawn for world " + ChatColor.RED + world_name + ChatColor.GOLD + " has been set!");

		return true;
	}
}
//Location{world=CraftWorld{name=redstone},x=-226.4725109032863,y=-40.0,z=-187.60060013948495,pitch=13.200708,yaw=141.66856}