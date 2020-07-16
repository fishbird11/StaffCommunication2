package com.fishbirddd.staffcommunication.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.fishbirddd.staffcommunication.StaffCommunication;
import com.fishbirddd.staffcommunication.events.StaffCommunicationChatEvent;
import com.fishbirddd.staffcommunication.events.StaffCommunicationStaffMessageEvent;

public final class GeneralMethods {

	private static Plugin plugin;
	private static FileConfiguration config;
	
	public static void setVars() {
		
		plugin = StaffCommunication.getInstance();
		config = plugin.getConfig();
		
	} 
	
	private GeneralMethods() {}
	
	public static String colorize(String string) {
		
		return ChatColor.translateAlternateColorCodes('&', string);
		
	}
	
	public static String argsToString(String[] args) {
		
		String message = "";
		for (String s : args) { if (s!="") { message += s + " "; } }
		if (message.charAt(message.length()-1) == ' ') { message = message.substring(0, message.length() - 1); }
		
		return message;
		
	}
	
	public static void broadcastMessageByPermission(StaffCommunicationChatEvent event, Player player, String permission, String message, String format) {
		
		if (!event.isCancelled()) {
			
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				
				if (p.hasPermission(permission)) {
					
					p.sendMessage(GeneralMethods.colorize(VariableHandling.translateVariables(config.getString(format), player, message)));
					
				}
				
			}
			
		}
		
	}
	
	public static void sendStaffMessage(StaffCommunicationStaffMessageEvent event, Player player, Player receiver, String message) {
		
		if (!event.isCancelled()) {
			
			receiver.sendMessage(VariableHandling.translateVariables(plugin.getConfig().getString("messages.staffmessage.receiver"), player, message));
			player.sendMessage(VariableHandling.translateVariables(plugin.getConfig().getString("messages.staffmessage.sender"), receiver, message));
			
		}
		
	}
	
}