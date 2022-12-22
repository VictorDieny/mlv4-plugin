package com.mlv4.mlv4.Powers.Chaman;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Powers.Power;

public class Clairvoyance extends Power {

  public Clairvoyance() {
    super(Material.GOLD_NUGGET, "Clairvoyance", new String[] {"Glowing 5s à tous les autres joueurs", "(sauf sorcier)", "Intervalle : 1 min 30s"}, 120, 0);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {
    if (super.use(by)) {
      for (BattleRoyalePlayer p : by.getCurrentGame().players) {
        if (p != by) {
          p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 5*20, 0));
        }
      }
      
      return true;
    }
    else return false;
  }
  
}
