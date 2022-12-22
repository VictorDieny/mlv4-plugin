package com.mlv4.mlv4.Powers.Druide;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Powers.Power;

public class AppelALaMeute extends Power {

  public AppelALaMeute() {
    super(Material.BONE, "Appel à la meute", new String[] {"Invoque un loup", "Reçoit 10 os", "Intervalle : 2 min"}, 120, 0);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {
    if (super.use(by)) {
      by.getPlayer().getWorld().spawnEntity(by.getPlayer().getLocation(), EntityType.WOLF);
      by.getPlayer().getInventory().addItem(new ItemStack(Material.BONE, 10));
      
      return true;
    }
    else return false;
  }
  
}
