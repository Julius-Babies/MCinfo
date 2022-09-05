package com.babiesjulius.mcinfo.handlers;

import com.babiesjulius.mcinfo.tools.PlayerTools;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PlayerlistHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder response = new StringBuilder("{");
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            response.append("\"").append(p.getName()).append("\" : { \"last_logon\": \"").append(PlayerTools.getScoreboardTagData(p, "last_login")).append("\" }, ");
        }
        response.append(" }");
        exchange.sendResponseHeaders(200, response.length());

        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
