package me.betong.essentials.resources_coding;
import java.util.HashMap;
import net.md_5.bungee.api.ChatColor;

public class global_functions {
    public static String[] textColor(String msg) {
        HashMap<String, String> color_map = new color_hashmap().getColorMap();

        String color_msg = "";
        char prev_char = 0;
        String IsPlayerUsingColors = "false";

        for (int i = 0; i < msg.length(); i++) {
            char curr_char = msg.charAt(i);
            String pair = String.valueOf(prev_char) + curr_char;
            if (color_map.containsKey(pair)) {
               IsPlayerUsingColors = "true";
               String color = color_map.get(pair);
               color_msg = color_msg + ChatColor.of(color);
               prev_char = 0;
            } else {
               color_msg = color_msg + curr_char;
               prev_char = curr_char;
            }
         }
        if (IsPlayerUsingColors.equalsIgnoreCase("true")) {
            color_msg = color_msg.replaceAll("&", "");
            msg = color_msg;
        }
        String[] result = {msg, IsPlayerUsingColors};
        return result;
    }
}
