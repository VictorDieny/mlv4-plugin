package com.mlv4.mlv4.Powers.Voyageur;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Powers.Power;

public class Enderman extends Power {

  public Enderman() {
    super(Material.ENDER_EYE, "Enderman", new String[] {"Reçoit une ender pearl", "1 pearl max dans l'inventaire", "En cas de surplus, Speed II / 5s", "Intervalle : 2 min"}, 120, 0);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {
    if (super.use(by)) {
      if (by.getPlayer().getInventory().contains(Material.ENDER_PEARL)) {
        by.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5*20, 1));
      }
      else {
        by.getPlayer().getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
      }
      
      return true;
    }
    else return false;
  }
  
}
