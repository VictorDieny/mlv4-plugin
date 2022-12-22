package com.mlv4.mlv4.Achievements;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.mlv4.mlv4.BattleRoyalePlayer;

public class Famine extends BRAchievement {

  public Famine() {
    super(
      "Famine !!!!",
      new ArrayList<String> (Arrays.asList("Ayez très, très, faim (0 bouffe)")),
      3,
      200,
      new ItemStack(Material.GOLDEN_CARROT, 8), new ItemStack(Material.GOLDEN_APPLE, 3));
  }

  @Override
  public boolean isAchievable(BattleRoyalePlayer by) {
    return
      by.getPlayer().getFoodLevel() == 0;
  }
}
