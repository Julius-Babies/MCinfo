package com.babiesjulius.mcinfo.tools;

import org.bukkit.entity.Player;

public class PlayerTools {

    public static String getScoreboardTagData(Player player, String key) {
        for(String value : player.getScoreboardTags()) {
            if (value.startsWith(key + ":")) {
                return value.replaceFirst(key + ":", "");
            }
        }
        return null;
    }
}
