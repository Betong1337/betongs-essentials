package me.betong.essentials.events;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.Player;
import me.betong.essentials.resources_coding.global_functions;

public class onPlayerChatEV implements Listener {
    @EventHandler
     public void ChatColors(AsyncPlayerChatEvent event) {
         Player player = event.getPlayer();
         String msg = event.getMessage();

         if (!player.hasPermission("betong.essentials.chatcolor")) {
            return;
         }

         String msg_result[] = global_functions.textColor(msg);
         String color_msg = msg_result[0];
         String IsPlayerUsingColors = msg_result[1];

         if (IsPlayerUsingColors.equalsIgnoreCase("true")) {
            msg = color_msg;
         }

         ChatColor username_color = ChatColor.LIGHT_PURPLE;
        
         event.setFormat(username_color + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.WHITE + msg);
     }
}
