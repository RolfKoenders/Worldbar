package com.gligoth.worldbar.commands;

import com.gligoth.worldbar.Permissions;
import com.gligoth.worldbar.WorldbarManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldnameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(sender.hasPermission(Permissions.WORLD_NAME)) {
            WorldbarManager worldbarManager = WorldbarManager.getInstance();
            String worldName = worldbarManager.getWorldbarTitle(player.getWorld());
            player.sendMessage("You are currently in the: " + ChatColor.GOLD + worldName);
        }

        return false;
    }
}
