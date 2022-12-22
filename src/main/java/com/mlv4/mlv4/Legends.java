package com.mlv4.mlv4;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Legends {
  public static final ItemStack anduril = new ItemStack(Material.GOLDEN_SWORD);
  public static final ItemStack helmOfHador = new ItemStack(Material.GOLDEN_HELMET);
  public static final ItemStack theOneRing = new ItemStack(Material.FIRE_CHARGE);

  public static ItemStack random() {
    Random rand = new Random();
    return Legends.all().get(rand.nextInt(Legends.all().size()));
  }

  public static List<ItemStack> all() {
    return Arrays.asList(
      Legends.anduril,
      Legends.helmOfHador,
      Legends.theOneRing
    );
  }

  public static void init() {
    ItemMeta andurilMeta = Legends.anduril.getItemMeta();
    andurilMeta.setDisplayName("Anduril, l'épée reforgée d'Elendil");
    andurilMeta.setLore(Arrays.asList(
      "Elle a tué Sauron, certes,",
      "mais sera-t-elle à la hauteur",
      "de Steve ?",
      "ou d'Alex ?",
      "Dotée d'une multitude d'enchantements",
      "et de sortilèges secrets",
      "que même les plus sages ignorent..."
    ));
    andurilMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
    Random random = new Random();
    andurilMeta.addEnchant(Enchantment.DAMAGE_ALL, random.nextInt(2) + 3, true);
    andurilMeta.addEnchant(Enchantment.KNOCKBACK, random.nextInt(1) + 1, true);
    andurilMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    Legends.anduril.setItemMeta(andurilMeta);

    ItemMeta helmMeta = Legends.helmOfHador.getItemMeta();
    helmMeta.setDisplayName("Le Heaume du Dragon du Dor-Lómin");
    helmMeta.setLore(Arrays.asList(
      "Appartenant à Túrin Turambar,",
      "fils de Húrin,",
      "fils de Galdor,",
      "fils de Hador",
      "Il a tué Glaurung,",
      "le père des Dragons,",
      "mais sera-t-il en mesure",
      "de vous protéger contre Steve ?",
      "ou même Alex...",
      "ou même Vibout ???",
      "Doté d'un pouvoir,",
      "d'une force inconnue",
      "dont même Morgoth",
      "ne connaît pas la vraie nature..." ));
    helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, random.nextInt(5) + 5, true);
    helmMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, random.nextInt(5) + 5, true);
    helmMeta.addEnchant(Enchantment.PROTECTION_FIRE, random.nextInt(5) + 5, true);
    helmMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    Legends.helmOfHador.setItemMeta(helmMeta);

    ItemMeta ringMeta = Legends.theOneRing.getItemMeta();
    ringMeta.setDisplayName("L'anneau Unique");
    ringMeta.setLore(Arrays.asList(
      "One Ring to rule them all,",
      "One Ring to find them",
      "One Ring to bring them all",
      "And in the darkness bind them",
      "In the Land of Mordor",
      "Where the Shadows lie."
      
    ));
    ringMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
    ringMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    Legends.theOneRing.setItemMeta(ringMeta);
  }
}
