package com.mlv4.mlv4.Achievements;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.mlv4.mlv4.BattleRoyalePlayer;

public class Risky extends BRAchievement {

  public Risky() {
    super(
      "Risqué !",
      new ArrayList<String> (Arrays.asList("Être en glowing avec 3 cœurs", "ou moins est dangereux !")),
      3,
      200,
      new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1)
    );
  }

  @Override
  public boolean isAchievable(BattleRoyalePlayer by) {
    return
      by.getPlayer().getHealth() <= 6
      && by.getPlayer().hasPotionEffect(PotionEffectType.GLOWING);

  }
}
