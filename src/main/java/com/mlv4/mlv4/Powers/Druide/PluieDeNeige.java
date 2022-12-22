package com.mlv4.mlv4.Powers.Druide;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Powers.Power;

public class PluieDeNeige extends Power {

  private Random random = new Random();

  public PluieDeNeige() {
    super(Material.SNOWBALL, "Pluie de neige", new String[] {"Invoque 10 snowmen", "Coût : 5 levels XP", "Intervalle : 5 min"}, 60*5, 5);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {
    if (super.use(by)) {
      for (int i = 0; i < 10; i++) {
        by.getPlayer().getWorld().spawnEntity(by.getPlayer().getLocation().add(new Vector(random.nextInt(i+1), 0, random.nextInt(i+1))), EntityType.SNOWMAN);
        by.getPlayer().getWorld().spawnEntity(by.getPlayer().getLocation().add(new Vector(random.nextInt(i+1), 3, random.nextInt(i+1))), EntityType.SNOWBALL);
      }
      
      return true;
    }
    else return false;
  }
}
