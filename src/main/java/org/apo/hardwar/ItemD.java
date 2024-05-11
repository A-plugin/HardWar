package org.apo.hardwar;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemD {
    public void set() {
        ItemStack i=new ItemStack(Material.END_CRYSTAL);
        i.getItemMeta().setMaxStackSize(1);
    }
}
