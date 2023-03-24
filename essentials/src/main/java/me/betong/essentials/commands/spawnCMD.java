package me.betong.essentials.commands;

import me.betong.essentials.App;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class spawnCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public spawnCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("spawn").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		String permission = ChatColor.RED + "You don't have permission execute this command!";
		
		if (!p.hasPermission("betong.essentials.spawn.use")) {
			p.sendMessage(permission);
			return true;
		}

        String world_name = p.getLocation().getWorld().getName();
        World world = p.getLocation().getWorld();
        String prefix = "spawns." + world_name;
        double world_X = plugin.getConfig().getDouble(prefix + ".x");
        double world_Y = plugin.getConfig().getDouble(prefix + ".y");
        double world_Z = plugin.getConfig().getDouble(prefix + ".z");
        //double world_yaw = plugin.getConfig().getDouble(prefix + ".yaw");
        //double world_pitch = plugin.getConfig().getDouble(prefix + ".pitch");
        Location spawn_location = new Location(world, world_X, world_Y, world_Z);

        p.sendMessage(ChatColor.GOLD + "Teleporting to " + ChatColor.RED + world_name + ChatColor.GOLD + " spawn");
        p.teleport(spawn_location);
        
		return true;
	}
}
