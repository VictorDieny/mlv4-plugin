package com.mlv4.mlv4.Powers.Enchanteur;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Powers.Power;

public class Invocation extends Power {

  public Invocation() {
    super(Material.ROTTEN_FLESH, "Invocation", new String[] {"Invoque 3 zombies", "Intervalle : 1 min"}, 60, 0);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {
    if (super.use(by)) {
      by.getPlayer().getWorld().spawnEntity(by.getPlayer().getLocation().add(2, 0, 0), EntityType.ZOMBIE);
      by.getPlayer().getWorld().spawnEntity(by.getPlayer().getLocation().add(-1, 0, -2), EntityType.ZOMBIE_VILLAGER);
      by.getPlayer().getWorld().spawnEntity(by.getPlayer().getLocation().add(-1, 0, 2), EntityType.HUSK);
      
      return true;
    }
    else return false;
  }
  
}
