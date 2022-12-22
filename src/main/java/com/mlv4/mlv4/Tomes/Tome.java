package com.mlv4.mlv4.Tomes;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.ItemBuilder;

public enum Tome {

  // Passerelles
  GLOWING(new TomeBuilder("Glowing")
    .setUnlockLevel(5)
    .setPotionEffectType(PotionEffectType.GLOWING)
    .setSlotInInventory(6)
  ),
  SLOWNESS(new TomeBuilder("Slowness")
    .setUnlockLevel(5)
    .setPotionEffectType(PotionEffectType.SLOW)
    .setSlotInInventory(15)
  ),

  // Meta agressivité
  HASTE_1(new TomeBuilder("Haste I")
    .setUnlockLevel(3)
    .setPotionEffectType(PotionEffectType.FAST_DIGGING)
    .setSlotInInventory(0)
  ),
  HASTE_2(new TomeBuilder("Haste II")
    .setUnlockLevel(8)
    .setPotionEffectType(PotionEffectType.FAST_DIGGING)
    .setPotionAmplifier(1)
    .setSlotInInventory(9)
    .setPreviousTomesRequired(HASTE_1)
  ),
  STRENGTH_1(new TomeBuilder("Strength I")
    .setUnlockLevel(20)
    .setPotionEffectType(PotionEffectType.INCREASE_DAMAGE)
    .setSlotInInventory(18)
    .setPreviousTomesRequired(HASTE_2, GLOWING)
  ),

  // Meta Soins
  REGENERATION_1(new TomeBuilder("Regeneration I")
    .setUnlockLevel(8)
    .setPotionEffectType(PotionEffectType.REGENERATION)
    .setSlotInInventory(1)
  ),
  REGENERATION_2(new TomeBuilder("Regeneration II")
    .setUnlockLevel(14)
    .setPotionEffectType(PotionEffectType.REGENERATION)
    .setPotionAmplifier(1)
    .setSlotInInventory(10)
    .setPreviousTomesRequired(REGENERATION_1)
  ),
  SATURATION_1(new TomeBuilder("Saturation I")
    .setUnlockLevel(20)
    .setPotionEffectType(PotionEffectType.SATURATION)
    .setSlotInInventory(19)
    .setPreviousTomesRequired(REGENERATION_2, SLOWNESS)
    .setTomesErased(REGENERATION_2, REGENERATION_1)
  ),

  // Meta agilité
  JUMP_BOOST_2(new TomeBuilder("Jump Boost II")
    .setUnlockLevel(3)
    .setPotionEffectType(PotionEffectType.JUMP)
    .setPotionAmplifier(1)
    .setSlotInInventory(2)
  ),
  SPEED_1(new TomeBuilder("Speed I")
    .setUnlockLevel(8)
    .setPotionEffectType(PotionEffectType.SPEED)
    .setSlotInInventory(11)
    .setPreviousTomesRequired(JUMP_BOOST_2)
  ),
  SPEED_2(new TomeBuilder("Speed II")
    .setUnlockLevel(14)
    .setPotionEffectType(PotionEffectType.SPEED)
    .setPotionAmplifier(1)
    .setSlotInInventory(20)
    .setPreviousTomesRequired(SPEED_1, JUMP_BOOST_2, GLOWING)
  ),

  // Meta aquatique
  CONDUIT_POWER(new TomeBuilder("Conduit Power")
    .setUnlockLevel(3)
    .setPotionEffectType(PotionEffectType.CONDUIT_POWER)
    .setSlotInInventory(3)
  ),
  DOLPHINS_GRACE_1(new TomeBuilder("Dolphin's Grace I")
    .setUnlockLevel(5)
    .setPotionEffectType(PotionEffectType.DOLPHINS_GRACE)
    .setSlotInInventory(12)
    .setPreviousTomesRequired(CONDUIT_POWER)
  ),

  // Meta Protection
  FIRE_RESISTANCE(new TomeBuilder("Fire Resistance")
    .setUnlockLevel(3)
    .setPotionEffectType(PotionEffectType.FIRE_RESISTANCE)
    .setSlotInInventory(4)
  ),
  ABSORBTION_2(new TomeBuilder("Absorbtion II (+4 cœurs)")
    .setUnlockLevel(8)
    .setPotionEffectType(PotionEffectType.ABSORPTION)
    .setPotionAmplifier(1)
    .setSlotInInventory(13)
    .setPreviousTomesRequired(FIRE_RESISTANCE)
  ),
  HEALTH_BOOST_1(new TomeBuilder("Health Boost (+2 cœurs rechargeables)")
    .setUnlockLevel(8)
    .setPotionEffectType(PotionEffectType.HEALTH_BOOST)
    .setSlotInInventory(14)
    .setPreviousTomesRequired(FIRE_RESISTANCE)
  ),
  RESISTANCE_1(new TomeBuilder("Resistance I")
    .setUnlockLevel(20)
    .setPotionEffectType(PotionEffectType.DAMAGE_RESISTANCE)
    .setSlotInInventory(22)
    .setPreviousTomesRequired(ABSORBTION_2, HEALTH_BOOST_1, SLOWNESS)
    .setTomesErased(SATURATION_1)
  )
  



