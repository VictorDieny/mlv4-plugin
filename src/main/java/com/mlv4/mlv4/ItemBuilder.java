package com.mlv4.mlv4;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder extends ItemStack {

  protected CommandBattleroyale br;

  public ItemBuilder(Material material) {
    super(material);
  }

  public ItemBuilder setDisplayName(String name) {
    ItemMeta im = this.getItemMeta();
    im.setDisplayName(name);
    this.setItemMeta(im);
    return this;
  }

  public ItemBuilder setLore(String... lore) {
    return this.setLore(Arrays.asList(lore));
  }

  public ItemBuilder setLore(List<String> lore) {
    ItemMeta im = this.getItemMeta();
    im.setLore(lore);
    this.setItemMeta(im);
    return this;
  }


  public ItemBuilder enchantSkin() {
    ItemMeta im = this.getItemMeta();
    im.addEnchant(Enchantment.LUCK, 1, true);
    im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    this.setItemMeta(im);
    return this;
  }
}
