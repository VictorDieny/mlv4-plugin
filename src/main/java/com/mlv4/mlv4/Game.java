package com.mlv4.mlv4;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.mlv4.mlv4.Artefacts.Artefact;
import com.mlv4.mlv4.Artefacts.Mine;
import com.mlv4.mlv4.Powers.Power;
import com.mlv4.mlv4.Powers.Role;

public class Game {
  public Plugin plugin;
  Random random = new Random();
  public HashSet<BattleRoyalePlayer> players = new HashSet<BattleRoyalePlayer>();
  public HashSet<BattleRoyalePlayer> zoneProtectedPlayers = new HashSet<BattleRoyalePlayer>();
  private BukkitTask countdown;
  private BukkitTask countdownZone;
  private BukkitTask cycleRandomizeChests;
  private BukkitTask cycleXP;
  private BossBar timerBar = Bukkit.createBossBar("Début des combats dans 60 secondes", BarColor.RED, BarStyle.SEGMENTED_6);
  private int seconds;
  public Zone zone;
  public HashSet<Mine> mines = new HashSet<Mine>();
  public Map map;
  public int lootTableId = 0;
  public HashSet<BossBar> bossBars = new HashSet<BossBar>();
  public HashSet<Power> powersUsed = new HashSet<Power>();

  // Legends
  private boolean legendLooted = false;
  HashSet<Player> pl;

  public Game(HashSet<Player> pl, Map map, Plugin plugin) {
    this.pl = pl;
    this.map = map;
    this.zone = new Zone(this.map);
    this.plugin = plugin;
    this.bossBars.add(timerBar);
  }


