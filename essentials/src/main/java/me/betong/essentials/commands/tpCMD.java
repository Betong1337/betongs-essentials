package me.betong.essentials.commands;
import me.betong.essentials.App;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class tpCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public tpCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("tp").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		String permission = ChatColor.RED + "You don't have permission execute this command!";
		
		if (!p.hasPermission("betong.essentials.teleport.use")) {
			p.sendMessage(permission);
			return true;
		}

        if (args.length < 2) {
            p.sendMessage(ChatColor.RED + "Usage: /tp <player to teleport> < to player>");
            return true;
        }

		Player tp_to_player = Bukkit.getPlayer(args[1]);
        Player player_to_tp = Bukkit.getPlayer(args[0]);

		if (tp_to_player == null) {
			p.sendMessage(ChatColor.RED + args[1] + ChatColor.WHITE + " is not online!");
			return true;
		} else if (player_to_tp == null) {
            p.sendMessage(ChatColor.RED + args[0] + ChatColor.WHITE + " is not online!");
            return true;
        }

        player_to_tp.teleport(tp_to_player.getLocation());
        p.sendMessage(ChatColor.GOLD + "Teleported " + ChatColor.GREEN + player_to_tp.getDisplayName() + ChatColor.GOLD + " to " + ChatColor.GREEN + tp_to_player.getDisplayName());

		return true;
	}
}