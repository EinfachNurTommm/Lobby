package com.tom.lobby.commands;

import com.tom.lobby.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {

    public Main plugin;

    public Commands(Main main) {
        this.plugin = main;
    }

    /**
     * 	/setBedWars befehl
     * 	/setSkyWars befehl
     * 	/setSpawn befehl
     * 	/setOneline befehl
     *  /setTTT befehl
     *  /setMLGRush
     *  –Ž
     */

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args){
        Player p = (Player) sender;
        Inventory inv = p.getInventory();
        switch(cmd.getName()){
            case "setBedWars":
                if(p.hasPermission("set.bedwars")) {
                    saveCoordinates("§a§lSkyWars", p);
                    p.sendMessage(plugin.serverPrefix + "§aDer §a§lBedWars-Point§r§a wurde gesetzt");
                    System.out.println("Der §a§lBedWars-Point §r§awurde gespeichert");
                } else {
                    p.sendMessage(plugin.noPerm);
                }
                break;
            case "setSkyWars":
                if(p.hasPermission("set.skywars")) {
                    saveCoordinates("§a§lSkyWars", p);
                } else {
                    p.sendMessage(plugin.noPerm);
                }
                break;

            case "setLobby":
                if(p.hasPermission("set.spawn")) {
                    saveCoordinates("§a§lSpawn", p);
                } else {
                    p.sendMessage(plugin.noPerm);
                }
                break;
            case "setOneLine":
                if(p.hasPermission("set.oneline")) {
                    saveCoordinates("§a§lOneLine", p);
                } else {
                    p.sendMessage(plugin.noPerm);
                }
                break;
            case "setMTC":
                if(p.hasPermission("set.mtc")) {
                    saveCoordinates("§a§lMoveTheCore", p);
                } else {
                    p.sendMessage(plugin.noPerm);
                }
                break;
            case "setMurder":
                if(p.hasPermission("set.murder")) {
                    saveCoordinates("§a§lMurder", p);
                } else {
                    p.sendMessage(plugin.noPerm);
                }
                break;
            case "setKnockFFA":
                if(p.hasPermission("set.knockffa")) {
                    saveCoordinates("§a§lKnockFFA", p);
                } else {
                    p.sendMessage(plugin.noPerm);
                }
                break;
            case "setCookieClicker":
                if(p.hasPermission("set.cookieclicker")) {
                    saveCoordinates("§a§lCookieClicker", p);
                } else {
                    p.sendMessage(plugin.noPerm);
                }
                break;
            case "setCaseOpening":
                if(p.hasPermission("set.caseopening")) {
                    saveCoordinates("§a§lCaseOpening", p);
                } else {
                    p.sendMessage(plugin.noPerm);
                }
                break;
            case "inv":
                if(p.hasPermission("build.inv")){
                    if(!plugin.invItemMover.containsKey(p)){
                        plugin.invItemMover.put(p, 1);
                        p.sendMessage("§bDu kannst dein Inventar nun bearbeiten!");
                    }else{
                        p.sendMessage("§bDu kannst dein Inventar nun nicht mehr bearbeiten!");
                        plugin.invItemMover.remove(p);
                    }
                }else{
                    p.sendMessage(plugin.noPerm);
                }
                break;
            case "gm":
                if(p.hasPermission("lobby.gm")){
                    if(args.length >= 1) {
                        try {
                            int gmNumber = Integer.parseInt(args[0]);
                            if(gmNumber >=0 && gmNumber <=3) {
                                if(gmNumber == 0) {
                                    p.setGameMode(GameMode.SURVIVAL);
                                    p.sendMessage(plugin.serverPrefix + "Dein Spielmodus wurde auf§6 Survival §fgesetzt!");
                                } else if(gmNumber == 1) {
                                    p.setGameMode(GameMode.CREATIVE);
                                    p.sendMessage(plugin.serverPrefix + "Dein Spielmodus wurde auf§6 Creative §fgesetzt!");
                                } else if(gmNumber == 2) {
                                    p.setGameMode(GameMode.ADVENTURE);
                                    p.sendMessage(plugin.serverPrefix + "Dein Spielmodus wurde auf§6 Adventure §fgesetzt!");
                                } else if(gmNumber == 3) {
                                    p.setGameMode(GameMode.SPECTATOR);
                                    p.sendMessage(plugin.serverPrefix + "Dein Spielmodus wurde auf§6 Spectator §fgesetzt!");
                                }
                            }
                        } catch (Exception e) {
                            p.sendMessage(plugin.serverPrefix + "§cDieser Gamemode existiert nicht!");
                            return true;
                        }
                    }
                }else{
                    p.sendMessage(plugin.noPerm);
                }
                break;
            case "kopf":
                if(p.hasPermission("command.kopf")){
                    if(args.length == 1){
                        ItemStack is = new ItemStack(Material.SKULL_ITEM, 1 , (short) 3);
                        SkullMeta im = (SkullMeta)is.getItemMeta();
                        im.setOwner(args[0]);
                        im.setDisplayName("Kopf von §4" + args[0]);
                        is.setItemMeta(im);
                        inv.addItem(new ItemStack[] {is});
                        p.updateInventory();
                        return true;
                    }else{
                        p.sendMessage(plugin.serverPrefix + "§4/kopf <Spieler>");
                        return true;
                    }
                }else{
                    p.sendMessage(plugin.noPerm);
                }
                break;

            case "heal":
                if(p.hasPermission("command.heal")) {
                    p.setHealth(20d);
                    p.setFoodLevel(20);
                } else {
                    p.sendMessage(plugin.noPerm);
                }
                break;

            case "build":
                if(p.hasPermission("command.build")) {
                    if(plugin.build.get(p) == true) {
                        plugin.build.put(p, false);
                        p.sendMessage(plugin.serverPrefix + "§cDu kannst nun nicht mehr bauen!");
                    } else {
                        plugin.build.put(p, true);
                        p.sendMessage(plugin.serverPrefix + "§cDu kannst nun bauen!");
                    }
                } else {
                    p.sendMessage(plugin.noPerm);
                }
                break;



            default:

                return false;
        }

        return true;
    }

    private void saveCoordinates(String spielmodus, Player p){
        plugin.cfg.set(spielmodus +"-World", p.getWorld().getName());
        plugin.cfg.set(spielmodus +"-x", p.getLocation().getX());
        plugin.cfg.set(spielmodus +"-y", p.getLocation().getY());
        plugin.cfg.set(spielmodus +"-z", p.getLocation().getZ());
        plugin.cfg.set(spielmodus +"-yaw", p.getLocation().getYaw());
        plugin.cfg.set(spielmodus +"-pitch", p.getLocation().getPitch());
        p.sendMessage(plugin.serverPrefix + "§aDer "+ spielmodus +"-Point§r§a wurde gesetzt");
        plugin.cfg.options().copyDefaults(true);
        plugin.saveConfig();
        System.out.println("Der "+ spielmodus +"-Point wurde gespeichert");
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
}
