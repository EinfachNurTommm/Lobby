package com.tom.lobby.listeners;

import com.tom.lobby.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

public class Listeners implements Listener {

    private Main plugin;

    public Listeners(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    // Verhindert das abbauen von Blöcken
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(plugin.build.get(p) == false){
            e.setCancelled(true);
        }
    }

    // Verhindert das Platzieren von Blöcken, wenn der Spieler nicht in der HashMap build ist
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(plugin.build.get(p) == false){
            e.setCancelled(true);
        }
    }

    // Verhindert das aufsammeln von Items
    @EventHandler
    public void onBlockPickup(InventoryPickupItemEvent e) {
        e.setCancelled(true);
    }


    // Verhindert das droppen von Items, wenn der Spieler nicht in der HashMap invItemMover ist
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (!plugin.invItemMover.containsKey(p)) {
            e.setCancelled(true);
        }
    }

    //Verhindert generellen Schaden

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }


    // Verhindert das verlieren von Essen
    @EventHandler
    public void onEat(FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
        e.setCancelled(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (plugin.run.containsKey(e.getPlayer())) {
            plugin.run.get(e.getPlayer()).cancel();
            plugin.run.remove(e.getPlayer());
        }
    }


    // Die Vektor berechnung für das Schild welches ein Teammitglied anmachen kann um Spieler von sich fern zu halten
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        for (Player players : plugin.run.keySet()) {
            if (p != players && !p.hasPermission("lobby.inschild")) {
                if (p.getLocation().distance(players.getLocation()) <= 5) {
                    double Ax = p.getLocation().getX();
                    double Ay = p.getLocation().getY();
                    double Az = p.getLocation().getZ();

                    double Bx = players.getLocation().getX();
                    double By = players.getLocation().getY();
                    double Bz = players.getLocation().getZ();

                    double x = Ax - Bx;
                    double y = Ay - By;
                    double z = Az - Bz;
                    Vector v = new Vector(x, y, z).normalize().multiply(1D).setY(0.3D);
                    p.setVelocity(v);
                }
            }
        }

        if (plugin.run.containsKey(p)) {

            for (Entity entity : p.getNearbyEntities(5, 5, 5)) {
                if (entity instanceof Player) {
                    Player target = (Player) entity;
                    if (p != target) {
                        if (!target.hasPermission("lobby.inschild")) {

                            double Ax = p.getLocation().getX();
                            double Ay = p.getLocation().getY();
                            double Az = p.getLocation().getZ();

                            double Bx = target.getLocation().getX();
                            double By = target.getLocation().getY();
                            double Bz = target.getLocation().getZ();

                            double x = Bx - Ax;
                            double y = By - Ay;
                            double z = Bz - Az;
                            Vector v = new Vector(x, y, z).normalize().multiply(0.5D).setY(0.3D);
                            target.setVelocity(v);
                        }
                    }
                }
            }

        }
    }
}
