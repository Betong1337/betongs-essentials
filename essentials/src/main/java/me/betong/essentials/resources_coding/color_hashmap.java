package me.betong.essentials.resources_coding;

import java.util.HashMap;

public class color_hashmap {
    public HashMap<String, String> getColorMap() {
        HashMap<String, String> color_map = new HashMap<>();

        color_map.put("&0", "BLACK");
        color_map.put("&1", "DARK_BLUE");
        color_map.put("&2", "DARK_GREEN");
        color_map.put("&3", "DARK_AQUA");
        color_map.put("&4", "DARK_RED");
        color_map.put("&5", "DARK_PURPLE");
        color_map.put("&6", "GOLD");
        color_map.put("&7", "GRAY");
        color_map.put("&8", "DARK_GRAY");
        color_map.put("&9", "BLUE");
        color_map.put("&a", "GREEN");
        color_map.put("&c", "RED");
        color_map.put("&d", "LIGHT_PURPLE");
        color_map.put("&e", "YELLOW");
        color_map.put("&f", "WHITE");
        color_map.put("&l", "BOLD");
        color_map.put("&m", "STRIKETHROUGH");
        color_map.put("&n", "UNDERLINE");
        color_map.put("&o", "ITALIC");
        color_map.put("&r", "RESET");

        return color_map;
    }
}