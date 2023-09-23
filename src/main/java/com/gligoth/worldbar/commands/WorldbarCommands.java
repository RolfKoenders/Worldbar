package com.gligoth.worldbar.commands;

import com.gligoth.worldbar.WorldbarManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WorldbarCommands implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args == null || args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNot enough args"));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "toggle":
                System.out.println("Adding " + player.getName() + " to the bossBar of world: " + player.getWorld());
                WorldbarManager worldbarManager = WorldbarManager.getInstance();
                worldbarManager.toggleBarForPlayer(player);
                return true;

            default:
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid Command"));
                return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("worldbar")) {

            List<String> autoCompletes = new ArrayList<>();
            autoCompletes.add("toggle");

            if (args.length == 1) {
                return autoCompletes;
            }
        }

        return null;
    }
}
