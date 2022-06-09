package com.tom.lobby.listeners;

import com.tom.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class HelpListener  implements Listener {

    private Main plugin;

    public HelpListener(Main main) {
        this.plugin = main;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onfalseCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if(!(e.isCancelled())){
            String msg = e.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if(topic == null) {
                p.sendMessage(plugin.serverPrefix + "§cDieser Befehl existiert nicht!");
                e.setCancelled(true);
            }
        }
    }
}
