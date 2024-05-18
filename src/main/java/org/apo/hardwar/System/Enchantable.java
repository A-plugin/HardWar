package org.apo.hardwar.System;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Enchantable {
    public boolean armor(ItemStack itemStack) {
        Material material=itemStack.getType();
        if (material.equals(Material.LEATHER_BOOTS)) return true;
        if (material.equals(Material.LEATHER_LEGGINGS)) return true;
        if (material.equals(Material.LEATHER_CHESTPLATE)) return true;
        if (material.equals(Material.LEATHER_HELMET)) return true;
        if (material.equals(Material.IRON_BOOTS)) return true;
        if (material.equals(Material.IRON_LEGGINGS)) return true;
        if (material.equals(Material.IRON_CHESTPLATE)) return true;
        if (material.equals(Material.IRON_HELMET)) return true;
        if (material.equals(Material.GOLDEN_BOOTS)) return true;
        if (material.equals(Material.GOLDEN_LEGGINGS)) return true;
        if (material.equals(Material.GOLDEN_CHESTPLATE)) return true;
        if (material.equals(Material.GOLDEN_HELMET)) return true;
        if (material.equals(Material.DIAMOND_BOOTS)) return true;
        if (material.equals(Material.DIAMOND_LEGGINGS)) return true;
        if (material.equals(Material.DIAMOND_CHESTPLATE)) return true;
        if (material.equals(Material.DIAMOND_HELMET)) return true;
        if (material.equals(Material.NETHERITE_BOOTS)) return true;
        if (material.equals(Material.NETHERITE_LEGGINGS)) return true;
        if (material.equals(Material.NETHERITE_CHESTPLATE)) return true;
        if (material.equals(Material.NETHERITE_HELMET)) return true;
        return false;
    }

    public boolean Helmet(ItemStack itemStack) {
        Material material=itemStack.getType();
        if (material.equals(Material.LEATHER_HELMET)) return true;
        if (material.equals(Material.IRON_HELMET)) return true;
        if (material.equals(Material.GOLDEN_HELMET)) return true;
        if (material.equals(Material.DIAMOND_HELMET)) return true;
        if (material.equals(Material.NETHERITE_HELMET)) return true;
        return false;
    }

    public boolean Chestplate(ItemStack itemStack) {
        Material material=itemStack.getType();
        if (material.equals(Material.LEATHER_CHESTPLATE)) return true;
        if (material.equals(Material.IRON_CHESTPLATE)) return true;
        if (material.equals(Material.GOLDEN_CHESTPLATE)) return true;
        if (material.equals(Material.DIAMOND_CHESTPLATE)) return true;
        if (material.equals(Material.NETHERITE_CHESTPLATE)) return true;
        return false;
    }

    public boolean Leggings(ItemStack itemStack) {
        Material material=itemStack.getType();
        if (material.equals(Material.LEATHER_LEGGINGS)) return true;
        if (material.equals(Material.IRON_LEGGINGS)) return true;
        if (material.equals(Material.GOLDEN_LEGGINGS)) return true;
        if (material.equals(Material.DIAMOND_LEGGINGS)) return true;
        if (material.equals(Material.NETHERITE_LEGGINGS)) return true;
        return false;
    }

    public boolean Boots(ItemStack itemStack) {
        Material material=itemStack.getType();
        if (material.equals(Material.LEATHER_BOOTS)) return true;
        if (material.equals(Material.IRON_BOOTS)) return true;
        if (material.equals(Material.GOLDEN_BOOTS)) return true;
        if (material.equals(Material.DIAMOND_BOOTS)) return true;
        if (material.equals(Material.NETHERITE_BOOTS)) return true;
        return false;
    }

    public boolean Stuff(ItemStack itemStack) {
        Material material=itemStack.getType();
        if (material.equals(Material.WOODEN_SWORD)) return true;
        if (material.equals(Material.STONE_SWORD)) return true;
        if (material.equals(Material.IRON_SWORD)) return true;
        if (material.equals(Material.GOLDEN_SWORD)) return true;
        if (material.equals(Material.DIAMOND_SWORD)) return true;
        if (material.equals(Material.NETHERITE_SWORD)) return true;
        if (material.equals(Material.WOODEN_AXE)) return true;
        if (material.equals(Material.STONE_AXE)) return true;
        if (material.equals(Material.IRON_AXE)) return true;
        if (material.equals(Material.GOLDEN_AXE)) return true;
        if (material.equals(Material.DIAMOND_AXE)) return true;
        if (material.equals(Material.NETHERITE_AXE)) return true;
        if (material.equals(Material.WOODEN_PICKAXE)) return true;
        if (material.equals(Material.STONE_PICKAXE)) return true;
        if (material.equals(Material.IRON_PICKAXE)) return true;
        if (material.equals(Material.GOLDEN_PICKAXE)) return true;
        if (material.equals(Material.DIAMOND_PICKAXE)) return true;
        if (material.equals(Material.NETHERITE_PICKAXE)) return true;
        if (material.equals(Material.WOODEN_SHOVEL)) return true;
        if (material.equals(Material.STONE_SHOVEL)) return true;
        if (material.equals(Material.IRON_SHOVEL)) return true;
        if (material.equals(Material.GOLDEN_SHOVEL)) return true;
        if (material.equals(Material.DIAMOND_SHOVEL)) return true;
        if (material.equals(Material.NETHERITE_SHOVEL)) return true;
        if (material.equals(Material.WOODEN_HOE)) return true;
        if (material.equals(Material.STONE_HOE)) return true;
        if (material.equals(Material.IRON_HOE)) return true;
        if (material.equals(Material.GOLDEN_HOE)) return true;
        if (material.equals(Material.DIAMOND_HOE)) return true;
        if (material.equals(Material.NETHERITE_HOE)) return true;
        if (material.equals(Material.FISHING_ROD)) return true;
        if (material.equals(Material.BOW)) return true;
        if (material.equals(Material.CROSSBOW)) return true;
        if (material.equals(Material.TRIDENT)) return true;
        if (material.equals(Material.FLINT_AND_STEEL)) return true;
        if (material.equals(Material.SHEARS)) return true;
        return false;
    }

    public boolean sword(ItemStack itemStack) {
        Material material=itemStack.getType();
        if (material.equals(Material.WOODEN_SWORD)) return true;
        if (material.equals(Material.STONE_SWORD)) return true;
        if (material.equals(Material.IRON_SWORD)) return true;
        if (material.equals(Material.GOLDEN_SWORD)) return true;
        if (material.equals(Material.DIAMOND_SWORD)) return true;
        if (material.equals(Material.NETHERITE_SWORD)) return true;
        return false;
    }

    public double getArmorDefense(Material material) {
        switch (material) {
            case LEATHER_HELMET:
                return 1.0;
            case LEATHER_CHESTPLATE:
                return 3.0;
            case LEATHER_LEGGINGS:
                return 2.0;
            case LEATHER_BOOTS:
                return 1.0;
            case CHAINMAIL_HELMET:
                return 2.0;
            case CHAINMAIL_CHESTPLATE:
                return 5.0;
            case CHAINMAIL_LEGGINGS:
                return 4.0;
            case CHAINMAIL_BOOTS:
                return 1.0;
            case IRON_HELMET:
                return 2.0;
            case IRON_CHESTPLATE:
                return 6.0;
            case IRON_LEGGINGS:
                return 5.0;
            case IRON_BOOTS:
                return 2.0;
            case DIAMOND_HELMET:
                return 3.0;
            case DIAMOND_CHESTPLATE:
                return 8.0;
            case DIAMOND_LEGGINGS:
                return 6.0;
            case DIAMOND_BOOTS:
                return 3.0;
            case GOLDEN_HELMET:
                return 2.0;
            case GOLDEN_CHESTPLATE:
                return 5.0;
            case GOLDEN_LEGGINGS:
                return 3.0;
            case GOLDEN_BOOTS:
                return 1.0;
            case NETHERITE_HELMET:
                return 3.0;
            case NETHERITE_CHESTPLATE:
                return 8.0;
            case NETHERITE_LEGGINGS:
                return 6.0;
            case NETHERITE_BOOTS:
                return 3.0;
            default:
                return 0.0;
        }
    }


    public double damage(ItemStack item) {
        Material material = item.getType();
        switch (material) {
            case WOODEN_SWORD:
                return 4.0;
            case STONE_SWORD:
                return 5.0;
            case IRON_SWORD:
                return 6.0;
            case GOLDEN_SWORD:
                return 4.0;
            case DIAMOND_SWORD:
                return 7.0;
            case NETHERITE_SWORD:
                return 8.0;
            case WOODEN_AXE:
                return 7.0;
            case STONE_AXE:
                return 9.0;
            case IRON_AXE:
                return 9.0;
            case GOLDEN_AXE:
                return 7.0;
            case DIAMOND_AXE:
                return 9.0;
            case NETHERITE_AXE:
                return 10.0;
            case WOODEN_PICKAXE:
                return 2.0;
            case STONE_PICKAXE:
                return 3.0;
            case IRON_PICKAXE:
                return 4.0;
            case GOLDEN_PICKAXE:
                return 2.0;
            case DIAMOND_PICKAXE:
                return 5.0;
            case NETHERITE_PICKAXE:
                return 6.0;
            case WOODEN_SHOVEL:
                return 2.5;
            case STONE_SHOVEL:
                return 3.5;
            case IRON_SHOVEL:
                return 4.5;
            case GOLDEN_SHOVEL:
                return 2.5;
            case DIAMOND_SHOVEL:
                return 5.5;
            case NETHERITE_SHOVEL:
                return 6.5;
            case WOODEN_HOE:
                return 1.0;
            case STONE_HOE:
                return 1.0;
            case IRON_HOE:
                return 1.0;
            case GOLDEN_HOE:
                return 1.0;
            case DIAMOND_HOE:
                return 1.0;
            case NETHERITE_HOE:
                return 1.0;
            case FISHING_ROD:
                return 0.0;
            case BOW:
                return 0.0;
            case CROSSBOW:
                return 0.0;
            case TRIDENT:
                return 9.0;
            case FLINT_AND_STEEL:
                return 0.0;
            case SHEARS:
                return 0.0;
            default:
                return 0.0;
        }
    }

    public boolean enchantment(ItemStack itemStack) {
        Material material=itemStack.getType();
        if (material.equals(Material.WOODEN_SWORD)) return true;
        if (material.equals(Material.STONE_SWORD)) return true;
        if (material.equals(Material.IRON_SWORD)) return true;
        if (material.equals(Material.GOLDEN_SWORD)) return true;
        if (material.equals(Material.DIAMOND_SWORD)) return true;
        if (material.equals(Material.NETHERITE_SWORD)) return true;
        if (material.equals(Material.WOODEN_AXE)) return true;
        if (material.equals(Material.STONE_AXE)) return true;
        if (material.equals(Material.IRON_AXE)) return true;
        if (material.equals(Material.GOLDEN_AXE)) return true;
        if (material.equals(Material.DIAMOND_AXE)) return true;
        if (material.equals(Material.NETHERITE_AXE)) return true;
        if (material.equals(Material.WOODEN_PICKAXE)) return true;
        if (material.equals(Material.STONE_PICKAXE)) return true;
        if (material.equals(Material.IRON_PICKAXE)) return true;
        if (material.equals(Material.GOLDEN_PICKAXE)) return true;
        if (material.equals(Material.DIAMOND_PICKAXE)) return true;
        if (material.equals(Material.NETHERITE_PICKAXE)) return true;
        if (material.equals(Material.WOODEN_SHOVEL)) return true;
        if (material.equals(Material.STONE_SHOVEL)) return true;
        if (material.equals(Material.IRON_SHOVEL)) return true;
        if (material.equals(Material.GOLDEN_SHOVEL)) return true;
        if (material.equals(Material.DIAMOND_SHOVEL)) return true;
        if (material.equals(Material.NETHERITE_SHOVEL)) return true;
        if (material.equals(Material.WOODEN_HOE)) return true;
        if (material.equals(Material.STONE_HOE)) return true;
        if (material.equals(Material.IRON_HOE)) return true;
        if (material.equals(Material.GOLDEN_HOE)) return true;
        if (material.equals(Material.DIAMOND_HOE)) return true;
        if (material.equals(Material.NETHERITE_HOE)) return true;
        if (material.equals(Material.FISHING_ROD)) return true;
        if (material.equals(Material.BOW)) return true;
        if (material.equals(Material.CROSSBOW)) return true;
        if (material.equals(Material.TRIDENT)) return true;
        if (material.equals(Material.FLINT_AND_STEEL)) return true;
        if (material.equals(Material.SHEARS)) return true;
        if (material.equals(Material.LEATHER_BOOTS)) return true;
        if (material.equals(Material.LEATHER_LEGGINGS)) return true;
        if (material.equals(Material.LEATHER_CHESTPLATE)) return true;
        if (material.equals(Material.LEATHER_HELMET)) return true;
        if (material.equals(Material.IRON_BOOTS)) return true;
        if (material.equals(Material.IRON_LEGGINGS)) return true;
        if (material.equals(Material.IRON_CHESTPLATE)) return true;
        if (material.equals(Material.IRON_HELMET)) return true;
        if (material.equals(Material.GOLDEN_BOOTS)) return true;
        if (material.equals(Material.GOLDEN_LEGGINGS)) return true;
        if (material.equals(Material.GOLDEN_CHESTPLATE)) return true;
        if (material.equals(Material.GOLDEN_HELMET)) return true;
        if (material.equals(Material.DIAMOND_BOOTS)) return true;
        if (material.equals(Material.DIAMOND_LEGGINGS)) return true;
        if (material.equals(Material.DIAMOND_CHESTPLATE)) return true;
        if (material.equals(Material.DIAMOND_HELMET)) return true;
        if (material.equals(Material.NETHERITE_BOOTS)) return true;
        if (material.equals(Material.NETHERITE_LEGGINGS)) return true;
        if (material.equals(Material.NETHERITE_CHESTPLATE)) return true;
        if (material.equals(Material.NETHERITE_HELMET)) return true;
        return false;
    }

    public String type(ItemStack i) {
        String type=i.getType().toString();
        if (type.contains("SWORD")) return "검";
        if (type.contains("PICKAXE")) return "곡괭이";
        if (type.contains("HOE")) return "괭이";
        if (type.contains("AXE")) return "도끼";
        if (type.contains("SHOVEL")) return "삽";
        if (type.contains("HELMET")) return "헬멧";
        if (type.contains("CHESTPLATE")) return "흉갑";
        if (type.contains("LEGGINGS")) return "레깅스";
        if (type.contains("BOOTS")) return "부츠";
        if (type.contains("TRIDENT")) return "삼지창";
        if (type.contains("BOW")) return "활";
        if (type.contains("CROSSBOW")) return "쇠뇌";
        if (type.contains("FISHING_ROD")) return "낚시대";
        return type;
    }

}