  ;

  public String displayName;
  private int unlockLevel;
  private PotionEffectType potionEffectType;
  private int potionAmplifier;
  private Tome[] previousTomesRequired;
  public int slot;
  private Tome[] tomesErased;

  private Tome(TomeBuilder builder) {
    this.displayName = builder.displayName;
    this.unlockLevel = builder.unlockLevel;
    this.potionEffectType = builder.potionEffectType;
    this.potionAmplifier = builder.potionAmplifier;
    this.previousTomesRequired = builder.previousTomesRequired;
    this.slot = builder.slot;
    this.tomesErased = builder.tomesErased;
  }

  private static class TomeBuilder {

    public TomeBuilder(String displayName) {
      this.displayName = displayName;
    };
    
    private String displayName;
    private int unlockLevel = 0;
    private PotionEffectType potionEffectType = PotionEffectType.LUCK;
    private int potionAmplifier = 0;
    private Tome[] previousTomesRequired;
    public int slot = TomeBuilder.slotOffset;
    private Tome[] tomesErased = new Tome[] {};
    public static int slotOffset = 27;
    
    public TomeBuilder setUnlockLevel(int level) {
      this.unlockLevel = level;
      return this;
    }
    public TomeBuilder setPotionEffectType(PotionEffectType potionEffectType) {
      this.potionEffectType = potionEffectType;
      return this;
    }
    /**
     * @param potionAmplifier level of the potion effect -1. For example, value 0 returns potion level 1 
     */
    public TomeBuilder setPotionAmplifier(int potionAmplifier) {
      this.potionAmplifier = potionAmplifier;
      return this;
    }
    public TomeBuilder setSlotInInventory(int slotFromZero) {
      this.slot = slotFromZero + TomeBuilder.slotOffset;
      return this;
    }
    public TomeBuilder setPreviousTomesRequired(Tome... previousTomesRequired) {
      this.previousTomesRequired = previousTomesRequired;
      return this;
    }
    public TomeBuilder setTomesErased(Tome... tomesErased) {
      this.tomesErased = tomesErased;
      return this;
    } 
  }

    public boolean isUnlockable(BattleRoyalePlayer by) {
      boolean pathOk = false;
      if (this.previousTomesRequired == null) pathOk = true;
      else
      for (Tome tome : previousTomesRequired) {
        if (by.hasTome(tome))
          pathOk = true;
      }

    return (
      by.getPlayer().getLevel() >= this.unlockLevel // by a suffisamment de niveaux
      && pathOk // Possède bien au moins un tome-passerelle nécessaire
      && ( // By ne possède pas déjà le tome
        !by.getPlayer().hasPotionEffect(this.potionEffectType)
        || (
          by.getPlayer().hasPotionEffect(this.potionEffectType)
          && by.getPlayer().getPotionEffect(this.potionEffectType).getAmplifier() < this.potionAmplifier
          )
      )
    );
  }

  public ItemStack toItemStack(BattleRoyalePlayer player) {
    Material material;

    if (this.isUnlockable(player)) {
      material = Material.BOOK;
    }
    else if (player.hasTome(this)) {
      material = Material.ENCHANTED_BOOK;
    }
    else {
      material = Material.BARRIER;
    }


    return new ItemBuilder(material)
      .setDisplayName(this.displayName)
      .setLore("Coût : " + this.unlockLevel + " levels");
  }



  public boolean unlock(BattleRoyalePlayer by) {
    if (this.isUnlockable(by)) {
      by.getPlayer().addPotionEffect(new PotionEffect(this.potionEffectType, Integer.MAX_VALUE, this.potionAmplifier));
      by.getPlayer().giveExpLevels(-this.unlockLevel);
      by.addTome(this);
      for (Tome tomeErased : this.tomesErased) {
        by.removeTome(tomeErased);
        by.getPlayer().removePotionEffect(tomeErased.potionEffectType);
      }
    }
    
    return true;
  }
  
}
