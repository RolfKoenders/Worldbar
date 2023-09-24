package com.gligoth.worldbar.listeners;

import com.gligoth.worldbar.WorldbarManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangedWorldListener implements Listener {

    private final WorldbarManager worldbarManager = WorldbarManager.getInstance();

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        if (this.worldbarManager.doesPlayerHaveWorldbar(event.getPlayer())) {
            this.worldbarManager.playerSwitchedWorld(event);
        }
    }

}
