package me.betong.essentials.events;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.EventHandler;
import net.md_5.bungee.api.ChatColor;
import java.util.*;
import me.betong.essentials.resources_coding.color_hashmap;
import java.util.HashMap;

public class onSignEV implements Listener {
    @EventHandler
    public void onPlayerJoin(SignChangeEvent event) {
        String[] signLines = event.getLines();
        List<String> color_sign_lines = new ArrayList<>();
        HashMap<String, String> color_map = new color_hashmap().getColorMap();

        boolean IsPlayerUsingColors = false;

        for (int i=0; i < 4;i++) {
            if (signLines[i].length() < 1) {
                color_sign_lines.add("");
            } else {
                char prev_char = 0;
                String sign_color_line = "";
                for (int j = 0; j < signLines[i].length(); j++) {
                    char curr_char = signLines[i].charAt(j);
                    String pair = String.valueOf(prev_char) + curr_char;
                    if (color_map.containsKey(pair)) {
                        IsPlayerUsingColors = true;
                        String color = color_map.get(pair);
                        sign_color_line = sign_color_line + ChatColor.of(color);
                        prev_char = 0;
                    } else {
                        sign_color_line = sign_color_line + curr_char;
                        prev_char = curr_char;
                    }
                }
                if (IsPlayerUsingColors) {
                    IsPlayerUsingColors = false;
                    sign_color_line = sign_color_line.replaceAll("&", "");
                    color_sign_lines.add(sign_color_line);
                }
            }
        }
        
        int c = 0;
        for (String sign_line : color_sign_lines) {
            event.setLine(c, sign_line);
            c++;
        }
    }
}
