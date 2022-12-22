package com.mlv4.mlv4;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandButton implements CommandExecutor{
  private HashSet<Player> goodies = new HashSet<Player>();
  private HashSet<Player> baddies = new HashSet<Player>();

  public CommandButton() {
  }
  public boolean isGood(Player player) {
    return this.goodies.contains(player);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;

      if (command.getName().equalsIgnoreCase("button")) { // /button <mode:start|...> <map:Arbre|Vilage|Origine|Chateau> <role:bad|good>
        if (args[0].equalsIgnoreCase("start")) {
          Bukkit.dispatchCommand(player, "bossbar set minecraft:time_arbre players @a");
          player.setGameMode(GameMode.SURVIVAL);
          Bukkit.dispatchCommand(player, "effect clear");
          player.getInventory().clear();
          if (args[1].equalsIgnoreCase("arbre")) {
            if (args[2].equalsIgnoreCase("good")) {
              this.goodies.add(player);
              // tags management
              Bukkit.dispatchCommand(player, "tag @s remove Hunter");
              Bukkit.dispatchCommand(player, "tag @s add Ecureuil");
              // main armor
              player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
              player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
              player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
              // boots
              ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
              ItemMeta bootsMeta = boots.getItemMeta();
              bootsMeta.addEnchant(Enchantment.PROTECTION_FALL, 100, true);
              boots.setItemMeta(bootsMeta);
              player.getInventory().setBoots(boots);
              // knockback stick
              ItemStack stick = new ItemStack(Material.STICK);
              ItemMeta stickMeta = stick.getItemMeta();
              stickMeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
              stick.setItemMeta(stickMeta);
              player.getInventory().addItem(stick);
              // punch + infinity bow
              ItemStack bow = new ItemStack(Material.BOW);
              ItemMeta bowMeta = bow.getItemMeta();
              bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
              bowMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 3, true);
              bowMeta.setUnbreakable(true);
              bow.setItemMeta(bowMeta);
              player.getInventory().addItem(bow);
              // arrow + apples
              player.getInventory().addItem(new ItemStack(Material.ARROW));
              player.getInventory().addItem(new ItemStack(Material.APPLE, 64));
              
              // invisibility + mining fatigue
              player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
              player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 1));

              // TP
              Location goodLobby = new Location(player.getWorld(), -4, 60, -622);
              player.teleport(goodLobby);
            }
            else if (args[2].equalsIgnoreCase("bad")) {
              this.baddies.add(player);
              // tags management
              Bukkit.dispatchCommand(player, "tag @s remove Ecureuil");
              Bukkit.dispatchCommand(player, "tag @s add Hunter");
              // main armor
              player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
              player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
              player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
              // boots
              ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
              ItemMeta bootsMeta = boots.getItemMeta();
              bootsMeta.addEnchant(Enchantment.PROTECTION_FALL, 300, true);
              boots.setItemMeta(bootsMeta);
              player.getInventory().setBoots(boots);
              // knockback stick
              ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
              ItemMeta swordMeta = sword.getItemMeta();
              swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
              sword.setItemMeta(swordMeta);
              player.getInventory().addItem(sword);
              // punch + infinity bow
              ItemStack bow = new ItemStack(Material.BOW);
              ItemMeta bowMeta = bow.getItemMeta();
              bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
              bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
              bowMeta.setUnbreakable(true);
              bow.setItemMeta(bowMeta);
              player.getInventory().addItem(bow);
              // arrow
              player.getInventory().addItem(new ItemStack(Material.ARROW));
              player.getInventory().addItem(new ItemStack(Material.APPLE, 64));
              
              // mining fatigue + resistance
              player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 1));
              player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 5));
              
              // TP
              Location badLobby = new Location(player.getWorld(), -1, 50, -622);
              player.teleport(badLobby);
            }
          }
        }
      }
      return true;
    }
    return false;
  }
  
}