package com.mlv4.mlv4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompleterGoTo implements TabCompleter {

  private Set<String> args1; 

  public TabCompleterGoTo() {
    args1 = Plugin.getInstance().getConfig().getConfigurationSection("gotoLocations").getKeys(false);
    args1.add("+");
    args1.add("-");
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    List<String> completions = new ArrayList<>();
    if (args.length == 1) {
      for (String destination : args1) {
        if (destination.startsWith(args[0].toLowerCase())) {
          completions.add(destination);
        }
      }
    }
    return completions;
  }
}
