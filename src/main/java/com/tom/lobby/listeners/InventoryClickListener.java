package com.tom.lobby.listeners;

import com.tom.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class InventoryClickListener implements Listener {

    private Main plugin;
    public static ArrayList<Player> SpielerVerstecken = new ArrayList<>();


    public InventoryClickListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        String clickedItemName;

        if (e.getCurrentItem().hasItemMeta()) {
            clickedItemName = e.getCurrentItem().getItemMeta().getDisplayName();
            if (!plugin.invItemMover.containsKey(p)) {
                e.setCancelled(true);// verhindert das Items bewegt werden können

                switch (clickedItemName) {

                    //Für den Teleporter
                    case "§a§lBedWars":
                        // teleportiere ihn zu BedWars
                        plugin.myTeleporter.teleportPlayer(p, clickedItemName);
                        break;
                    case "§a§lSkyWars":
                        // teleportiere ihn zu SkyWars
                        plugin.myTeleporter.teleportPlayer(p, clickedItemName);
                        break;
                    case "§a§lSpawn":
                        // teleportiere ihn zum Spawn
                        plugin.myTeleporter.teleportPlayer(p, clickedItemName);
                        break;
                    case "§a§lOneLine":
                        // teleportiere ihn zu Oneline
                        plugin.myTeleporter.teleportPlayer(p, clickedItemName);
                        break;
                    case "§a§lMoveTheCore":
                        // teleportiere ihn zu MoveTheCore
                        plugin.myTeleporter.teleportPlayer(p, clickedItemName);
                        break;
                    case "§a§lMurder":
                        // teleportiere ihn zu Murder;
                        plugin.myTeleporter.teleportPlayer(p, clickedItemName);
                        break;
                    case "§a§lKnockFFA":
                        // teleportiere ihn zu KnockFFA
                        plugin.myTeleporter.teleportPlayer(p, clickedItemName);
                        break;
                    case "§a§lCookieClicker":
                        // teleportiere ihn zu CookieClicker
                        plugin.myTeleporter.teleportPlayer(p, clickedItemName);
                        break;
                    case "§a§lCaseOpening":
                        // teleportiere ihn zu CaseOpening
                        plugin.myTeleporter.teleportPlayer(p, clickedItemName);
                        break;

                    //Gedgets
                    case "§b§lEnderperle":
                        p.getInventory().setItem(4, buildItem(Material.ENDER_PEARL, 1,"§b§lEnderperle", "§7Um sich zu teleportieren"));
                        p.closeInventory();
                        break;
                    case "§b§lFeuerwerk":
                        p.getInventory().setItem(4, buildItem(Material.FIREWORK, 1,"§b§lFeuerwerk", "§7Um ein Feuerwerk zu spawnen"));
                        p.closeInventory();
                        break;
                    case "§b§lEnterhaken":
                        p.getInventory().setItem(4, buildItem(Material.FISHING_ROD, 1,"§b§lEnterhaken", "§7Um sich schneller fort bewegen zu können"));
                        p.closeInventory();
                        break;
                    case "§c§lKein Gadget":
                        p.getInventory().setItem(4, buildItem(Material.BARRIER, 1,"§c§lKein Gadget§a", "§7Es ist kein Gadget aus gewählt"));
                        p.closeInventory();
                        break;
                    case "§8» §cSchild":
                        if (plugin.run.containsKey(p)) {
                            p.sendMessage(plugin.serverPrefix + "§bDu hast nun kein Schild mehr!");
                            plugin.run.get(p).cancel();
                            plugin.run.remove(p);
                            p.closeInventory();
                        } else if (!plugin.run.containsKey(p)) {
                            plugin.run.put(p, new BukkitRunnable() {
                                @Override
                                public void run() {
                                    p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 3);
                                }
                            });
                            plugin.run.get(p).runTaskTimer(plugin, 20, 20);
                            p.sendMessage(plugin.serverPrefix + "§bDu hast nun ein Schild");
                            p.closeInventory();
                        }
                        break;

                    default:
                        break;

                }

            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Inventory inv = p.getInventory();
        if (e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Material m = e.getMaterial();
            switch (m) {
                case COMPASS:
                    inv = p.getServer().createInventory(null, 27, "§8» §cTeleporter");
                    inv.setItem(13, buildItem(Material.NETHER_STAR, 1, "§a§lSpawn", "§7Linksklick zum teleportieren"));
                    inv.setItem(7, buildItem(Material.BED, (byte) 1, "§a§lBedWars", "§7Linksklick zum teleportieren"));
                    inv.setItem(1, build(Material.GRASS, 1, (byte) 1, "§a§lSkyWars", "§7Linksklick zum teleportieren"));
                    inv.setItem(19, build(Material.SANDSTONE, 1, (byte) 1, "§a§lOneLine", "§7Linksklick zum teleportieren"));
                    inv.setItem(25, build(Material.BEACON, 1, 0, "§a§lMoveTheCore", "§7Linksklick zum teleportieren"));
                    inv.setItem(11, buildItem(Material.IRON_SWORD, (byte) 1, "§a§lMurder", "§7Linksklick zum teleportieren"));
                    inv.setItem(15, build(Material.FISHING_ROD, 1, (byte) 0, "§a§lKnockFFA", "§7Linksklick zum teleportieren"));
                    inv.setItem(4, build(Material.CHEST, 1, (byte) 0, "§a§lCaseOpening", "§7Linksklick zum teleportieren"));
                    inv.setItem(22, build(Material.COOKIE, 1, (byte) 0, "§a§lCookieClicker", "§7Linksklick zum teleportieren"));
                    p.openInventory(inv);
                    break;

                case BLAZE_ROD:
                    if (SpielerVerstecken.contains(p)) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            SpielerVerstecken.remove(p);
                            p.showPlayer(all);
                            // p.sendMessage("§aDu siehst nun alle spieler");
                        }
                    } else {
                        SpielerVerstecken.add(p);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            p.hidePlayer(all);
                            // p.sendMessage("§cDu hasst alle spieler versteckt");
                        }
                    }
                    break;

                case ENDER_CHEST:
                    inv = p.getServer().createInventory(null, 9, "§8» §cGadgets");
                    inv.setItem(0, buildItem(Material.ENDER_PEARL, 1,"§b§lEnderperle", "§7Um sich schneller zu teleportieren"));
                    inv.setItem(2, buildItem(Material.FIREWORK, 1,"§b§lFeuerwerk", "§7Um ein Feuerwerk zu spawnen"));
                    inv.setItem(4, buildItem(Material.FISHING_ROD, 1,"§b§lEnterhaken", "§7Um sich schneller fort bewegen können"));
                    inv.setItem(8, buildItem(Material.BARRIER, 1,"§c§lKein Gadget", "§7Um kein Gadget auszuwählen"));
                    p.openInventory(inv);
                    e.setCancelled(true);
                    break;

                case ENDER_PEARL:
                    if(e.isCancelled()){
                        e.setCancelled(false);
                    }
                    break;

                case CHEST:
                    if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aRänge")){
                        inv = p.getServer().createInventory(null, 9, "§8» §aRänge");
                        inv.setItem(0, buildItem(Material.BRICK, 1,"§6§lKupfer", "§7Preis §a10.000 Münzen"));
                        inv.setItem(2, buildItem(Material.IRON_NUGGET, 1,"§8§lEisen", "§7Preis §a20.000 Münzen"));
                        inv.setItem(4, buildItem(Material.IRON_INGOT, 1,"§7§lSilber", "§7Preis §a40.000 Münzen"));
                        inv.setItem(6, buildItem(Material.GOLD_INGOT, 1,"§e§lGold", "§7Preis §a80.000 Münzen"));
                        inv.setItem(8, buildItem(Material.DIAMOND, 1,"§3§lDiamant", "§7Preis §a160.000 Münzen"));
                        p.openInventory(inv);
                        e.setCancelled(true);
                    }
                    break;

                default:
                    break;
            }
        }
    }




    public ItemStack build(Material m, int anzahl, int subId, String name, String subname) {
        ItemStack itemStack = new ItemStack(m, anzahl, (byte) subId);// Item,
        // Anzahl,
        // subID
        ItemMeta meta = itemStack.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(subname);
        meta.setLore(lore);

        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack buildItem(Material m, int anzahl, String name, String subname) {
        ItemStack is = new ItemStack(m, anzahl);// m z.B. Material.DIAMOND_AXE
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        List<String> lore = new ArrayList<String>();
        lore.add(subname);
        im.setLore(lore);
        is.setItemMeta(im);
        return is;
    }

}
