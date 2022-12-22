package com.mlv4.mlv4.Artefacts;

import org.bukkit.Material;

import com.mlv4.mlv4.BattleRoyalePlayer;
import com.mlv4.mlv4.Game;
import com.mlv4.mlv4.ItemBuilder;

public class Artefact extends ItemBuilder {

  protected String displayName;
  protected String[] lore;

  public Artefact(Material material, String displayName, String[] lore) {
    super(material);
    this.setDisplayName(displayName);
    this.setLore(lore);
    this.enchantSkin();
  };

  public boolean use(BattleRoyalePlayer by, Game game) {
    by.getPlayer().sendMessage("Tu utilises l'artefact neutre");
    return true;
  }
}
