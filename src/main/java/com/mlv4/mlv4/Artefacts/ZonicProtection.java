package com.mlv4.mlv4.Artefacts;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Game;


public class ZonicProtection extends Artefact {

  private Random random = new Random();
  private static final String displayName = "Carapace zonique";
  private BukkitTask cancelProtection;
  private static final String[] lore = {
    "Dotée des pouvoirs puisés chez le Wither lui-même",
    "Vous protège de tous les effets nocifs de la zone",
    "Et ce pendant une durée indéterminée et aléatoire",
    "comprise entre 20s et 30s dès sa consommation"
  };


  public ZonicProtection() {
    super(Material.WITHER_ROSE, displayName, lore);
  }

  @Override
  public boolean use(BattleRoyalePlayer player, Game game) {
    int length = (random.nextInt(10) + 20) * 20;
    final BattleRoyalePlayer p = player;
    p.getPlayer().removePotionEffect(PotionEffectType.WITHER);
    game.zoneProtectedPlayers.add(p);
    p.getPlayer().sendMessage("§6Vous voilà protégé des effets de la zone pendant §b" + length/20 + " secondes §6!");
    final Game g = game;
    this.cancelProtection = new BukkitRunnable() {
      @Override
      public void run() {
        cancelProtection.cancel();
        g.zoneProtectedPlayers.remove(p);
        p.getPlayer().sendMessage("§cAttention, les effets de la protection zonique sont désormais terminés.");
      }
    }.runTaskTimer(game.plugin, length, -1);
    return true;
  }
}