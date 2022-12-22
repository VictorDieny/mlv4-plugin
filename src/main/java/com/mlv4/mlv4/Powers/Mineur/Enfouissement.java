package com.mlv4.mlv4.Powers.Mineur;

import org.bukkit.Material;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Artefacts.Mine;
import com.mlv4.mlv4.Powers.Power;

public class Enfouissement extends Power {

  public Enfouissement() {
    super(Material.GUNPOWDER, "Enfouissement", new String[] {"Dépose une mine", "Coût : 3 levels", "Intervalle : 3 min"}, 3*60, 2);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {
    return super.use(by) && new Mine().use(by, by.getCurrentGame());
  }
  
}
