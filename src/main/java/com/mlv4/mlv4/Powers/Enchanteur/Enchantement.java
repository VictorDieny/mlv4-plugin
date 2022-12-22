package com.mlv4.mlv4.Powers.Enchanteur;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Powers.Power;

public class Enchantement extends Power {
  
  private static HashSet<Material> enchatableWithProtection = new HashSet<Material>() {{
    add(Material.LEATHER_HELMET);
    add(Material.LEATHER_CHESTPLATE);
    add(Material.LEATHER_LEGGINGS);
    add(Material.LEATHER_BOOTS);

    add(Material.CHAINMAIL_HELMET);
    add(Material.CHAINMAIL_CHESTPLATE);
    add(Material.CHAINMAIL_LEGGINGS);
    add(Material.CHAINMAIL_BOOTS);

    add(Material.IRON_HELMET);
    add(Material.IRON_CHESTPLATE);
    add(Material.IRON_LEGGINGS);
    add(Material.IRON_BOOTS);

    add(Material.GOLDEN_HELMET);
    add(Material.GOLDEN_CHESTPLATE);
    add(Material.GOLDEN_LEGGINGS);
    add(Material.GOLDEN_BOOTS);

    add(Material.DIAMOND_HELMET);
    add(Material.DIAMOND_CHESTPLATE);
    add(Material.DIAMOND_LEGGINGS);
    add(Material.DIAMOND_BOOTS);
  }};

  private static HashSet<Material> enchatableWithSharpness = new HashSet<Material>() {{
    add(Material.GOLDEN_SWORD);
    add(Material.WOODEN_SWORD);
    add(Material.STONE_SWORD);
    add(Material.IRON_SWORD);
    add(Material.DIAMOND_SWORD);
  }};


  public Enchantement() {
    super(Material.LAPIS_LAZULI, "Enchantement", new String[] {"+ 1 Sharpness/Protection", "Sur l'item en main gauche", "Coût : 6 levels", "Intervalle : 2 min"}, 120, 6);
  }

  @Override
  public boolean use(BattleRoyalePlayer by) {
    ItemStack item = by.getPlayer().getInventory().getItemInOffHand();

    if (super.use(by) && item != null) {
      if (enchatableWithProtection.contains(item.getType())) {
        int level = item.getEnchantments().containsKey(Enchantment.PROTECTION_ENVIRONMENTAL) ? item.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) + 1 : 1;
        item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
      }

      else if (enchatableWithSharpness.contains(item.getType())) {
        int level = item.getEnchantments().containsKey(Enchantment.DAMAGE_ALL) ? item.getEnchantmentLevel(Enchantment.DAMAGE_ALL) + 1 : 1;
        item.addEnchantment(Enchantment.DAMAGE_ALL, level);
      }

      else {
        by.getPlayer().sendMessage("§c L'item en main gauche ne peut pas être enchanté avec ce pouvoir (seulement armure et épées)");
        return false;
      }
      
      return true;
    }
    else return false;
  }
  
}
