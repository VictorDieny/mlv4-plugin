package com.mlv4.mlv4.Powers;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.mlv4.mlv4.Powers.Archer.EtLaLumiereFut;
import com.mlv4.mlv4.Powers.Archer.Rafale;
import com.mlv4.mlv4.Powers.Chaman.Clairvoyance;
import com.mlv4.mlv4.Powers.Chaman.Totem;
import com.mlv4.mlv4.Powers.Druide.AppelALaMeute;
import com.mlv4.mlv4.Powers.Druide.PluieDeNeige;
import com.mlv4.mlv4.Powers.Enchanteur.Enchantement;
import com.mlv4.mlv4.Powers.Enchanteur.Invocation;
import com.mlv4.mlv4.Powers.Mineur.Enfouissement;
import com.mlv4.mlv4.Powers.Mineur.CadeauDeNoel;
import com.mlv4.mlv4.Powers.Voyageur.Enderman;
import com.mlv4.mlv4.Powers.Voyageur.Faille;

public enum Role {

  DRUIDE(new AppelALaMeute(), new PluieDeNeige()),
  MINEUR(new CadeauDeNoel(), new Enfouissement()),
  VOYAGEUR(new Enderman(), new Faille()),
  ARCHER(new EtLaLumiereFut(), new Rafale()),
  CHAMAN(new Clairvoyance(), new Totem()),
  ENCHANTEUR(new Invocation(), new Enchantement()),
  ;

  private Power[] powers;

  private Role(Power... powers) {
    this.powers = powers;
  }

  public static Role randomRole() {
    Random random = new Random();
    return Role.values()[random.nextInt(Role.values().length)];
  }

  public List<Power> getPowers() {
    return Arrays.asList(this.powers);
  }
}