package org.apo.hardwar.System;

import org.apo.hardwar.HardWar;
import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Recipe {
    public void R() {
        ItemStack item = new ItemStack(Material.COMPASS, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA+"마컴");
        meta.setRarity(ItemRarity.UNCOMMON);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§7가장 가까운 플레이어 감지");
        meta.setLore(lore);
        item.setItemMeta(meta);

        NamespacedKey key=new NamespacedKey(HardWar.Instance, "Mcom");

        ShapedRecipe McomR=new ShapedRecipe(key, item);
        McomR.shape("N  ", " C ", "  P");
        McomR.setIngredient('C', Material.COMPASS);
        McomR.setIngredient('N', Material.NETHERITE_SCRAP);
        McomR.setIngredient('P', Material.PLAYER_HEAD);

        Bukkit.addRecipe(McomR);


        ItemStack item1 = new ItemStack(Material.SHIELD, 1);

        ItemStack banner = new ItemStack(Material.LIGHT_GRAY_BANNER, 1);
        BannerMeta bannerMeta = (BannerMeta) banner.getItemMeta();
        bannerMeta.addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_DOWNLEFT));
        bannerMeta.addPattern(new Pattern(DyeColor.LIGHT_BLUE, PatternType.GRADIENT));
        bannerMeta.addPattern(new Pattern(DyeColor.WHITE, PatternType.GRADIENT_UP));
        bannerMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.BORDER));
        banner.setItemMeta(bannerMeta);

        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.AQUA+"거울");
        ArrayList<String> lore1 = new ArrayList<String>();
        lore1.add("§7데미지 반사(쿨타임 10분)");
        meta1.setLore(lore1);

        // 패던 방패에 추가
        item1.setItemMeta(meta1);

        NamespacedKey key1=new NamespacedKey(HardWar.Instance, "Mi");

        ShapedRecipe Mi=new ShapedRecipe(key1, item1);
        Mi.shape(" A ", "ASA", " A ");
        Mi.setIngredient('S', Material.SHIELD);
        Mi.setIngredient('A', Material.AMETHYST_SHARD);

        Bukkit.addRecipe(Mi);

        ItemStack u=new ItemStack(Material.ECHO_SHARD);
        ItemMeta m=u.getItemMeta();
        m.setCustomModelData(1);
        m.setDisplayName(ChatColor.LIGHT_PURPLE+"강화석");
        m.setRarity(ItemRarity.RARE);
        m.setMaxStackSize(99);
        u.setItemMeta(m);
        NamespacedKey key2= new NamespacedKey(HardWar.Instance, "Upgrade");
        ShapedRecipe Up=new ShapedRecipe(key2, u);
        Up.shape("LE");
        Up.setIngredient('L', Material.LAPIS_LAZULI);
        Up.setIngredient('E',Material.ECHO_SHARD);
        Bukkit.addRecipe(Up);

        ItemStack e=new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta me=u.getItemMeta();
        me.setCustomModelData(1);
        me.setDisplayName(ChatColor.AQUA+"마법부여석");
        me.setMaxStackSize(99);
        me.setRarity(ItemRarity.RARE);
        e.setItemMeta(me);
        NamespacedKey key3= new NamespacedKey(HardWar.Instance, "Enchant");
        ShapedRecipe En=new ShapedRecipe(key3, e);
        En.shape("DA");
        En.setIngredient('D', Material.DIAMOND);
        En.setIngredient('A',Material.AMETHYST_SHARD);
        Bukkit.addRecipe(En);
    }
}
