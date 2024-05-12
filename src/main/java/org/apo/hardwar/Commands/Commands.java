package org.apo.hardwar.Commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ItemTag;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.chat.ComponentSerializer;
import org.apo.hardwar.HardWar;
import org.apo.hardwar.System.Enchantable;
import org.apo.hardwar.System.ItemNameTranslator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Commands implements TabExecutor {
    Enchantable enchantable=new Enchantable();
    HardWar hardWar=HardWar.Instance;
    public String name(String n) {
        ItemNameTranslator in=new ItemNameTranslator();
        n=in.translate(n);
        return n;
    }
    public Commands(JavaPlugin p) {
        p.getCommand("war").setExecutor(this);
        p.getCommand("war").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        if (!(commandSender.getName().contains("APO2073"))) return false;
        Player player=((Player) commandSender).getPlayer();
        {
            ItemStack item = player.getItemInHand();
            ItemMeta meta = item.getItemMeta();
            Random random = new Random();



            int ppp = random.nextInt(1000);
            List<String> lore = meta.getLore();
            if (lore == null) lore = new ArrayList<>();
            lore.removeIf(line -> line.contains("★"));
            lore.removeIf(line -> line.contains(":"));
            if (ppp >= 910 && meta.hasEnchants()) {
                //과부화
                lore.add(ChatColor.BLUE + "★★★★★★★★★★");
                meta.setDisplayName(ChatColor.BLUE + player.getName() + "의 " + name(String.valueOf(item.getType())));
                Map<Enchantment, Integer> enchants = meta.getEnchants();
                for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
                    Enchantment enchantment = entry.getKey();
                    int lv = entry.getValue();
                    int ml = lv * 2;
                    meta.removeEnchant(enchantment);
                    meta.addEnchant(enchantment, ml, true);
                }
            } else if (ppp <= 20) {
                //저주 받은 인첸트
                lore.add(ChatColor.RED + "★★★★★★★★★★");
                meta.setDisplayName(ChatColor.RED + "저주받은 " + player.getName() + "의 " + name(String.valueOf(item.getType())));

            } else {
                //일반
                lore.add(ChatColor.YELLOW + "★★★★★★★★★★");
                meta.setDisplayName(ChatColor.YELLOW + player.getName() + "의 " + name(String.valueOf(item.getType())));
                if (enchantable.sword(item)) {
                    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_SPEED.name(), 1000, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), enchantable.damage(item)*0.65, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                }
                if (enchantable.armor(item)) {
                    if (enchantable.Helmet(item)) meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), enchantable.getArmorDefense(item.getType())*1.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                    if (enchantable.Chestplate(item)) meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), enchantable.getArmorDefense(item.getType())*1.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
                    if (enchantable.Leggings(item)) meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), enchantable.getArmorDefense(item.getType())*1.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
                    if (enchantable.Boots(item)) meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), enchantable.getArmorDefense(item.getType())*1.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));

                    if (item.getType().equals(Material.NETHERITE_HELMET)) {
                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                    } else if (item.getType().equals(Material.NETHERITE_CHESTPLATE)) {
                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
                        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
                    } else if (item.getType().equals(Material.NETHERITE_LEGGINGS)) {
                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
                        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
                    } else if (item.getType().equals(Material.NETHERITE_BOOTS)) {
                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
                        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
                    }
                }

                if (item.getItemMeta().hasEnchants()) {
                    for (Enchantment en : meta.getEnchants().keySet()) {
                        int ml = en.getMaxLevel();
                        meta.addEnchant(en, ml, true);
                    }
                }

                TextComponent text = new TextComponent(ChatColor.YELLOW + "전설의 장비가 탄생했습니다!");
                Bukkit.spigot().broadcast(text);
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
