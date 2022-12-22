package com.mlv4.mlv4;

import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mlv4.mlv4.Achievements.BRAchievement;
import com.mlv4.mlv4.Artefacts.Mine;
import com.mlv4.mlv4.Powers.Power;
import com.mlv4.mlv4.Tomes.Tome;

public class Mlv4Listeners implements Listener {

  @EventHandler 
  public void onDropEgg(PlayerEggThrowEvent e) {
    Player player = e.getPlayer();
    if (!Plugin.getInstance().cbr.games.isEmpty()) 
      for (Game game : Plugin.getInstance().cbr.games)
        game.playerThrowEgg(player);
        
  }

  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent e) {
    if (!Plugin.getInstance().cbr.games.isEmpty()) {
      Game g = Plugin.getInstance().cbr.gameOf(e.getEntity());
      g.players.remove(g.toBattleRoyalePlayer(e.getEntity()));
      if (g.players.size() <= 1) {
        g.stopGame();
        Plugin.getInstance().cbr.games.remove(g);
        BattleRoyalePlayer winner = (BattleRoyalePlayer) g.players.toArray()[0];
        Bukkit.broadcastMessage("§l§aLe bg de la game est §b" + winner.getPlayer().getName());
      }
    }
  }

  @EventHandler
  public void onPlayerDisconnect(PlayerQuitEvent e) {
    if (Bukkit.getOnlinePlayers().isEmpty()) {
      Plugin.getInstance().cbr.clearGames();
    }
  }

  @EventHandler
  public void onMove(PlayerMoveEvent e) {
    BattleRoyalePlayer player = BattleRoyalePlayer.get(e.getPlayer());
    if (player == null) return;
    Location destination = e.getTo();
    Game g = player.getCurrentGame();
    if (g != null) {
      if (player.getPlayer().getGameMode() == GameMode.SURVIVAL && !g.zone.isInZone(destination) && !g.zoneProtectedPlayers.contains(player)) {
        player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
        Bukkit.getWorld("world").spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, destination, 1);
        if (g.zone.isInZone(e.getFrom())) {
          player.getPlayer().sendMessage("§cTu entres dans la zone !");
        }
      }
      if (!g.mines.isEmpty()) {
        for (Mine mine : g.mines) {
          if (mine.isInRange(destination) && !mine.isInRange(e.getFrom())) {
            if (mine.owner == player) {
              player.getPlayer().sendMessage("§e{Rappel} Vous marchez actuellement sur une de vos mines");
            }
            else mine.explode(player);
          }
        }
      }
    }
  }

  @EventHandler
  public void onInventoryClick(InventoryClickEvent e) {
    ItemStack item = e.getCurrentItem();
    BattleRoyalePlayer player = BattleRoyalePlayer.get((Player) e.getWhoClicked());
    if (item == null && player != null) return;

    if (e.getView().getTitle() == "Quêtes & Tomes" && player.getCurrentGame() != null) {
      if (item.getType() == Material.BOOK) {
        for (Tome t : Tome.values()) {
          if (t.slot == e.getSlot()) {
            player.getPlayer().sendMessage("§aTu as unlock le tome §b" + t.displayName);
            t.unlock(player);
          }
        }
      }
      else if (item.getType() == Material.MAP) {
        BRAchievement achievement = Data.achievements[e.getSlot()];
        player.achieve(achievement);
      }
      e.setCancelled(true);
      player.getPlayer().closeInventory();
    }
  }

  @EventHandler
  public void onInteract(PlayerInteractEvent e) {
    ItemStack item = e.getItem();
    BattleRoyalePlayer player = BattleRoyalePlayer.get(e.getPlayer());
    if (player == null) return;
    Game g = player.getCurrentGame();

    if (g != null && player != null) {
      if (item != null) {
        
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
          
      /* Quêtes et Tomes */

          if (item.getType() == Material.BOOK) {
            Inventory bsInventory = Bukkit.createInventory(null, 54, "Quêtes & Tomes");

            // Quêtes
            for (int i = 0; i < Data.achievements.length; i++) {
              BRAchievement achievement = Data.achievements[i];
              if (player.achievementsAvailable.contains(achievement)) {
                bsInventory.setItem(i, new ItemBuilder(Material.MAP).setDisplayName(achievement.name).setLore(achievement.lore));
              }
            }
        
            // Items de métas (visuel only)
            bsInventory.setItem(18, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§5Catégorie : Agressivité"));
            bsInventory.setItem(19, new ItemBuilder(Material.APPLE).setDisplayName("§aCatégorie : Soin"));
            bsInventory.setItem(20, new ItemBuilder(Material.SADDLE).setDisplayName("§2Catégorie : Agilité"));
            bsInventory.setItem(21, new ItemBuilder(Material.WATER_BUCKET).setDisplayName("§3Catégorie : Aquatique"));
            bsInventory.setItem(22, new ItemBuilder(Material.SHIELD).setDisplayName("§6Catégorie : Protection"));
            
            // Tomes + apparence
            for (Tome t : Tome.values()) {
              bsInventory.setItem(t.slot, t.toItemStack(player));
            }
            player.getPlayer().openInventory(bsInventory);
          }

      /* Artefacts */

          else if (Data.artefactMaterials.keySet().contains(item.getType())) {
            boolean artefactConsumed = Data.artefacts.get(item.getItemMeta().getDisplayName()).use(player, g);
            if (artefactConsumed) player.getPlayer().getInventory().remove(item);
            else e.setCancelled(true);
          }

      /* Powers | Roles */

          // Ci-dessous gros gros bordel pour comparer les displayname des Power sans passer par un foreach, donc utilisation de casts ArrayList <=> Stream à tort et à travers
          else if (player.getRole().getPowers().stream().map(power -> power.getItemMeta().getDisplayName()).collect(Collectors.toList()).contains(item.getItemMeta().getDisplayName())) {
            Power powerUsed = Power.getFromMaterial(item.getType());

            if (powerUsed.use(player)) {
              g.bossBars.add(powerUsed.reloadBar);
              player.getPlayer().getInventory().remove(item);
              g.powersUsed.add(powerUsed);
            }
            else {
              player.getPlayer().sendMessage("Tu n'as pas assez d'XP pour utiliser ce pouvoir");
              player.getPlayer().getInventory().remove(item);
              player.getPlayer().getInventory().setItem(player.getPlayer().getInventory().getHeldItemSlot(), item);
              e.setCancelled(true);
            }
          }
        }
      }
    }
  }
}