  public void launch() {
    Bukkit.broadcastMessage("§aLa partie commence avec §b" + pl.size() + " joueurs");
    for (Player p : pl) {
      // Spread players
      // Integer randX = this.random.nextInt(230) - 115;
      // Integer randZ = this.random.nextInt(230 - 2*Math.abs(randX)) - (115+Math.abs(randX));
      Integer randX = this.random.nextInt((int) Math.floor(map.side * .70)) + (int) Math.ceil(map.side * .15);
      Integer randZ = this.random.nextInt((int) Math.floor(map.side * .70)) + (int) Math.ceil(map.side * .15);
      p.teleport(new Location(p.getWorld(), map.xPos + randX, this.map.spawnHeight, map.zPos + randZ)); // 230 blocs de diamètre
      
      // Clear + Effects + gamemodeSurvival
      p.setGameMode(GameMode.SURVIVAL);
      p.getInventory().clear();
      Bukkit.dispatchCommand(p, "effect clear");
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Integer.MAX_VALUE, 1));
      p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 100));
      p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 100));
      p.setExp(0);
      p.setLevel(0);
      
      // Give egg
      p.getInventory().addItem(new ItemBuilder(Material.EGG)
        .setDisplayName("§5The ready egg")
        .setLore("§7Jette cette œuf une fois atteri, jeune tourterau !")
        .enchantSkin()
      );
    }

    // Clear chests && init Legends
    this.randomizeChests(Data.lootTableEmpty);
    Legends.init();

    // Setup timer
    this.seconds = 30;
    this.countdown = new BukkitRunnable() {
      @Override
      public void run() {
        if (seconds != 0) {
            timerBar.setProgress(seconds / 30D);
            seconds -= 1;
            timerBar.setTitle("§fDébut des combats dans §c" + seconds + "§f secondes");
        } 
        else {
          for (Player p : pl) {
            if (toBattleRoyalePlayer(p) == null) 
              players.add(new BattleRoyalePlayer(p, Role.randomRole()));
          }
          start();
        }
      }
    }.runTaskTimer(this.plugin, 0, 20);
    timerBar.setVisible(true);
    for (Player p : pl) {
      timerBar.addPlayer(p);
    }

    // Setup zone
    this.countdownZone = new BukkitRunnable() {
      private boolean zoneOpen = true;
      @Override
      public void run() {
        if (zoneOpen) zoneOpen = zone.progress(1);
        if (zone.side == 80) lootTableId = 1;
      } 
    }.runTaskTimer(this.plugin, 0, 40);
  }


  private void start() {
    Bukkit.broadcastMessage("§aIl est l'heure de commencer la game");
    this.countdown.cancel();
    for (BattleRoyalePlayer p : this.players) {
      p.getPlayer().sendTitle("§bTu es §a" + p.getRole(), null, 2, 60, 8);
      Bukkit.dispatchCommand(p.getPlayer(), "effect clear");
      p.getPlayer().getInventory().remove(Material.EGG);
      p.getPlayer().getInventory().setItem(8,new ItemBuilder(Material.BOOK)
        .setDisplayName("Tomes & Quêtes")
        .setLore("Menu d'accès aux pouvoirs de votre héros, et à l'achat de tomes")
        .enchantSkin()
      );
      for (ItemStack power : p.getRole().getPowers()) {
        p.getPlayer().getInventory().addItem(power);
      }
    }
    timerBar.removeAll();
    this.cycleRandomizeChests = new BukkitRunnable() {
      @Override
      public void run() {
        randomizeChests(Data.lootTables[lootTableId]);
      }
    }.runTaskTimer(this.plugin, 0, 100*20);
    this.cycleXP = new BukkitRunnable() {
      @Override
      public void run() {
        for (BattleRoyalePlayer p : players) {
          p.getPlayer().giveExp(1);
        }
      }
    }.runTaskTimer(this.plugin, 0, 50);
  }


  public void playerThrowEgg(Player player) {
    if (this.pl.contains(player)) this.players.add(new BattleRoyalePlayer(player, Role.randomRole()));
    Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " §6a jeté son œuf");
    if (this.getPlayers().equals(this.pl)) this.start();
  }


  public void stopGame() {
    if (this.countdown != null) this.countdown.cancel();
    if (this.countdownZone != null) this.countdownZone.cancel();
    if (this.cycleRandomizeChests != null) this.cycleRandomizeChests.cancel();
    if (this.cycleXP != null) this.cycleXP.cancel();
    for (Power p : this.powersUsed) {
      p.stopLifecycle();
    }
    this.randomizeChests(Data.lootTableEmpty);
    this.map.buildFromModel();
    for(Entity e : Bukkit.getWorld("world").getEntities()){
      if (e.getType() == EntityType.DROPPED_ITEM
      || e.getType() == EntityType.WOLF
      || e.getType() == EntityType.SNOWMAN
      || e.getType() == EntityType.HUSK
      || e.getType() == EntityType.ZOMBIE
      || e.getType() == EntityType.ZOMBIE_VILLAGER
      )
        e.remove();
    }

    for (BossBar b : this.bossBars) {
      b.removeAll();
    }
  }


  public void randomizeChests(Object[][] lootTable) {
    for (int[] location : this.map.chestLocations) {
      final Location loc = new Location(Bukkit.getWorld("world"), location[0], location[1], location[2]);
      loc.getBlock().setType(Material.CHEST);
      Chest chest = (Chest) loc.getBlock().getState();
      chest.getInventory().clear();
      for (Object[] lootTableItem : lootTable) {
        if (lootTableItem[0] instanceof Material)
          chest.getInventory().addItem(randomItemStack((Material) lootTableItem[0], (int) lootTableItem[1], (int) lootTableItem[2], (int) lootTableItem[3]));
        else if (lootTableItem[0] instanceof Artefact)
          chest.getInventory().addItem(randomItemStack((Artefact) lootTableItem[0], (int) lootTableItem[1]));
      }
      if (Math.random()<0.005 && !this.legendLooted && lootTable != Data.lootTableEmpty) {
        ItemStack lootedLegend = (ItemStack) Legends.random();
        chest.getInventory().addItem(lootedLegend);
        this.legendLooted = true;
        Bukkit.broadcastMessage("§d§oUne légende est apparue dans un coffre...");
      }
    }
  }


  // For standard ItemStacks
  private ItemStack randomItemStack(Material material, int chance, int amountMin, int amountMax, PotionData... potionData) {
    if (chance > Math.random()*100) {
      int amount = amountMin == amountMax ? amountMin : this.random.nextInt(amountMax - amountMin) + amountMin;
      ItemStack itemStack = new ItemStack(material, amount);
      if (potionData.length > 0) {
        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
        potionMeta.setBasePotionData(potionData[0]);
        itemStack.setItemMeta(potionMeta);
      }
      return itemStack;

    }
    else return new ItemStack(Material.VOID_AIR);
  }
  // For Artefacts
  private ItemStack randomItemStack(Artefact artefact, int chance) {
    if (chance > Math.random()*100)
      return artefact;
    else
      return new ItemStack(Material.VOID_AIR);
  }

  public Role roleOf(Player p) {
    return toBattleRoyalePlayer(p).getRole();
  }

  public Set<Player> getPlayers() {
    return this.players.stream().map(p -> p.getPlayer()).collect(Collectors.toSet());
  }

  public BattleRoyalePlayer toBattleRoyalePlayer(Player p) {
    for (BattleRoyalePlayer brp : this.players) {
      if (brp.getPlayer() == p) return brp;
    }
    return null;
  }
}
