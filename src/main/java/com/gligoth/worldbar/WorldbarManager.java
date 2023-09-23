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

    private WorldbarManager() {}

    public void init() {
        List<World> worlds = Bukkit.getWorlds();
        for (World world : worlds) {
            System.out.println("Creating bossBar for world: " + world.getName());
            String bossBarTitle ="You are in: " + world.getName();
            BossBar bossBar = Bukkit.createBossBar(bossBarTitle, BarColor.PINK, BarStyle.SOLID);
            bossBar.setProgress(1);
            bossBar.setVisible(true);
            this.bossBarMap.put(world.getName(), bossBar);
        }
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
