package com.mlv4.mlv4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import static org.bukkit.Bukkit.getOnlinePlayers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class CommandBattleroyale implements CommandExecutor {
  /**
   *
   */
  Random random = new Random();
  HashSet<Player> players = new HashSet<Player>();
  public HashSet<Game> games = new HashSet<Game>();

  
  public Game gameOf(Player p) {
    Game game = null;
    for (Game g : this.games) {
      if (g.getPlayers().contains(p)) game = g;
    }
    return game;
  }


  public void clearGames() {
    for (Game g : this.games) {
      g.stopGame();
    }
    this.games.clear();
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player && (command.getName().equalsIgnoreCase("battleroyale") || command.getName().equalsIgnoreCase("br"))) {
      Player player = (Player) sender;

      if (args[0].equalsIgnoreCase("radis") && !this.players.contains(player)) {
        this.players.add(player);
        Iterator<Player> playersIterator = players.iterator();
        String playersListString = "";
        while (playersIterator.hasNext()) playersListString += playersIterator.next().getName() + ", ";
        Bukkit.broadcastMessage("§6Les joueurs radis pour le battle royale sont §b" + playersListString);
        return true;
      }

      else if (args[0].equalsIgnoreCase("start")) {
        if (args.length == 1 || !Plugin.getInstance().getConfig().getConfigurationSection("maps").getKeys(false).contains(args[1])) {
          player.sendMessage("§c Tu dois renseigner la map de la game");
          return false;
        }

        final HashSet<int[]> chestLocations = new HashSet<int[]>();
        for (Object loc : Plugin.getInstance().getConfig().getList("maps." + args[1] + ".chestLocations")) {
          if (loc instanceof List<?>) {
            List<Integer> l = (List<Integer>) loc;
            chestLocations.add(new int[] {(int) l.get(0), (int) l.get(1), (int) l.get(2)});
          }
        }


        Map map = new Map(
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".xPos"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".yPos"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".zPos"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".height"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".side"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".spawnHeight"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".yOffsetBetweenMapAndModel"),
          chestLocations
        );
        this.clearGames();

        if (this.players.isEmpty())
          for (Player p : getOnlinePlayers())
            this.players.add(p);
        Game g = new Game(players, map, Plugin.getInstance());
        this.games.add(g);
        g.launch();
        return true;
      }

      else if (args[0].equalsIgnoreCase("stop")) {
        this.clearGames();
        return true;
      }

      else if (args[0].equalsIgnoreCase("register_chests")) {
        if (args.length == 1 || !Plugin.getInstance().getConfig().getConfigurationSection("maps").getKeys(false).contains(args[1])) {
          player.sendMessage("§c Tu dois renseigner la map de la game");
          return false;
        }

        HashSet<int[]> chestLocations = new HashSet<int[]>();
        Plugin.getInstance().getConfig().set("maps." + args[1] + ".chestLocations", null);

        Map map = new Map(
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".xPos"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".yPos"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".zPos"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".height"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".side"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".spawnHeight"),
          Plugin.getInstance().getConfig().getInt("maps." + args[1] + ".yOffsetBetweenMapAndModel"),
          chestLocations
        );

        for (int x = map.xPos; x < map.xPos + map.side; x++) {
          for (int y = map.yPos; y < map.yPos + map.height; y++) {
            for (int z = map.zPos; z < map.zPos + map.side; z++) {
              if (new Location(Bukkit.getWorld("world"), x, y, z).getBlock().getType() == Material.CHEST) {
                chestLocations.add(new int[] {x, y, z});
                Bukkit.broadcastMessage("§aUn coffre trouvé en §b" + x + " / " + y + " / " + z);
              }
            }
          }
        }

        Plugin.getInstance().getConfig().set("maps." + args[1] + ".chestLocations", chestLocations.toArray());

        Plugin.getInstance().saveConfig();
      }
    }    
    return false;
  }
}
