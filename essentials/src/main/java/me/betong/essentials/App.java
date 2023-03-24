package me.betong.essentials;
import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

import me.betong.essentials.commands.*;
import me.betong.essentials.events.*;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;

public class App extends JavaPlugin {

    private File customConfigFile;

	@Override
    public void onEnable() {
        onPlayerJoinEV playerJoinListener = new onPlayerJoinEV(this);
        getServer().getPluginManager().registerEvents(playerJoinListener, this);
        getServer().getPluginManager().registerEvents(new onPlayerChatEV(), this);
        getServer().getPluginManager().registerEvents(new onSignEV(), this);

        saveDefaultConfig();
        createConfig("homes.yml");
        createConfig("warps.yml");
        createTxt("motd.txt", "Welcome to the server!");
        new broadcastCMD(this);
        new flyCMD(this);
        new healCMD(this);
        new feedCMD(this);
        new gmCMD(this);
        new tpCMD(this);
        new invseeCMD(this);
        new spawnCMD(this);
        new setspawnCMD(this);
        new setwarpCMD(this);
        new warpCMD(this);
        new warpsCMD(this);
        new delwarpCMD(this);
        new sethomeCMD(this);
        new homeCMD(this);
        new homesCMD(this);
        new delhomeCMD(this);
    }
    private void createConfig(String filename) {
        customConfigFile = new File(getDataFolder(), filename);
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource(filename, false);
        }
        YamlConfiguration.loadConfiguration(customConfigFile);
    }

    private void createTxt(String filename, String defaultText) {
        customConfigFile = new File(getDataFolder(), filename);
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource(filename, false);
        }
    
        // Check if the file has any text
        if (customConfigFile.length() == 0) {
            try (FileWriter writer = new FileWriter(customConfigFile)) {
                writer.write(defaultText);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    }

}
