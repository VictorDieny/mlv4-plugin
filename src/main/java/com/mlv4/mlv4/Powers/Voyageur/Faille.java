package com.mlv4.mlv4.Powers.Voyageur;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Powers.Power;

public class Faille extends Power {

  public Faille() {
    super(Material.NETHER_STAR, "Faille", new String[] {"TP en hauteur (+70 blocs)", "Slow Falling 20s", "Coût : 3 levels", "Intervalle : 2 min"}, 120, 3);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {
    if (super.use(by)) {
      by.getPlayer().teleport(by.getPlayer().getLocation().add(new Vector(0, 70, 0)));
      by.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20*20, 0));
      return true;
    }
    else return false;
  }
  
}
