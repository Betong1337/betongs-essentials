package me.betong.essentials.commands;
import me.betong.essentials.App;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class flyCMD implements CommandExecutor {
	
    public boolean setPlayerFlight(Player p, boolean mode) {
        p.setAllowFlight(mode);
        p.setFlying(mode);
        String mode_msg = "Disabled";
        if (mode == true) {
            mode_msg = "Enabled";
        }
        String msg = ChatColor.GOLD + "Flying has been " + ChatColor.RED + mode_msg + ChatColor.GOLD + " on " + ChatColor.GREEN + p.getDisplayName();
        p.sendMessage(msg);
        return true;
    }

	@SuppressWarnings("unused")
	private App plugin;
	
	public flyCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("fly").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		String permission = ChatColor.RED + "You don't have permission execute this command!";
		
		if (!p.hasPermission("betong.essentials.fly.use")) {
			p.sendMessage(permission);
			return true;
		}

        if (args.length == 0) {
            if (p.getAllowFlight() != true) {
                setPlayerFlight(p, true);
                return true;
            }
            setPlayerFlight(p, false);
            return true;
        }

        if (!p.hasPermission("betong.essentials.fly.player.use")) {
            p.sendMessage(permission);
            return true;
        }

		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			p.sendMessage(ChatColor.RED + args[0] + ChatColor.WHITE + " is not online!");
			return true;
		}

        if (target.getAllowFlight() != true) {
            setPlayerFlight(target, true);
            target.sendMessage(ChatColor.GOLD + "Flying has been " + ChatColor.RED + "Enabled" + ChatColor.GOLD + " on " + ChatColor.GREEN + target.getDisplayName());
            return true;
        }
        setPlayerFlight(target, false);
        target.sendMessage(ChatColor.GOLD + "Flying has been " + ChatColor.RED + "Disabled" + ChatColor.GOLD + " on " + ChatColor.GREEN + target.getDisplayName());
		return true;
	}
}