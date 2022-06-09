package com.tom.lobby.listeners;

import com.tom.lobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private Main plugin;

    public ChatListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        // Erster String Parameter ist player.getDisplayName(), zweiter event.getMessage()
        // Verwendung der Parameter ist nicht nötig
        e.setFormat("%s§8 :§f %s"); // -> "Spieler: Nachricht"
    }
}
