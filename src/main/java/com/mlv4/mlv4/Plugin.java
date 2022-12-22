package com.mlv4.mlv4;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import com.mlv4.mlv4.Powers.PowerListeners;

/*
 * mlv4 java plugin
 */
public class Plugin extends JavaPlugin
{

  public static Logger LOGGER=Logger.getLogger("mlv4");
  public CommandBattleroyale cbr = new CommandBattleroyale();
  private static Plugin instance;
  
  public Plugin() {
    Plugin.instance = this; 
  }

  public void onEnable()
  {
    LOGGER.info("mlv4 enabled");    
    saveDefaultConfig();

    
    getCommand("button").setExecutor(new CommandButton());
    getCommand("battleroyale").setExecutor(cbr);
    getCommand("battleroyale").setTabCompleter(new TabCompleterBattleRoyale());
    getCommand("goto").setExecutor(new CommandGoTo());
    getCommand("goto").setTabCompleter(new TabCompleterGoTo());
    getServer().getPluginManager().registerEvents(new Mlv4Listeners(), this);
    getServer().getPluginManager().registerEvents(new PowerListeners(), this);

    Plugin.instance = this;
  }

  public void onDisable()
  {
    this.cbr.clearGames();
    LOGGER.info("mlv4 disabled");
  }

  public static Plugin getInstance() {
    return Plugin.instance;
  }
}