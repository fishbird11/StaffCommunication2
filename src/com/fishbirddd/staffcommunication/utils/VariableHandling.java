package com.fishbirddd.staffcommunication.utils;

import org.bukkit.entity.Player;

import com.fishbirddd.staffcommunication.StaffCommunication;

public final class VariableHandling {

	private VariableHandling() {}
	
	public static String translateVariables(String string, Player player) {
		
		return GeneralMethods.colorize(string.replaceAll("%player%", player.getName()).replaceAll("%version%", StaffCommunication.VERSION));
		
	}
	
	public static String translateVariables(String string, Player player, String message) {
		
		return GeneralMethods.colorize(string.replaceAll("%player%", player.getName()).replaceAll("%message%", message).replaceAll("%version%", StaffCommunication.VERSION));
		
	}
	
	public static String translateVariablesUsage(String string, Player player, String usage) {
		
		return GeneralMethods.colorize(string.replaceAll("%player%", player.getName()).replaceAll("%usage%", usage).replaceAll("%version%", StaffCommunication.VERSION));
		
	}
	
}
