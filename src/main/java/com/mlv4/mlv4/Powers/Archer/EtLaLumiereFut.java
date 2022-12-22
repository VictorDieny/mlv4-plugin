package com.mlv4.mlv4.Powers.Archer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Powers.Power;

public class EtLaLumiereFut extends Power {

  public EtLaLumiereFut() {
    super(Material.GLOWSTONE_DUST, "Et la lumière fut", new String[] {"Give 3 spectral arrows", "15 spectral arrows max dans l'inventaire", "En cas de surplus, give firework rocket", "Intervalle : 1 min"}, 60, 0);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {
    if (super.use(by)) {
      if (by.getPlayer().getInventory().contains(Material.SPECTRAL_ARROW, 15)) {
        by.getPlayer().getInventory().addItem(new ItemStack(Material.FIREWORK_ROCKET));
      }
      else {
        by.getPlayer().getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW, 3));
      }
      return true;
    }
    else return false;
  }
  
}
