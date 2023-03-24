package me.betong.essentials.events;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import net.md_5.bungee.api.ChatColor;
import java.io.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.entity.Player;
import me.betong.essentials.App;

public class onPlayerJoinEV implements Listener {
   private static App main;
   public onPlayerJoinEV(App main) {
      this.main = main;
  }
    @EventHandler
     public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String fileName = "motd.txt";
        File motdFile = new File(main.getDataFolder(), fileName);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(motdFile));
            String line = reader.readLine();
            while (line != null) {
               player.sendMessage(ChatColor.GOLD + line);
               line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
     }

}