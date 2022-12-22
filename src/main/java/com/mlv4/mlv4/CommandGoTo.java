package com.mlv4.mlv4;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGoTo implements CommandExecutor {


  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player && command.getName().equalsIgnoreCase("goto") && args.length >= 1) {
      Player player = (Player) sender;

      if (args[0].equalsIgnoreCase("+")) {
        player.sendMessage("§aLa destination a été enregistrée !");
        Plugin.getInstance().getConfig().createSection("gotoLocations." + args[1]);
        Plugin.getInstance().getConfig().set("gotoLocations." + args[1] + ".world".toLowerCase(), player.getLocation().getWorld().getName());
        Plugin.getInstance().getConfig().set("gotoLocations." + args[1] + ".x".toLowerCase(), Math.round(player.getLocation().getX()));
        Plugin.getInstance().getConfig().set("gotoLocations." + args[1] + ".y".toLowerCase(), Math.round(player.getLocation().getY()));
        Plugin.getInstance().getConfig().set("gotoLocations." + args[1] + ".z".toLowerCase(), Math.round(player.getLocation().getZ()));

        Plugin.getInstance().saveConfig();

        return true;
      }

      else if (args[0].equalsIgnoreCase("-")) {
        player.sendMessage("§aLa destination a été supprimée !");
        Plugin.getInstance().getConfig().set("gotoLocations." + args[1], null);

        Plugin.getInstance().saveConfig();

        return true;
      }

      else for (String destinationName : Plugin.getInstance().getConfig().getConfigurationSection("gotoLocations").getKeys(false)) {
        if (destinationName.equalsIgnoreCase(args[0])) {
          Location destination = new Location(
            Bukkit.getWorld(Plugin.getInstance().getConfig().getString("gotoLocations." + destinationName + ".world")),
            Plugin.getInstance().getConfig().getInt("gotoLocations." + destinationName + ".x"),
            Plugin.getInstance().getConfig().getInt("gotoLocations." + destinationName + ".y"),
            Plugin.getInstance().getConfig().getInt("gotoLocations." + destinationName + ".z")
          );
          player.teleport(destination);

          return true;
        }
      }
    }
    return false;
  }

}
