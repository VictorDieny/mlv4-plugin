package com.mlv4.mlv4.Powers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.ItemBuilder;
import com.mlv4.mlv4.Plugin;

public class Power extends ItemBuilder {

  private int latency;
  private int price;
  public BukkitTask reloadPowerTask;
  public BossBar reloadBar;
  private int ticksFromLastUse = 0;


  public Power(Material material, String displayName, String[] lore, int latencySeconds, int priceLevels) {
    //super(itemStack, displayName, lore, true);
    super(material);
    this.setDisplayName(displayName);
    this.setLore(lore);
    this.enchantSkin();

    this.latency = latencySeconds;
    this.price = priceLevels;
    this.reloadBar = Bukkit.createBossBar("§l" + this.getItemMeta().getDisplayName(), BarColor.BLUE, BarStyle.SOLID);
    
    if (price > 0) {
      this.reloadBar.setStyle(BarStyle.SEGMENTED_6);
      this.reloadBar.setColor(BarColor.RED);
    }
  }
  
  public void reload(BattleRoyalePlayer by) {
    by.getPlayer().getInventory().addItem(this);
    by.getPlayer().sendMessage("§a Le power §b" + this.getItemMeta().getDisplayName() + "§a a été rechargé");
    by.getPlayer().playSound(by.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 0, latency);
  }

  public void stopLifecycle() {
    this.reloadBar.removeAll();
    this.reloadPowerTask.cancel();
  }

  public boolean use(BattleRoyalePlayer by) {
    if (by.getPlayer().getLevel() >= this.price) {
      by.getPlayer().sendMessage("§6Tu utilises le pouvoir §b" + this.getItemMeta().getDisplayName());
      by.getPlayer().giveExpLevels(-this.price);
      this.reloadBar.setVisible(true);
      this.reloadBar.addPlayer(by.getPlayer());
      this.ticksFromLastUse = 0;

      final BattleRoyalePlayer p = by;
      this.reloadPowerTask = new BukkitRunnable() {
        @Override
        public void run() {
          if (ticksFromLastUse < latency*20) {
            ticksFromLastUse += 1;
            reloadBar.setProgress((ticksFromLastUse/20)/(Double.valueOf(latency)));
          }
          else {
            reload(p);
            this.cancel();
            reloadBar.removeAll();
          }
        }
      }.runTaskTimer(Plugin.getInstance(), 0, 1);
      return true;
    }
    else return false;
  }

  public static Power getFromMaterial(Material m) {
    for (Role role: Role.values()) {
      for (Power power : role.getPowers()) {
        if (power.getType() == m)
          return power;
      }
    }
    return null;
  }
}
