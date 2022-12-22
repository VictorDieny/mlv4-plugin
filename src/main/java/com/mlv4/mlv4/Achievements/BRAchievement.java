package com.mlv4.mlv4.Achievements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Data;

public class BRAchievement {

  public String name;
  public int expReward;
  public ItemStack[] itemRewards;
  public List<String> lore;

  public BRAchievement(String name, List<String> lore , int tier, int expReward, ItemStack... itemRewards) {
    this.name = name;
    this.expReward = expReward;
    this.itemRewards = itemRewards;
    this.lore = lore;
    String xpLore = "§6+ " + this.expReward + "xp"; 
    this.lore.add(xpLore);
    for (ItemStack reward : itemRewards) {
      String rewardLore = "§6+ " + reward.getAmount() + " " + reward.getType().toString();
      this.lore.add(rewardLore);
    }
  }

  public boolean isAchievable(BattleRoyalePlayer by) {return false;}


  public static HashSet<BRAchievement> randomAchievementsPull(int amount) {
    HashSet<BRAchievement> achievementsSet = new HashSet<BRAchievement>();
    HashSet<BRAchievement> pull = new HashSet<BRAchievement>();

    for (BRAchievement brAchievement : Data.achievements) {
      achievementsSet.add(brAchievement);
    }

    ArrayList<BRAchievement> shuffledAchievements = new ArrayList<BRAchievement>(achievementsSet);
    Collections.shuffle(shuffledAchievements);

    for (int i = 0; i < amount; i++) {
      pull.add(shuffledAchievements.get(i));
    }

    return pull;
  }
  
}
