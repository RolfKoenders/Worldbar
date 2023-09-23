package com.gligoth.worldbar;

import com.gligoth.worldbar.commands.WorldbarCommands;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Worldbar extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("worldbar").setExecutor(new WorldbarCommands());

        WorldbarManager worldbarManager = WorldbarManager.getInstance();
        worldbarManager.init();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
