package com.mlv4.mlv4.Artefacts;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Game;

public class Mine extends Artefact {

  private static final String displayName = "Mine"; 
  private static final String[] lore = {
    "C'est le cœur des magma cubes",
    "Qui est maintenu dans cet outil",
    "Invisible et impalpable,",
    "Il se déchaîne et explose",
    "Lorsqu'un ennemi marche dessus"
  };
  public Location location;
  public BattleRoyalePlayer owner;
  public Game game;


  public Mine() {
    super(Material.MAGMA_CREAM, displayName, lore);
  }

  @Override
  public boolean use(BattleRoyalePlayer by, Game game) {
    if (game.zone.isInZone(by.getPlayer().getLocation())) {
      this.location = by.getPlayer().getLocation();
      this.owner = by;
      game.mines.add(this);
      by.getPlayer().sendMessage("§6Un joli feu d'artifice se prépare : La mine est installée !");
      this.game = game;
      return true;
    }
    else {
      by.getPlayer().sendMessage("§4La mine est uniquement installable à l'intérieur de la zone");
      return false;
    }

  }
  public boolean isInRange(Location loc) {
    return this.location.distance(loc) < 3;
  }

  public void explode(BattleRoyalePlayer on) {
    this.game.mines.remove(this);
    this.location.getWorld().createExplosion(this.location, 2.0F, true, true);
    owner.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
    on.getPlayer().sendMessage("§cBOOOOOM ! §dTu remercieras " + this.owner.getPlayer().getName() + " :)");
    this.owner.getPlayer().sendMessage("§b" + on.getPlayer().getName() + "§a sent un peu le cramé après avoir marché sur ta mine");
  }
  
}
