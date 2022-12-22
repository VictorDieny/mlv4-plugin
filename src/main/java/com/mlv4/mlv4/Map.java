package com.mlv4.mlv4;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Map {
  public final int xPos;
  public final int yPos;
  public final int zPos;
  private final int yOffsetBetweenMapAndModel;
  final int height;
  final int side;
  public final HashSet<int[]> chestLocations;
  public int spawnHeight;
  
  public Map(int xPos, int yPos, int zPos, int height, int side, int spawnHeight, int yOffsetBetweenMapAndModel, HashSet<int[]> chestLocations) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.zPos = zPos;
    this.height = height;
    this.side = side;
    this.spawnHeight = spawnHeight;
    this.yOffsetBetweenMapAndModel = yOffsetBetweenMapAndModel;
    this.chestLocations = chestLocations;
  }


  public void buildFromModel() {
    for (int x = 0; x < this.side; x++) {
      for (int y = 0; y < this.height; y++) {
        for (int z = 0; z < this.side; z++) {
          Block modelBlock = new Location(Bukkit.getWorld("world"), x + xPos, y + yPos - yOffsetBetweenMapAndModel, z + zPos).getBlock();
          Block clonedBlock = new Location(Bukkit.getWorld("world"), x + xPos, y + yPos, z + zPos).getBlock();
          if (modelBlock.getType() != clonedBlock.getType() || modelBlock.getBlockData() != clonedBlock) {
            clonedBlock.setType(modelBlock.getType());
            clonedBlock.setBlockData(modelBlock.getBlockData());
          }
        }
      }
    }
  }

  public void burn(int x, int z) {
    Random rand = new Random();
    for (int y = 0; y < height; y++) {
      Material unburnedType = new Location(Bukkit.getWorld("world"), x, y + yPos, z).getBlock().getType();
      if (unburnedType != Material.AIR)
      new Location(Bukkit.getWorld("world"), x, y + yPos, z).getBlock().setType(Data.netherMaterials[rand.nextInt(Data.netherMaterials.length)]);;
        
    }
  }
  
}
