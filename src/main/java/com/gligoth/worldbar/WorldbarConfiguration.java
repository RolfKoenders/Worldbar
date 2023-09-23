package com.gligoth.worldbar;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldbarConfiguration {

    private JavaPlugin plugin;
    private FileConfiguration config;

    private static WorldbarConfiguration INSTANCE;

    private WorldbarConfiguration() {}

    public void init(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.config.options().copyDefaults(true);
    }

    public void saveConfig() {
        plugin.saveConfig();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public static WorldbarConfiguration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WorldbarConfiguration();
        }
        return INSTANCE;
    }

}
