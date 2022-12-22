package com.mlv4.mlv4;
import java.util.HashSet;


import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.mlv4.mlv4.Achievements.BRAchievement;
import com.mlv4.mlv4.Powers.Role;
import com.mlv4.mlv4.Tomes.Tome;

public class BattleRoyalePlayer {
  private Player player;
  public HashSet<Tome> tomes = new HashSet<Tome>();
  public HashSet<BRAchievement> achievementsAvailable = new HashSet<BRAchievement>();
  private Role role;

  public BattleRoyalePlayer(Player player, Role role) {
    this.player = player;
    this.role = role;
    this.achievementsAvailable = BRAchievement.randomAchievementsPull(3);
  }

  public Game getCurrentGame() {
    return Plugin.getInstance().cbr.gameOf(this.player);
  }

  public boolean hasTome(Tome tome) {
    return this.tomes.contains(tome);
  }

  public Role getRole() {
    return this.role;
  }

  public Player getPlayer() {
    return this.player;
  }

  public boolean removeTome(Tome tomeErased) {
    if (this.tomes.contains(tomeErased)) {
      this.tomes.remove(tomeErased);
      return true;
    }
    else return false;
  }
  
  public static BattleRoyalePlayer get(Player p) {
    Game g = Plugin.getInstance().cbr.gameOf(p);
    if (g != null)
      return g.toBattleRoyalePlayer(p);
    else return null;
  }

  public void addTome(Tome tome) {
    this.tomes.add(tome);
  }

  public boolean achieve(BRAchievement achievement) {

    if (achievement.isAchievable(this) && this.achievementsAvailable.contains(achievement)) {
      this.achievementsAvailable.remove(achievement);
      player.giveExp(achievement.expReward);
      for (ItemStack reward : achievement.itemRewards) {
        player.getInventory().addItem(reward);
      }
      String message = "§aTu as validé l'achievement §b" + achievement.name  + "§a et tu gagnes §b+" + achievement.expReward + "xp";
      if (achievement.itemRewards.length > 0) message += " et un petit cadeau supplémentaire !";
      player.sendMessage(message);
      return true;
    }
    else {
      player.sendMessage("§cTu ne remplis pas encore les critères nécessaires pour valider cette quête");
      return false;
    }
  }
}
