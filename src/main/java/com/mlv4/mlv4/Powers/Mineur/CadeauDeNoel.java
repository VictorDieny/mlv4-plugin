package com.mlv4.mlv4.Powers.Mineur;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Powers.Power;

public class CadeauDeNoel extends Power {

  public CadeauDeNoel() {
    super(Material.EMERALD, "Cadeau de Noël !", new String[] {"Dépose une TNT allumée", "Intervalle : 2min"}, 120, 0);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {
    if (super.use(by)) {
      by.getPlayer().getWorld().spawnEntity(by.getPlayer().getLocation(), EntityType.PRIMED_TNT);      
      return true;
    }
    else return false;
  }
  
}
