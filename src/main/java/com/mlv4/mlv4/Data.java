package com.mlv4.mlv4;

import java.util.HashMap;

import org.bukkit.Material;

import com.mlv4.mlv4.Achievements.BRAchievement;
import com.mlv4.mlv4.Achievements.BobCrafter;
import com.mlv4.mlv4.Achievements.Famine;
import com.mlv4.mlv4.Achievements.ImACow;
import com.mlv4.mlv4.Achievements.MainVerte;
import com.mlv4.mlv4.Achievements.MarteauPiqueur;
import com.mlv4.mlv4.Achievements.Risky;
import com.mlv4.mlv4.Artefacts.Accelereacteur;
import com.mlv4.mlv4.Artefacts.Artefact;
import com.mlv4.mlv4.Artefacts.DustCoal;
import com.mlv4.mlv4.Artefacts.Mine;
import com.mlv4.mlv4.Artefacts.ZonicMagnet;
import com.mlv4.mlv4.Artefacts.ZonicProtection;


public class Data {

  public static final Object[][] lootTableEmpty = {};
  public static final Object[][] lootTableMain = {
    // Material, percentage, min amount, max amount
    // Armor
    {Material.DIAMOND_CHESTPLATE, 3, 1, 1},
    {Material.DIAMOND_LEGGINGS, 3, 1, 1},
    {Material.DIAMOND_HELMET, 3, 1, 1},
    {Material.DIAMOND_BOOTS, 3, 1, 1},
    
    {Material.LEATHER_CHESTPLATE, 30, 1, 1},
    {Material.LEATHER_LEGGINGS, 30, 1, 1},
    {Material.LEATHER_HELMET, 30, 1, 1},
    {Material.LEATHER_BOOTS, 30, 1, 1},
    
    {Material.IRON_CHESTPLATE, 8, 1, 1},
    {Material.IRON_LEGGINGS, 8, 1, 1},
    {Material.IRON_HELMET, 8, 1, 1},
    {Material.IRON_BOOTS, 8, 1, 1},
    
    {Material.CHAINMAIL_CHESTPLATE, 15, 1, 1},
    {Material.CHAINMAIL_LEGGINGS, 15, 1, 1},
    {Material.CHAINMAIL_HELMET, 15, 1, 1},
    {Material.CHAINMAIL_BOOTS, 15, 1, 1},
    
    {Material.GOLDEN_CHESTPLATE, 3, 1, 1},
    {Material.GOLDEN_LEGGINGS, 3, 1, 1},
    {Material.GOLDEN_HELMET, 3, 1, 1},
    {Material.GOLDEN_BOOTS, 3, 1, 1},
    
    // Swords
    {Material.DIAMOND_SWORD, 3, 1, 1},
    {Material.IRON_SWORD, 12, 1, 1},
    {Material.STONE_SWORD, 32, 1, 1},
    {Material.GOLDEN_SWORD, 3, 1, 1},
    // Axes
    {Material.IRON_AXE, 7, 1, 1},
    {Material.STONE_AXE, 10, 1, 1},
    // Gadgets
    {Material.WATER_BUCKET, 15, 1, 1},
    {Material.SHIELD, 7, 1, 1},
    {Material.FLINT_AND_STEEL, 6, 1, 1},
    // Bowing
    {Material.BOW, 7, 1, 1},
    {Material.CROSSBOW, 18, 1, 1},
    {Material.ARROW, 30, 1, 8},
    // Food
    {Material.COOKED_BEEF, 10, 2, 6},
    {Material.CARROT, 20, 2, 8},
    {Material.APPLE, 15, 1, 4},

    // Artefacts
    {new ZonicMagnet(), 3},
    {new DustCoal(), 4},
    {new ZonicProtection(), 4},
    {new Mine(), 3},
    {new Accelereacteur(), 4}
  };
  public static final Object[][] lootTableEpic = {
    {Material.DIAMOND_CHESTPLATE, 5, 1, 1},
    {Material.DIAMOND_BOOTS, 5, 1, 1},
    {Material.DIAMOND_HELMET, 5, 1, 1},
    {Material.DIAMOND_LEGGINGS, 5, 1, 1},

    {Material.IRON_CHESTPLATE, 12, 1, 1},
    {Material.IRON_LEGGINGS, 12, 1, 1},
    {Material.IRON_HELMET, 12, 1, 1},
    {Material.IRON_BOOTS, 12, 1, 1},

    {Material.DIAMOND_SWORD, 5, 1, 1},
    {Material.IRON_SWORD, 10, 1, 1},
    {Material.IRON_AXE, 10, 1, 1},

    {Material.SHIELD, 15, 1, 1},
    {Material.COBBLESTONE, 15, 16, 32},
    {Material.OAK_SAPLING, 15, 16, 32},
    {Material.EXPERIENCE_BOTTLE, 10, 10, 20},

    {Material.ENDER_PEARL, 10, 1, 1},
    {Material.CHORUS_FRUIT, 10, 1, 4},

    {Material.BOW, 10, 1, 1},
    {Material.CROSSBOW, 20, 1, 1},
    {Material.ARROW, 20, 2, 8},
    {Material.ARROW, 3, 1, 5},
    {Material.FIREWORK_ROCKET, 3, 1, 5},

    {Material.COOKED_BEEF, 15, 2, 6},
    {Material.GOLDEN_CARROT, 10, 2, 8},
    {Material.GOLDEN_APPLE, 5, 1, 3},

    {new ZonicMagnet(), 7},
    {new DustCoal(), 10},
    {new ZonicProtection(), 4},
    {new Mine(), 10},
    {new Accelereacteur(), 10}
  };

  public static final Object[][][] lootTables = {
    lootTableMain, lootTableEpic
  };

  public static final HashMap<Material, String> artefactMaterials =  new HashMap<Material, String>() {{
    put(Material.IRON_BLOCK, "zonicmagnet");
    put(Material.COAL, "dustcoal");
    put(Material.WITHER_ROSE, "zonicprotection");
    put(Material.MAGMA_CREAM, "mine");
    put(Material.CLOCK, "acceleracteur");

  }};

  public static final Material[] netherMaterials = {
    Material.NETHERRACK,
    Material.NETHER_BRICKS,
    Material.MAGMA_BLOCK,
    Material.COAL_BLOCK,
    Material.OBSIDIAN,
    Material.BEDROCK,
    Material.AIR
  };

  public static final BRAchievement[] achievements = {
    new ImACow(),
    new BobCrafter(),
    new MarteauPiqueur(),
    new Risky(),
    new Famine(),
    new MainVerte()
  };

  public static final HashMap<String, ? extends Artefact> artefacts = new HashMap<String, Artefact>() {{
    put("Aimant zonique", new ZonicMagnet());
    put("Charbon Poussiéreux", new DustCoal());
    put("Carapace zonique", new ZonicProtection());
    put("Mine", new Mine());
    put("Accéléréacteur temporel", new Accelereacteur());
  }};
}
