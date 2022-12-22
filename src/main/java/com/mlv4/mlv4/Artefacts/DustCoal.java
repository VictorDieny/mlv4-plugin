package com.mlv4.mlv4.Artefacts;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Game;


public class DustCoal extends Artefact {

  private static final String displayName = "Charbon Poussiéreux"; 
  private static final String[] lore = {
    "Issu des entrailles du monde",
    "Emettant une poussière d'une densité",
    "qui aveugle vos ennemis environnants",
    "il vous protège tout de même de ses effets"
  };


  public DustCoal() {
    super(Material.COAL, displayName, lore);
  }

  @Override
  public boolean use(BattleRoyalePlayer by, Game game) {
    Location loc = by.getPlayer().getLocation();
    int i = 0;
    for (Player p : game.getPlayers()) {
      if (p.getLocation().distance(loc) < 25 ) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
        i++;
      }
    }
    if (i==0) by.getPlayer().sendMessage("§cHélas, personne n'a été perturbé par les particules du charbon poussiéreux");
    else by.getPlayer().sendMessage("&6Le charbon poussiéreux a attaqué §b" + i + " joueurs §6!");
    return true;
  }
}