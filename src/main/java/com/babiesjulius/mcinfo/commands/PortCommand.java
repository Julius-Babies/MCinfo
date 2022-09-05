package com.babiesjulius.mcinfo.commands;

import com.babiesjulius.mcinfo.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PortCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String s, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "MCInfo" + ChatColor.WHITE + "] " + ChatColor.RED + "Fehler: Du hast keine Rechte um diesen Befehl auszuführen.");
            return false;
        }
        sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "MCInfo" + ChatColor.WHITE + "] " + ChatColor.RED + "Der Port ist auf " + Main.getConfigObject().get("port") + " gesetzt.");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>();
    }
}
