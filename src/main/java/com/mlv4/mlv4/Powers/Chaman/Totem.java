package com.mlv4.mlv4.Powers.Chaman;


import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Witch;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Plugin;
import com.mlv4.mlv4.Powers.Power;

public class Totem extends Power {

  public Totem() {
    super(Material.HONEYCOMB, "Totem", new String[] {"Invoque un totem avec glowing", "Invisibility tant que le totem vit in zone", "Coût : 8 levels", "Intervalle : 2 min"}, 3*60, 8);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {
    if (super.use(by) && by.getCurrentGame().zone.isInZone(by.getPlayer().getLocation())) {
      Witch totem = (Witch) by.getPlayer().getWorld().spawnEntity(by.getPlayer().getLocation(), EntityType.WITCH);
      totem.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0));
      totem.setAI(false);
      totem.setCustomName(by.getPlayer().getName());

      by.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
      for (BattleRoyalePlayer p : by.getCurrentGame().players) {
        if (p != by) {
          p.getPlayer().hidePlayer(Plugin.getInstance(), by.getPlayer());
        }
      }      
      
      return true;
    }
    else return false;
  }
  
}
