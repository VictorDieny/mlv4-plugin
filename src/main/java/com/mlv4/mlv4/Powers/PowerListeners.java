package com.mlv4.mlv4.Powers;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Game;
import com.mlv4.mlv4.Plugin;

public class PowerListeners implements Listener {

  @EventHandler
  public void onChamanTotemDeath(EntityDeathEvent e) {

    if (!Plugin.getInstance().cbr.games.isEmpty() && e.getEntityType() == EntityType.WITCH && e.getEntity().hasPotionEffect(PotionEffectType.GLOWING)) {
      BattleRoyalePlayer chamanPlayer = BattleRoyalePlayer.get(Plugin.getInstance().getServer().getPlayer(e.getEntity().getCustomName()));
      if (chamanPlayer != null) {
        chamanPlayer.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
        for (BattleRoyalePlayer p : chamanPlayer.getCurrentGame().players) {
          if (p != chamanPlayer) {
            p.getPlayer().showPlayer(Plugin.getInstance(), chamanPlayer.getPlayer());
          }
        }
      }
    }
  }


  @EventHandler
  public void chaman(PlayerMoveEvent e) {
    BattleRoyalePlayer player = BattleRoyalePlayer.get(e.getPlayer());
    if (player == null) return;
    Game g = player.getCurrentGame();

    if (g != null && player.getRole() == Role.CHAMAN) {
      for (BattleRoyalePlayer p : g.players) {
        if (p.getPlayer().getLocation().distance(e.getTo()) <= 3 && p.getPlayer().getLocation().distance(e.getFrom()) > 3)  {
          p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0));
        }

        else if (p.getPlayer().getLocation().distance(e.getFrom()) <= 3 && p.getPlayer().getLocation().distance(e.getTo()) > 3)  {
          p.getPlayer().removePotionEffect(PotionEffectType.SLOW);
        }
      }
    }
  }
  

  @EventHandler
  public void druide(StructureGrowEvent e) {
    for (Game g : Plugin.getInstance().cbr.games) {
      if (g.zone.isInZone(e.getLocation())) {
        for (BattleRoyalePlayer p : g.players) {
          if (p.getRole() == Role.DRUIDE)
            p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3*20, 0));
          
        }
      }
    }
  }


  @EventHandler
  public void archer(EntityShootBowEvent e) {
    
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();

      if (Plugin.getInstance().cbr.gameOf(player) != null && Plugin.getInstance().cbr.gameOf(player).roleOf(player) == Role.ARCHER) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3*20, 0));
      }
    }

  }


  @EventHandler
  public void voyageur(EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player) {
      Player player = (Player) e.getDamager();

      if (e.getDamage() >= 6 && Plugin.getInstance().cbr.gameOf(player) != null && Plugin.getInstance().cbr.gameOf(player).roleOf(player) == Role.VOYAGEUR) {
        player.getInventory().addItem(new ItemStack(Material.CHORUS_FRUIT));
      }
    }
  }


  @EventHandler
  public void mineur(EntityDamageEvent e) {
    if (e.getEntityType() == EntityType.PLAYER) {
      Player player = (Player) e.getEntity();
      
      if ((e.getCause() == DamageCause.BLOCK_EXPLOSION || e.getCause() == DamageCause.ENTITY_EXPLOSION) && Plugin.getInstance().cbr.gameOf(player) != null && Plugin.getInstance().cbr.gameOf(player).roleOf(player) == Role.MINEUR) {
        e.setDamage(0);
      }
    }
  }


  @EventHandler
  public void enchanteur(PlayerExpChangeEvent e) {
    Player player = e.getPlayer();
    if (Plugin.getInstance().cbr.gameOf(player) != null && Plugin.getInstance().cbr.gameOf(player).roleOf(player) == Role.ENCHANTEUR && e.getAmount() > 0) {
      e.setAmount((int) Math.floor(e.getAmount()*1.5));
    }
  }

  // @EventHandler
  // public void sorcier(EntityPotionEffectEvent e) {
  //   if (e.getEntity() instanceof Player) {
  //     Player player = (Player) e.getEntity();

  //     if (Plugin.getInstance().cbr.gameOf(player) != null && Plugin.getInstance().cbr.gameOf(player).roleOf(player) == Role.ENCHANTEUR)
  //   }
  // }

}
