package com.mlv4.mlv4.Achievements;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.mlv4.mlv4.BattleRoyalePlayer;

public class BobCrafter extends BRAchievement {

  public BobCrafter() {
    super(
      "Bob le bricoleur",
      new ArrayList<String> (Arrays.asList("Equipez-vous de pioche/hache/houe/pelle en bois")),
      1,
      50,
      new ItemStack(Material.IRON_INGOT, 10), new ItemStack(Material.DIAMOND, 4)
    );
  }

  @Override
  public boolean isAchievable(BattleRoyalePlayer by) {
    PlayerInventory inv = by.getPlayer().getInventory();
    return
      inv.contains(Material.WOODEN_AXE)
      && inv.contains(Material.WOODEN_PICKAXE)
      && inv.contains(Material.WOODEN_SHOVEL)
      && inv.contains(Material.WOODEN_HOE);
  }
}
