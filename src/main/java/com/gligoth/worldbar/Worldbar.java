package com.gligoth.worldbar;

import com.gligoth.worldbar.commands.WorldbarCommands;
import com.gligoth.worldbar.commands.WorldnameCommand;
import com.gligoth.worldbar.listeners.PlayerChangedWorldListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Worldbar extends JavaPlugin {

    private WorldbarConfiguration worldbarConfig;

    @Override
    public void onEnable() {
        this.worldbarConfig = WorldbarConfiguration.getInstance();
        this.worldbarConfig.init(this);
        this.worldbarConfig.saveConfig();

        WorldbarManager worldbarManager = WorldbarManager.getInstance();
        worldbarManager.setConfigurationManager(this.worldbarConfig);
        worldbarManager.init();

        this.getCommand("worldbar").setExecutor(new WorldbarCommands(this));
        this.getCommand("worldname").setExecutor(new WorldnameCommand());

        getServer().getPluginManager().registerEvents(new PlayerChangedWorldListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
