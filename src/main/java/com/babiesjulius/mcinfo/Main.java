package com.babiesjulius.mcinfo;

import com.babiesjulius.mcinfo.commands.PortCommand;
import com.babiesjulius.mcinfo.handlers.PlayerlistHandler;
import com.babiesjulius.mcinfo.listener.JoinListener;
import com.sun.net.httpserver.HttpServer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Objects;

public final class Main extends JavaPlugin {

    private static Main instance;

    private Thread networkThread;
    private HttpServer server;

    private static FileConfiguration config;

    public static FileConfiguration getConfigObject() { return config; }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        config = this.getConfig();
        config.addDefault("port", 42001);
        config.options().copyDefaults(true);
        saveConfig();

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);

        Objects.requireNonNull(this.getCommand("port")).setExecutor(new PortCommand());
        Objects.requireNonNull(this.getCommand("port")).setTabCompleter(new PortCommand());

        instance = this;

        networkThread = new Thread(() -> {
            try {
                server = HttpServer.create(new InetSocketAddress(this.getConfig().getInt("port")), 0);
                server.createContext("/playerlist", new PlayerlistHandler());
                server.setExecutor(null);
                server.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        networkThread.start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        networkThread.interrupt();
        server.stop(0);
    }
}
