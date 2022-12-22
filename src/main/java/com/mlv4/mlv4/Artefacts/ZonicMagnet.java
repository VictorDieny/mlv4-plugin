package com.mlv4.mlv4.Artefacts;

import org.bukkit.Location;
import org.bukkit.Material;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Game;

public class ZonicMagnet extends Artefact {

  private static final String displayName = "Aimant zonique"; 
  private static final String[] lore = {
    "Doté d'une immense force de magnétisme",
    "Permet de mouvoir la direction de la zone",
    "vers votre position actuelle",
    " -- Utilisable uniquement dans la zone"
  };


  public ZonicMagnet() {
    super(Material.IRON_BLOCK, displayName, lore);
  }

  @Override
  public boolean use(BattleRoyalePlayer player, Game game) {
    Location loc = player.getPlayer().getLocation();
    if (game.zone.isInZone(loc)) {
      game.zone.setZoneCenter((int) loc.getX(), (int) loc.getZ());
      player.getPlayer().sendMessage("§6La zone se resserre désormais autour du centre §b" + (int) loc.getX() + " / " + (int) loc.getZ());
      return true;
    }
    else {
      player.getPlayer().sendMessage("§4Veuillez utilisez cette artefact depuis l'interieur de la zone");
      return false;
    }
  }
}