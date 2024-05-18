package org.apo.hardwar.System

import org.apo.hardwar.HardWar
import org.bukkit.*
import org.bukkit.block.banner.Pattern
import org.bukkit.block.banner.PatternType
import org.bukkit.inventory.ItemRarity
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.meta.BannerMeta

class Recipe {


    fun R() {
        val item = ItemStack(Material.COMPASS, 1)

        val meta = item.itemMeta
        meta!!.setDisplayName(ChatColor.AQUA.toString() + "마컴")
        meta.setRarity(ItemRarity.UNCOMMON)
        val lore = ArrayList<String>()
        lore.add("§7가장 가까운 플레이어 감지")
        meta.lore = lore
        item.setItemMeta(meta)

        val key = NamespacedKey(HardWar.Instance, "Mcom")

        val McomR = ShapedRecipe(key, item)

        McomR.shape("   ", "NCP", "   ")
        McomR.setIngredient('C', Material.COMPASS)
        McomR.setIngredient('N', Material.NETHERITE_SCRAP)
        McomR.setIngredient('P', Material.PLAYER_HEAD)

        Bukkit.addRecipe(McomR)
        //==============================================================================
        val item1 = ItemStack(Material.SHIELD, 1)

        val banner = ItemStack(Material.LIGHT_GRAY_BANNER, 1)
        val bannerMeta = banner.itemMeta as BannerMeta?
        bannerMeta!!.addPattern(Pattern(DyeColor.WHITE, PatternType.STRIPE_DOWNLEFT))
        bannerMeta.addPattern(Pattern(DyeColor.LIGHT_BLUE, PatternType.GRADIENT))
        bannerMeta.addPattern(Pattern(DyeColor.WHITE, PatternType.GRADIENT_UP))
        bannerMeta.addPattern(Pattern(DyeColor.BLACK, PatternType.BORDER))
        banner.setItemMeta(bannerMeta)

        val meta1 = item1.itemMeta
        meta1!!.setDisplayName(ChatColor.AQUA.toString() + "거울")
        val lore1 = ArrayList<String>()
        lore1.add("§7데미지 반사(쿨타임 10분)")
        meta1.lore = lore1

        // 패던 방패에 추가
        item1.setItemMeta(meta1)

        val key1 = NamespacedKey(HardWar.Instance, "Mi")

        val Mi = ShapedRecipe(key1, item1)
        Mi.shape(" A ", "ASA", " A ")
        Mi.setIngredient('S', Material.SHIELD)
        Mi.setIngredient('A', Material.AMETHYST_SHARD)

        Bukkit.addRecipe(Mi)

        //==============================================================================
        val u = ItemStack(Material.ECHO_SHARD,4)
        val m = u.itemMeta
        m!!.setCustomModelData(1)
        m.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + "강화석")
        m.setRarity(ItemRarity.RARE)
        m.setMaxStackSize(99)
        u.setItemMeta(m)
        val key2 = NamespacedKey(HardWar.Instance, "Upgrade")
        val Up = ShapedRecipe(key2, u)
        Up.shape("LE")
        Up.setIngredient('L', Material.LAPIS_LAZULI)
        Up.setIngredient('E', Material.ECHO_SHARD)
        Bukkit.addRecipe(Up)
        //==============================================================================
        val e = ItemStack(Material.AMETHYST_SHARD,10)
        val me = u.itemMeta
        me!!.setCustomModelData(1)
        me.setDisplayName(ChatColor.AQUA.toString() + "마법부여석")
        me.setMaxStackSize(99)
        me.setRarity(ItemRarity.RARE)
        e.setItemMeta(me)
        val key3 = NamespacedKey(HardWar.Instance, "Enchant")
        val En = ShapedRecipe(key3, e)
        En.shape("DA")
        En.setIngredient('D', Material.DIAMOND)
        En.setIngredient('A', Material.AMETHYST_SHARD)
        Bukkit.addRecipe(En)
    }
}
