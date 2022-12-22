package com.mlv4.mlv4.Powers.Archer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Powers.Power;

public class Rafale extends Power {

  public Rafale() {
    super(Material.CROSSBOW, "Rafale", new String[] {"Remplit la barre d'inventaire", "d'arbalètes chargées", "Coût : 6 levels", "Intervalle : 2 min"}, 120, 6);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {

    ItemStack loadedCrossbow = new ItemStack(Material.CROSSBOW);
    CrossbowMeta lcMeta = (CrossbowMeta) loadedCrossbow.getItemMeta();
    lcMeta.addChargedProjectile(new ItemStack(Material.ARROW, 3));
    loadedCrossbow.setItemMeta(lcMeta);
    

    if (super.use(by)) {
      for (int i = 0; i < 9; i++) {
        if (by.getPlayer().getInventory().getItem(i) == null)
          by.getPlayer().getInventory().setItem(i, loadedCrossbow);
      }
      return true;
    }
    else return false;
  }
  
}
