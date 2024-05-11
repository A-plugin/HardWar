package org.apo.hardwar;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Avatar implements Listener {
    @EventHandler
    public void Quit(PlayerQuitEvent e) {
        Player p=e.getPlayer();
        Location l=p.getLocation();
        l.add(0,0,0);
        if (p.getGameMode().equals(GameMode.SURVIVAL)){
            p.getWorld().spawn(l, ArmorStand.class, a -> {
                ItemStack PlayerH = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta Ph = (SkullMeta) PlayerH.getItemMeta();
                Ph.setOwningPlayer(p);
                Ph.setDisplayName(p.getName());
                PlayerH.setItemMeta(Ph);
                a.setSmall(true);
                a.setHelmet(PlayerH);
                a.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
                a.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                a.setBoots(new ItemStack(Material.LEATHER_BOOTS));
                a.setGravity(false);
                a.addScoreboardTag(p.getName());
                a.setCustomName(p.getName());
                a.setCustomNameVisible(true);
                a.setInvulnerable(true);
            });
        }
    }

    @EventHandler
    public void ar(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof ArmorStand) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        for (World world : Bukkit.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (!(entity instanceof ArmorStand)) continue;

                if (entity.getCustomName() == null || !entity.getCustomName().contains(player.getName())) continue;

                player.teleport(entity.getLocation());
                entity.remove();
            }
        }
    }

    @EventHandler
    public void Potals(PlayerPortalEvent e) {
        Player p=e.getPlayer();
        World w=p.getWorld();
        Location l=e.getTo().clone();
        if (w.getEnvironment() == World.Environment.NETHER) {
            l.setX(l.getX() * 4);
            l.setZ(l.getZ() * 4);
        } else if (w.getEnvironment() == World.Environment.NORMAL) {
            l.setX(l.getX() / 4);
            l.setZ(l.getZ() / 4);
        }
        e.setTo(l);
    }
}
