package com.mlv4.mlv4.Artefacts;

import org.bukkit.Material;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Game;

public class Accelereacteur extends Artefact{

  private static final String displayName = "Accéléréacteur temporel"; 
  private static final String[] lore = {
    "Vous connaissiez sans doute la vitesse Île-Marique,",
    "Mais qu'en est il de sa dérivée, l'accéléraction ?",
    "L'accéléréacteur augmente la vitesse du Monde",
    "La Zone comme les joueurs gagnent une vitesse",
    "Similaire à l'effet de lunettes de vitesse",
    "-1 UIM (Unité Île-Marique) en moyenne"
  };

  public Accelereacteur() {
    super(Material.CLOCK, displayName, lore);
  }

  @Override
  public boolean use(BattleRoyalePlayer by, Game game) {
    if (game.zone.side >= 15) {
      game.zone.progress(6);
    }
    else {
      game.zone.progress(game.zone.side - 10);
    }
    by.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
    by.getPlayer().sendMessage("§6Accccccéléraaactioooooon !!!");
    return true;
  }
  
}
