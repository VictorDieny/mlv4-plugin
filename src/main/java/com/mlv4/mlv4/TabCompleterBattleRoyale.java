package com.mlv4.mlv4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompleterBattleRoyale implements TabCompleter {

  private Set<String> args1 = new HashSet<String>() {{
    add("radis");
    add("start");
    add("stop");
    add("register_chests");
  }};
  private Set<String> args2start;

  private List<String> filterMatches(String arg, Set<String> availableArgs) {
    List<String> comp = new ArrayList<String>();
    for (String a : availableArgs) {
      if (a.startsWith(arg.toLowerCase())) {
        comp.add(a);
      }
    }
    return comp;
  }


  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    args2start = Plugin.getInstance().getConfig().getConfigurationSection("maps").getKeys(false);

    List<String> completions = new ArrayList<>();
    if (args.length == 1 && !args1.contains(args[0])) completions = filterMatches(args[0], args1);

    if (args.length == 2) completions = filterMatches(args[1], args2start);
    

    return completions;
  }
}
