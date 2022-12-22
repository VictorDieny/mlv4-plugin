package com.mlv4.mlv4.Achievements;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.mlv4.mlv4.BattleRoyalePlayer;

public class MarteauPiqueur extends BRAchievement {

  public MarteauPiqueur() {
    super(
      "Marteau-piqueur",
      new ArrayList<String> (Arrays.asList("Creusez, creusez, creusez... (64 cobble)")),
      2,
      80,
      new ItemStack(Material.DIAMOND_PICKAXE, 1), new ItemStack(Material.TNT, 3));
  }

  @Override
  public boolean isAchievable(BattleRoyalePlayer by) {
    PlayerInventory inv = by.getPlayer().getInventory();
    return
      inv.contains(Material.COBBLESTONE, 64);
  }
}
