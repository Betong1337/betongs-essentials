package me.betong.essentials.commands;
import me.betong.essentials.App;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class gmCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	private App plugin;
	
	public gmCMD(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("gm").setExecutor(this);
	}
    public String intToString(String gamemode) {
        if ("0".equalsIgnoreCase(gamemode) || gamemode.equalsIgnoreCase("SURVIVAL")) {
            gamemode = "SURVIVAL";
        } else if ("1".equalsIgnoreCase(gamemode) || gamemode.equalsIgnoreCase("CREATIVE")) {
            gamemode = "CREATIVE";
        } else if ("2".equalsIgnoreCase(gamemode) || gamemode.equalsIgnoreCase("ADVENTURE")) {
            gamemode = "ADVENTURE";
        } else if ("3".equalsIgnoreCase(gamemode) || gamemode.equalsIgnoreCase("SPECTATOR")) {
            gamemode = "SPECTATOR";
        } else {
            gamemode = "ERROR";
        }
        return gamemode;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		String permission = ChatColor.RED + "You don't have permission execute this command!";
		
		if (!p.hasPermission(cmd.getPermission())) {
			p.sendMessage(permission);
			return true;
		}

        if (args.length == 0) {
            p.sendMessage(cmd.getUsage());
            return true;
        }

        if (args.length < 2) {
            String gamemode = intToString(args[0]);
            if (gamemode.equalsIgnoreCase("ERROR")) {
                p.sendMessage(cmd.getUsage());
                return true;
            }
            p.setGameMode(GameMode.valueOf(gamemode));
            p.sendMessage(ChatColor.GOLD + "Gamemode has been set to " + ChatColor.RED + gamemode);
            return true;
        }

        if (args.length != 2) {
            p.sendMessage(cmd.getUsage());
            return true;
        }

        if (!p.hasPermission("betong.essentials.gamemode.player.use")) {
            p.sendMessage(permission);
            return true;
        }

		Player target = Bukkit.getPlayer(args[1]);
        String gamemode = intToString(args[0]);

		if (target == null) {
			p.sendMessage(ChatColor.RED + args[1] + ChatColor.WHITE + " is not online!");
			return true;
		}

        if (p.getName() == target.getName()) {
            if (gamemode.equalsIgnoreCase("ERROR")) {
                p.sendMessage(cmd.getUsage());
                return true;
            }
            p.setGameMode(GameMode.valueOf(gamemode));
            p.sendMessage(ChatColor.GOLD + "Gamemode has been set to " + ChatColor.RED + gamemode);
            return true;
        }

        target.setGameMode(GameMode.valueOf(gamemode));
        target.sendMessage(ChatColor.GOLD + "Gamemode has been set to " + ChatColor.RED + gamemode);
        p.sendMessage(ChatColor.GREEN + p.getDisplayName()  + ChatColor.GOLD + "'s gamemode has been set to " + ChatColor.RED + gamemode);
		return true;
	}
}