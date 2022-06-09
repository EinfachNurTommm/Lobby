package com.tom.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleporter {

    public Main plugin;


    public Teleporter(Main main) {
        this.plugin = main;
    }

    public void teleportPlayer(Player p, String spielmodus){
        plugin.cfg.get(spielmodus +"-World");

        String myWorld=plugin.cfg.getString(spielmodus +"-World");

        double myX=plugin.cfg.getDouble(spielmodus +"-x");

        double myY=plugin.cfg.getDouble(spielmodus +"-y");

        double myZ=plugin.cfg.getDouble(spielmodus +"-z");

        double myYaw=plugin.cfg.getDouble(spielmodus +"-yaw");

        double myPitch=plugin.cfg.getDouble(spielmodus +"-pitch");


        Location loc = new Location(Bukkit.getWorld(myWorld), myX, myY, myZ);


        //Spieler in die richtige Richtung schauen lassen
        loc.setYaw((float) myYaw);
        loc.setPitch((float) myPitch);
        p.teleport(loc);
    }
}
