package org.apo.hardwar.System;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemD {
    public void set() {
        ItemStack i=new ItemStack(Material.END_CRYSTAL);
        i.getItemMeta().setMaxStackSize(1);
    }
}
