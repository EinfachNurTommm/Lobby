package com.tom.lobby;

import com.tom.lobby.commands.Commands;
import com.tom.lobby.listeners.ChatListener;
import com.tom.lobby.listeners.HelpListener;
import com.tom.lobby.listeners.InventoryClickListener;
import com.tom.lobby.listeners.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;

public final class Main extends JavaPlugin implements Listener {

    public String serverPrefix = "§7[§f§lCelestrio.net§7]§r ";
    public String noPerm = serverPrefix + "§c Dieser Befehl existiert nicht!";
    public FileConfiguration cfg = getConfig();
    //public boolean buildinv = false;
    public Teleporter myTeleporter = new Teleporter(this);
    public HashMap<Player, Integer> invItemMover = new HashMap<>();
    public HashMap<Player, Boolean> build = new HashMap<>();
    public HashMap<Player, BukkitRunnable> run = new HashMap<>();
    Scoreboard sb;



    @Override
    public void onEnable(){
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        this.getServer().getPluginManager().registerEvents(this, this);
        cfg.options().copyDefaults(true);
        loadConfig();
        registerCommands();
        registerMyEvents();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(null);
        if(invItemMover.containsKey(p)){
            invItemMover.remove(p);
        }
    }

    public void loadConfig(){
        this.getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void registerCommands(){
        Commands Commands = new Commands(this);
        getCommand("setbedwars").setExecutor(Commands);
        getCommand("setskywars").setExecutor(Commands);
        getCommand("setlobby").setExecutor(Commands);
        getCommand("setoneline").setExecutor(Commands);
        getCommand("setmtc").setExecutor(Commands);
        getCommand("setmurder").setExecutor(Commands);
        getCommand("setknockffa").setExecutor(Commands);
        getCommand("setCookieClicker").setExecutor(Commands);
        getCommand("setCaseOpening").setExecutor(Commands);
        getCommand("inv").setExecutor(Commands);
        getCommand("gm").setExecutor(Commands);
        getCommand("build").setExecutor(Commands);
    }

    public void registerMyEvents(){
        new InventoryClickListener(this);
        new ChatListener(this);
        new HelpListener(this);
        new Listeners(this);
    }


    public ItemStack build(Material m, int anzahl, int subId, String name){
        ItemStack itemStack = new ItemStack(m, anzahl, (byte) subId);//Item, Anzahl, subID
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }



    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p =e.getPlayer();
        e.setJoinMessage(null);
        p.getInventory().clear();
        p.getInventory().setItem(0, build(Material.COMPASS, 1, 0, "§7» §aTeleporter"));
        p.getInventory().setItem(1, build(Material.ANVIL, 1, 0, "§8» §cLobbys"));
        p.getInventory().setItem(3, build(Material.ENDER_CHEST, 1, 0, "§8» §aGadgets"));
        p.getInventory().setItem(4, build(Material.BARRIER, 1, 0, "§c§lKein Gadget"));
        p.getInventory().setItem(5, build(Material.CHEST, 1, 0, "§8» §aRänge"));
        p.getInventory().setItem(7, build(Material.BLAZE_ROD, 1, 0, "§7» §aSpieler verstecken"));

        ItemStack is = new ItemStack(Material.SKULL_ITEM, 1 , (short) 3);
        SkullMeta im = (SkullMeta)is.getItemMeta();
        im.setOwner(p.getName());
        im.setDisplayName("§8» §6Freunde");
        is.setItemMeta(im);
        p.getInventory().setItem(8, is);
        p.updateInventory();
        build.put(p, false);

        if(p.hasPermission("lobby.premium")){
            p.getInventory().setItem(31, build(Material.EYE_OF_ENDER, 1, 0, "§8» §cSchild"));
        }

        p.setHealth(20d);
        p.setFoodLevel(20);
        myTeleporter.teleportPlayer(p, "§a§lSpawn");
    }
    public ItemStack buildkopf(Player p, int anzahl){
        ItemStack is = new ItemStack(Material.SKULL_ITEM, 1 , (short) 3);
        SkullMeta im = (SkullMeta)is.getItemMeta();
        im.setOwner("Kopf von " + p.getDisplayName());
        im.setDisplayName("§6Freunde");
        is.setItemMeta(im);
        return is;
    }
}
