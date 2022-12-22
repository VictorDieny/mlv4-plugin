package com.mlv4.mlv4.Achievements;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.mlv4.mlv4.BattleRoyalePlayer;

public class MainVerte extends BRAchievement {

  public MainVerte() {
    super(
      "Main Verte",
      new ArrayList<String> (Arrays.asList("N'avez-vous pas faim ?", "Cultivez un peu la terre pour faire un pain !")),
      2,
      80,
      new ItemStack(Material.OAK_SAPLING, 30), new ItemStack(Material.GOLDEN_CARROT, 3));
  }

  @Override
  public boolean isAchievable(BattleRoyalePlayer by) {
    PlayerInventory inv = by.getPlayer().getInventory();
    return
      inv.contains(Material.COBBLESTONE, 64);
  }
}
