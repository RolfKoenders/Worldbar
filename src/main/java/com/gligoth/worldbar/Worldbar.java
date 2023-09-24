package com.gligoth.worldbar;

import com.gligoth.worldbar.commands.WorldbarCommands;
import com.gligoth.worldbar.listeners.PlayerChangedWorldListener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Worldbar extends JavaPlugin {

    private WorldbarConfiguration worldbarConfig;

    @Override
    public void onEnable() {
        this.worldbarConfig = WorldbarConfiguration.getInstance();
        this.worldbarConfig.init(this);
        this.worldbarConfig.saveConfig();

        this.getCommand("worldbar").setExecutor(new WorldbarCommands(this));

        WorldbarManager worldbarManager = WorldbarManager.getInstance();
        worldbarManager.setConfigurationManager(this.worldbarConfig);
        worldbarManager.init();

        getServer().getPluginManager().registerEvents(new PlayerChangedWorldListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
