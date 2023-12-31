package com.gligoth.worldbar.commands;

import com.gligoth.worldbar.Permissions;
import com.gligoth.worldbar.WorldbarConfiguration;
import com.gligoth.worldbar.WorldbarManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

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
            if (sender.hasPermission(Permissions.ALLOW_TOGGLE)) {
                this.worldbarManager.toggleBarForPlayer(player);
            }
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "toggle":
                if (sender.hasPermission(Permissions.ALLOW_TOGGLE)) {
                    this.worldbarManager.toggleBarForPlayer(player);
                }
                return true;

            case "reload":
                if (sender.hasPermission(Permissions.RELOAD_CONFIG)) {
                    getLogger().info("[Worldbar] Reloading config");
                    this.worldbarConfiguration.reloadConfig();
                    this.worldbarManager.reloadWorldbarConfiguration();
                }
                return true;

            default:
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid Command"));
                return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("worldbar")) {

            List<String> autoCompletes = new ArrayList<>();

            if (sender.hasPermission(Permissions.ALLOW_TOGGLE)) {
                autoCompletes.add("toggle");
            }

            if (sender.hasPermission(Permissions.RELOAD_CONFIG)) {
                autoCompletes.add("reload");
            }

            if (args.length == 1) {
                return autoCompletes;
            }
        }

        return null;
    }
}
