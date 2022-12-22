package com.mlv4.mlv4;

import java.util.Random;

import org.bukkit.Location;

public class Zone {
  private Random random = new Random();
  private int centerX;
  private int centerZ;
  int xPos;
  int zPos;
  // final int yPos = 135; 
  public int side = 250;
  private Map map;


  public Zone(Map map) {
    this.map = map;
    this.centerX = this.random.nextInt(250) + map.xPos;
    this.centerZ = this.random.nextInt(250) + map.zPos;
    this.map = map;
    this.xPos = map.xPos;
    this.zPos = map.zPos;
  }

  public Boolean progress(int blocks) {
    this.side -= blocks;
    if (this.side <= 10) return false;
    else {
      if (this.xPos + (this.side/2) < this.centerX) this.xPos += blocks;
      if (this.zPos + (this.side/2) < this.centerZ) this.zPos += blocks;
      this.show();
      return true;
    }
  }
  public int[] getCoordinates() {
    int[] coordinates = {this.xPos, this.zPos, this.side}; 
    return coordinates;
  }
  public void setZoneCenter(int x, int z) {
    this.centerX = x;
    this.centerZ = z;
  }
  public boolean isInZone(double x, double z) {
    return !(x < this.xPos || x > this.xPos + side || z < this.zPos || z > this.zPos + side);
  }
  public boolean isInZone(Location loc) {
    return isInZone(loc.getX(), loc.getZ());
  }

  public void show() {
    //World world = Bukkit.getWorld("world");
    // bottom
    for (int i = this.zPos; i < this.zPos + this.side; i++) {
      // new Location(world, xPos, yPos, i).getBlock().setType(Material.BLACK_STAINED_GLASS);
      map.burn(this.xPos, i);
    }
    // right
    for (int i = this.xPos; i < this.xPos + this.side; i++) {
      // new Location(world, i, yPos, zPos + this.side).getBlock().setType(Material.BLACK_STAINED_GLASS);
      map.burn(i, this.zPos + this.side);
    }
    // left
    for (int i = this.xPos; i < this.xPos + this.side; i++) {
      // new Location(world, i, yPos, this.zPos).getBlock().setType(Material.BLACK_STAINED_GLASS);
      map.burn(i, this.zPos);
    }
    // top
    for (int i = this.zPos; i < this.zPos + this.side; i++) {
      // new Location(world, this.xPos + this.side, yPos, i).getBlock().setType(Material.BLACK_STAINED_GLASS);
      map.burn(this.xPos + this.side, i);
    }
  }
}
