package org.apo.hardwar.Commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.chat.ComponentSerializer;
import org.apo.hardwar.HardWar;
import org.apo.hardwar.System.Enchantable;
import org.apo.hardwar.System.ItemNameTranslator;
import org.apo.hardwar.System.Recipe;
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
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) return false;
        if (!(commandSender.getName().contains("APO2073"))) return false;
        Player p=((Player) commandSender).getPlayer();
        TextComponent text=new TextComponent(ChatColor.YELLOW+"전설의 장비가 탄생했습니다!");
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(p.getName()+"의 "
                +enchantable.type(p.getInventory().getItemInMainHand())).create()));
        Bukkit.spigot().broadcast(text);
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
