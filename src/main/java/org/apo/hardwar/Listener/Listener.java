package org.apo.hardwar.Listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.md_5.bungee.api.chat.*;
import org.apo.hardwar.System.Enchantable;
import org.apo.hardwar.HardWar;
import org.apo.hardwar.System.ItemNameTranslator;
import org.apo.hardwar.GUI.Upgrade;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class Listener implements org.bukkit.event.Listener {

    Enchantable enchantable=new Enchantable();
    HardWar hardWar=HardWar.Instance;

    @EventHandler
    public void Event(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (a.equals(Action.RIGHT_CLICK_AIR)) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                String displayName = item.getItemMeta().getDisplayName();
                if (p.getCooldown(item.getType()) == 0) {
                    if (displayName.equals(ChatColor.AQUA+"마컴") && item.getType().equals(Material.COMPASS)) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (p.getInventory().getItemInMainHand().getType() != Material.COMPASS) {
                                    p.setCompassTarget(p.getWorld().getSpawnLocation());
                                    p.setCooldown(item.getType(), 20*60);
                                    this.cancel();
                                    return;
                                }
                                Player nearestPlayer = null;
                                double closestDistance = Double.MAX_VALUE;

                                for (Entity entity : p.getWorld().getEntities()) {
                                    if (!(entity instanceof Player) || entity.equals(p) || !(entity instanceof Villager)) {
                                        continue;
                                    }

                                    double distance = entity.getLocation().distanceSquared(p.getLocation());
                                    if (distance < closestDistance) {
                                        closestDistance = distance;
                                        nearestPlayer = (Player) entity;
                                    }
                                }
                                if (nearestPlayer != null) {
                                    Location targetLocation = nearestPlayer.getLocation();
                                    p.setCompassTarget(targetLocation);
                                } else {
                                    p.sendMessage("추적 대상을 찾을 수 없습니다.");
                                }
                            }
                        }.runTaskTimer(HardWar.Instance, 2, 2L);
                    }
                }
            }
        }
    }

    public static final Map<String, String> name = new HashMap<>();
    @EventHandler
    public void join(PlayerJoinEvent e) {
        e.getPlayer().setMaxHealth(40.0);
        if (!e.getPlayer().hasPlayedBefore()) e.getPlayer().setHealth(40.0);
        if (!e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) hardWar.getConfig().set(e.getPlayer().getName(), "Alive");
        name.put(String.valueOf(e.getPlayer().getUniqueId()), e.getPlayer().getName());
        hardWar.saveConfig();
    }

    public String nick(String nick) {
        return name.get(nick);
    }


    @EventHandler
    public void Attack(EntityDamageByEntityEvent e) {
        Entity entity = e.getDamager();
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (p.isBlocking()) {
                ItemStack item = p.getInventory().getItemInOffHand();
                if (item.getItemMeta().getDisplayName().contains("거울")) {
                    if (p.getCooldown(Material.SHIELD) == 0) {
                        if (entity instanceof LivingEntity) {
                            e.setCancelled(true);
                            ((LivingEntity) entity).damage(e.getDamage());
                            if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                                p.setCooldown(Material.SHIELD, 90 * 20);
                            }
                        }
                    }
                }
            }
        }

        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            if (itemInHand.hasItemMeta()) {
                ItemMeta meta = itemInHand.getItemMeta();
                if (meta.hasLore()) {
                    for (String lore : meta.getLore()) {
                        if (lore.contains("빙결")) {
                            if (e.getEntity() instanceof LivingEntity) {
                                LivingEntity targetEntity = (LivingEntity) e.getEntity();
                                targetEntity.setFreezeTicks(500);
                                break;
                            }
                        }
                    }
                }
            }
            if (Stage(itemInHand)==10) {
                ((Player) e.getDamager()).setNoDamageTicks(0);
            }
        }
    }

    @EventHandler
    public void Inv(PlayerSwapHandItemsEvent e) {
        if (e.getPlayer().isSneaking()) {
            e.setCancelled(true);
            Upgrade upgrade=new Upgrade();
            upgrade.Inv(e.getPlayer());
        }
    }

    @EventHandler
    public void Upgr(InventoryOpenEvent e) {
        if (e.getInventory().getType().equals(InventoryType.ANVIL)) {
            e.setCancelled(true);
            Upgrade upgrade=new Upgrade();
            upgrade.Inv((Player) e.getPlayer());
        }
    }
    @EventHandler
    public void Uclick(InventoryClickEvent e) {
        Inventory i=e.getInventory();
        if (e.getView().getTitle().contains("강화")){
            e.setCancelled(true);
            if (e.isShiftClick()) {
                if (e.isLeftClick()) {
                    if (e.getCurrentItem()!=null){
                        ItemMeta itemMeta=e.getCurrentItem().getItemMeta();
                        if (enchantable.enchantment(e.getCurrentItem())){
                            ItemStack item = e.getCurrentItem().clone();
                            item.setItemMeta(itemMeta);
                            e.getCurrentItem().setAmount(0);
                            i.setItem(13, item);
                        }
                    }
                }
                if (e.isRightClick()) {
                    if (i.getItem(13)!=null){
                        ItemMeta itemMeta=e.getCurrentItem().getItemMeta();
                        ItemStack iu = i.getItem(13).clone();
                        iu.setItemMeta(itemMeta);
                        e.getWhoClicked().getInventory().addItem(iu);
                        i.clear(13);
                    }
                }
            } else if (e.isLeftClick()) {
                if (e.getCurrentItem()!=null){
                    if (e.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;
                    if (enchantable.enchantment(e.getCurrentItem())) {
                        Random random = new Random();
                        if (e.getCurrentItem() == null) return;
                        ItemStack item = e.getCurrentItem();
                        ItemMeta meta = item.getItemMeta();
                        Player player = (Player) e.getWhoClicked();



                        if (!player.getInventory().contains(Material.ECHO_SHARD)) {
                            return;
                        }

                        ItemStack d=new ItemStack(Material.ECHO_SHARD,1);
                        player.getInventory().removeItem(d);
                        e.getWhoClicked().getInventory().removeItem(d);

                        int pp=random.nextInt(100);
                        int stage = Stage(item);
                        double suc = p(stage);
                        double br = 100 - suc;

                        if (Stage(item)==10) {
                            if (i.getItem(13)!=null){
                                ItemMeta itemMeta=e.getCurrentItem().getItemMeta();
                                ItemStack iu = i.getItem(13).clone();
                                iu.setItemMeta(itemMeta);
                                e.getWhoClicked().getInventory().addItem(iu);
                                i.clear(13);
                            }
                        }

                        if (Stage(item)==9) {
                            TextComponent text=new TextComponent(ChatColor.YELLOW+"전설의 장비가 탄생했습니다!");

                            Bukkit.spigot().broadcast(text);

                            int ppp=random.nextInt(1000);
                            List<String> lore= meta.getLore();
                            if (lore==null) lore=new ArrayList<>();
                            lore.removeIf(line-> line.contains("★"));
                            lore.removeIf(line-> line.contains(":"));
                            if (ppp >= 910 && meta.hasEnchants()) {
                                //과부화
                                lore.add(ChatColor.BLUE+"★★★★★★★★★★");
                                meta.setDisplayName(ChatColor.BLUE + player.getName() + "의 " + name(String.valueOf(item.getType())));
                                Map<Enchantment, Integer> enchants=meta.getEnchants();
                                for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
                                    Enchantment enchantment = entry.getKey();
                                    int lv = entry.getValue();
                                    int ml = lv * 2;
                                    meta.removeEnchant(enchantment);
                                    meta.addEnchant(enchantment, ml, true);
                                }
                            } else if (ppp<= 20) {
                                //저주 받은 인첸트
                                lore.add(ChatColor.RED+"★★★★★★★★★★");
                                meta.setDisplayName(ChatColor.RED +"저주받은 "+ player.getName() + "의 " + name(String.valueOf(item.getType())));
                            } else  {
                                //일반
                                lore.add(ChatColor.YELLOW+"★★★★★★★★★★");
                                meta.setDisplayName(ChatColor.YELLOW + player.getName() + "의 " + name(String.valueOf(item.getType())));
                                if (enchantable.sword(item)) {
                                    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_SPEED.name(), 1000, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                                    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), enchantable.damage(item), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                                }
                                if (enchantable.armor(item)) {
                                    if (enchantable.Helmet(item)) meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), enchantable.getArmorDefense(item.getType())+2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                                    if (enchantable.Chestplate(item)) meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), enchantable.getArmorDefense(item.getType())+2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
                                    if (enchantable.Leggings(item)) meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), enchantable.getArmorDefense(item.getType())+2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
                                    if (enchantable.Boots(item)) meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), enchantable.getArmorDefense(item.getType())+2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));

                                    if (item.getType().equals(Material.NETHERITE_HELMET)) {
                                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                                        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                                    } else if (item.getType().equals(Material.NETHERITE_CHESTPLATE)) {
                                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                                        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                                    } else if (item.getType().equals(Material.NETHERITE_LEGGINGS)) {
                                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                                        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                                    } else if (item.getType().equals(Material.NETHERITE_BOOTS)) {
                                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                                        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                                    }
                                }

                                if (item.getItemMeta().hasEnchants()) {
                                    Map<Enchantment,Integer> en=item.getItemMeta().getEnchants();

                                }
                            }
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            return;
                        }

                        if (p(Stage(item))>=pp) {
                            List<String> lore = meta.getLore();
                            if (lore == null) lore = new ArrayList<>();
                            lore.removeIf(line -> line.contains("★"));
                            lore.removeIf(line -> line.contains("%"));
                            lore.add(ChatColor.YELLOW + StageS(item));
                            lore.add(ChatColor.GREEN + "§l -성공 확률: " + p(Stage(item)) + "%");
                            br = 100 - p(Stage(item))-b(Stage(item));
                            lore.add(ChatColor.RED + "§l -실패 확률: " + br + "%");
                            lore.add(ChatColor.GRAY + "§l -파괴 확률: " + b(Stage(item)) + "%");
                            meta.setLore(lore);
                        } else if (b(Stage(item))>=pp) {
                            item.setAmount(0);
                            Bukkit.broadcastMessage(ChatColor.RED+"누군가의 장비가 파괴되었습니다!");
                        }

                        item.setItemMeta(meta);
                    }
                }
            }
        }
    }

    public int Stage(ItemStack i) {
        ItemMeta m=i.getItemMeta();
        int stage = 0;
        if (m!=null){
            if (m.hasLore()) {
                List<String> lore = m.getLore();
                int lv = 0;
                for (String line : lore) {
                    for (char c : line.toCharArray()) {
                        if (c == '★') {
                            lv++;
                            stage = lv;
                        }
                    }
                }
                if (stage >= 10) {
                    return 10;
                }
                return stage;
            }
        }
        return 0;
    }

    public String StageS(ItemStack i) {
        ItemMeta m=i.getItemMeta();
        if (Stage(i)==0) return "★☆☆☆☆☆☆☆☆☆";
        StringBuilder stars = new StringBuilder();
        for (int j = 0; j <= Stage(i); j++) {
            stars.append("★");
        }
        for (int k=stars.length();k<10;k++) {
            stars.append("☆");
        }

        return stars.toString();
    }

    public int p(int x) {
        int y = -x+10;
        return y*10;
    }
    public int b(int x) {
        int y=0;
        if (x>=5) {
            y=10-x;
            return 2*y;
        }
        return y;
    }


    public String name(String n) {
        ItemNameTranslator in=new ItemNameTranslator();
        n=in.translate(n);
        return n;
    }

    @EventHandler
    public void Colse(InventoryCloseEvent e) {
        if (e.getView().getTitle().contains("강화")) {
            Inventory i=e.getInventory();
            if (i.getItem(13)!=null){
                ItemStack iu = i.getItem(13).clone();
                e.getPlayer().getInventory().addItem(iu);
            }
        }
    }



    @EventHandler
    public void Death(PlayerDeathEvent e) {
        hardWar.getConfig().set(((e.getEntity().getName())), "Death");
        hardWar.saveConfig();

        ItemStack p=new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta= (SkullMeta) p.getItemMeta();
        meta.setOwningPlayer(e.getEntity());
        meta.setDisplayName(e.getEntity().getName()+"의 머리");
        p.setItemMeta(meta);

        e.getDrops().add(p);

        if (e.getEntity().getInventory().contains(Material.DRAGON_EGG)) {
            e.setKeepInventory(true);
            e.getDrops().clear();
        } else  {
            e.setDeathMessage(ChatColor.RED+"플레이어가 죽었습니다!");
        }
    }

    @EventHandler
    public void cr(CraftItemEvent e) {
        if (e.getInventory().getResult().getType().equals(Material.END_CRYSTAL) || e.getInventory().getResult().getType().equals(Material.RESPAWN_ANCHOR)){
            e.getInventory().getResult().getItemMeta().setMaxStackSize(1);
        }
    }


    @EventHandler
    public void en(EnchantItemEvent e) {
        Player p=e.getEnchanter();
        ItemStack itemStack=e.getItem();
        if (enchantable.sword(itemStack)) {
            if (e.getEnchantsToAdd().get(Enchantment.FIRE_ASPECT) == null) {
                int ran = (int)(Math.random() * 100.0 % 3.0);
                if (ran > 6) {
                    ArrayList lore;
                    ItemStack item;
                    ItemMeta meta;
                    lore = new ArrayList();
                    lore.add("§7빙결");
                    item = e.getItem();
                    meta = item.getItemMeta();
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                }
            }
        }
    }

    @EventHandler
    public void Respawn(PlayerRespawnEvent e) {
        if (e.getPlayer().getInventory().contains(Material.DRAGON_EGG)) {
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
            e.getPlayer().playEffect(EntityEffect.TOTEM_RESURRECT);
            e.getPlayer().setHealth(40.0);
            ItemStack d=new ItemStack(Material.DRAGON_EGG,1);
            e.getPlayer().getInventory().removeItem(d);
            e.getPlayer().sendMessage(ChatColor.RED+"드래곤알이 당신의 목숨을 대신했습니다.");
            hardWar.getConfig().set(nick(String.valueOf(e.getPlayer().getUniqueId())), "Alive");
            hardWar.saveConfig();
        }
    }

    @EventHandler
    public void portals(PlayerPortalEvent e) {
        World w=e.getTo().getWorld();
        if (w.equals(World.Environment.NORMAL)) {
            e.setTo(e.getTo().multiply(0.25));
        } else if (w.equals(World.Environment.NETHER)) {
            e.setTo(e.getTo().multiply(4));
        }
    }

    @EventHandler
    public void jump(PlayerJumpEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getBoots()!=null) {
            if (Stage(p.getInventory().getBoots()) == 10) {
                p.setAllowFlight(true);
            }
        }
    }


    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getBoots()!=null) {
            if (Stage(player.getInventory().getBoots()) == 10) {
                {
                    player.setFlying(true);
                    Vector vector = player.getLocation().getDirection().multiply(0.5).setY(1);
                    player.setVelocity(vector);
                    player.setFlying(false);
                    event.setCancelled(true);
                    player.setAllowFlight(false);
                    player.setFallDistance(0);
                }
            }
        }
        player.setFlying(false);
        if (!player.getGameMode().equals(GameMode.CREATIVE)) player.setFlying(false);
        player.setFallDistance(0);
    }
}
