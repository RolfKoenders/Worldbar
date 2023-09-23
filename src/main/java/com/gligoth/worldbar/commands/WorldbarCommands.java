package com.gligoth.worldbar.commands;

import com.gligoth.worldbar.WorldbarConfiguration;
import com.gligoth.worldbar.WorldbarManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class WorldbarCommands implements CommandExecutor, TabCompleter {

    private final JavaPlugin plugin;
    private final WorldbarManager worldbarManager;
    private final WorldbarConfiguration worldbarConfiguration;

    public WorldbarCommands(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldbarManager = WorldbarManager.getInstance();
        this.worldbarConfiguration = WorldbarConfiguration.getInstance();
    }

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
                this.worldbarManager.toggleBarForPlayer(player);
                return true;

            case "reload":
                System.out.println("Reloading config");
                this.worldbarConfiguration.reloadConfig();
                this.worldbarManager.reloadWorldbarConfiguration();
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
            autoCompletes.add("reload");

            if (args.length == 1) {
                return autoCompletes;
            }
        }

        return null;
    }
}
