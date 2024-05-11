package org.apo.hardwar.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Upgrade implements Listener {
    public void Inv(Player p) {
        Inventory i= Bukkit.createInventory(p,9*3,"강화");
        ItemStack itemStack=new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta= itemStack.getItemMeta();
        meta.setDisplayName(" ");
        meta.setCustomModelData(24);
        itemStack.setItemMeta(meta);
        i.setItem(0, itemStack);
        p.openInventory(i);
    }
}
