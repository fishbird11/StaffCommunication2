package com.fishbirddd.staffcommunication.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.fishbirddd.staffcommunication.StaffCommunication;

public final class Errors {

	private Errors() {}
	
	private static Plugin plugin;
	private static FileConfiguration config;
	
	public static void setVars() {
		
		plugin = StaffCommunication.getInstance();
		config = plugin.getConfig();
		
	}
	
	public static void sendPermissionError(Player player) {
		
		player.sendMessage(VariableHandling.translateVariables(config.getString("messages.errors.permission"), player));
		
	}
	
	public static void sendUsageError(Player player, String usage) {
		
		player.sendMessage(VariableHandling.translateVariablesUsage(config.getString("messages.errors.usage"), player, usage));
		
	}
	
	public static void sendPlayerError(Player player) {
		
		player.sendMessage(VariableHandling.translateVariables(config.getString("messages.errors.player"), player));
		
	}
	
}
