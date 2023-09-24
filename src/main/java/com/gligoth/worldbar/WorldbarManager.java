package com.gligoth.worldbar;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bukkit.Bukkit.getLogger;

public class WorldbarManager {
    private static WorldbarManager INSTANCE;
    private final Map<String, BossBar> bossBarMap = new HashMap<>();
    private final List<Player> playersWithWorldbar = new ArrayList<>();
    private WorldbarConfiguration worldbarConfiguration;

    private WorldbarManager() {}

    public void init() {
        List<World> worlds = Bukkit.getWorlds();
        for (World world : worlds) {
            getLogger().info("[Worldbar] Initializing worldbar for world: " + world.getName());
            String bossBarTitle = this.buildWorldbarTitle(world);
            BarColor barColor = this.getBarColor(world);
            BossBar bossBar = Bukkit.createBossBar(bossBarTitle, barColor, BarStyle.SOLID);
            bossBar.setProgress(1);
            bossBar.setVisible(true);
            this.bossBarMap.put(world.getName(), bossBar);
        }
    }

    public void reloadWorldbarConfiguration() {
        for (World world : Bukkit.getWorlds()) {
            BossBar bossBar = this.bossBarMap.get(world.getName());
            bossBar.setTitle(this.buildWorldbarTitle(world));
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

    public void playerSwitchedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        World fromWorld = event.getFrom();
        World toWorld = player.getWorld();

        BossBar fromBar = this.bossBarMap.get(fromWorld.getName());
        if (fromBar.getPlayers().contains(player)) {
            fromBar.removePlayer(player);
        }

        BossBar toBar = this.bossBarMap.get(toWorld.getName());
        toBar.addPlayer(player);
    }

    public boolean doesPlayerHaveWorldbar(Player player) {
        return this.playersWithWorldbar.contains(player);
    }

    private BarColor getBarColor(World world) {
        String barColorConfigPath = String.format("worlds.%s.color", world.getName());
        String configBarColor = this.worldbarConfiguration.getConfig().getString(barColorConfigPath);

        if (configBarColor == null) {
            return BarColor.PINK;
        } else {
            return BarColor.valueOf(configBarColor);
        }
    }

    private String buildWorldbarTitle(World world) {
        String worldTitleConfigPath = String.format("worlds.%s.title", world.getName());
        String configWorldTitle = this.worldbarConfiguration.getConfig().getString(worldTitleConfigPath);

        String worldTitle;
        if (configWorldTitle == null) {
            worldTitle = world.getName();
        } else {
            worldTitle = configWorldTitle;
        }

        String configTitlePrefix = this.worldbarConfiguration.getConfig().getString("titlePrefix");
        String titlePrefix;

        if (configTitlePrefix == null) {
            titlePrefix = "You are in: ";
        } else {
            titlePrefix = configTitlePrefix;
        }

        return String.format("%s %s", titlePrefix, worldTitle);
    }

    private void addPlayerToBar(Player player) {
        if (!this.doesPlayerHaveWorldbar(player)) {
            World world = player.getWorld();
            BossBar bossBar = this.bossBarMap.get(world.getName());
            bossBar.addPlayer(player);
            this.playersWithWorldbar.add(player);
        }
    }

    private void removePlayerFromBar(Player player) {
        if (this.doesPlayerHaveWorldbar(player)) {
            World world = player.getWorld();
            BossBar bossBar = this.bossBarMap.get(world.getName());
            bossBar.removePlayer(player);
            this.playersWithWorldbar.remove(player);
        }
    }

    public static WorldbarManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WorldbarManager();
        }
        return INSTANCE;
    }
}
