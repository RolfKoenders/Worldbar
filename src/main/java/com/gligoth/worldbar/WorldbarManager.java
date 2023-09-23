package com.gligoth.worldbar;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldbarManager {
    private static WorldbarManager INSTANCE;
    private final Map<String, BossBar> bossBarMap = new HashMap<>();

    private WorldbarConfiguration worldbarConfiguration;

    private WorldbarManager() {}

    public void init() {
        List<World> worlds = Bukkit.getWorlds();
        for (World world : worlds) {
            System.out.println("Creating bossBar for world: " + world.getName());
            String bossBarTitle = this.getWorldTitle(world);
            BossBar bossBar = Bukkit.createBossBar(bossBarTitle, BarColor.PINK, BarStyle.SOLID);
            bossBar.setProgress(1);
            bossBar.setVisible(true);
            this.bossBarMap.put(world.getName(), bossBar);
        }
    }

    public void reloadWorldbarConfiguration() {
        for (World world : Bukkit.getWorlds()) {
            BossBar bossBar = this.bossBarMap.get(world.getName());
            bossBar.setTitle(this.getWorldTitle(world));
        }
    }

    public void setConfigurationManager(WorldbarConfiguration worldbarConfiguration) {
        this.worldbarConfiguration = worldbarConfiguration;
    }

    public void toggleBarForPlayer(Player player) {
        World world = player.getWorld();
        BossBar bossBar = bossBarMap.get(world.getName());

        if (bossBar.getPlayers().contains(player)) {
            removePlayerFromBar(player);
        } else {
            addPlayerToBar(player);
        }
    }

    private String getWorldTitle(World world) {
        String configPath = String.format("worlds.%s.title", world.getName());
        String configWorldTitle = this.worldbarConfiguration.getConfig().getString(configPath);

        String worldTitle;
        if (configWorldTitle == null) {
            worldTitle = world.getName();
        } else {
            worldTitle = configWorldTitle;
        }

        return "You are in: " + worldTitle;
    }

    private void addPlayerToBar(Player player) {
        World world = player.getWorld();
        BossBar bossBar = bossBarMap.get(world.getName());
        bossBar.addPlayer(player);
    }

    private void removePlayerFromBar(Player player) {
        World world = player.getWorld();
        BossBar bossBar = bossBarMap.get(world.getName());
        bossBar.removePlayer(player);
    }

    public static WorldbarManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WorldbarManager();
        }
        return INSTANCE;
    }
}
