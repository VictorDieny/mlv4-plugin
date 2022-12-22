package com.mlv4.mlv4.Achievements;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.mlv4.mlv4.BattleRoyalePlayer;

public class ImACow extends BRAchievement {

  public ImACow() {
    super(
      "Je suis une vache",
      new ArrayList<String> (Arrays.asList("Revêtez-vous d'une armure complète en cuir")),
      1,
      35,
      new ItemStack(Material.COOKED_BEEF, 10), new ItemStack(Material.GOLDEN_HOE, 1));
  }

  @Override
  public boolean isAchievable(BattleRoyalePlayer by) {
    PlayerInventory inv = by.getPlayer().getInventory();
    return
      inv.getBoots() instanceof ItemStack && inv.getBoots().getType() == Material.LEATHER_BOOTS
      && inv.getLeggings() instanceof ItemStack && inv.getLeggings().getType() == Material.LEATHER_LEGGINGS
      && inv.getChestplate() instanceof ItemStack && inv.getChestplate().getType() == Material.LEATHER_CHESTPLATE
      && inv.getHelmet() instanceof ItemStack && inv.getHelmet().getType() == Material.LEATHER_HELMET;
  }
}
