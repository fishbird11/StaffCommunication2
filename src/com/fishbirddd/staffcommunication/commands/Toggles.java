package com.fishbirddd.staffcommunication.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.fishbirddd.staffcommunication.ChatType;
import com.fishbirddd.staffcommunication.StaffCommunication;
import com.fishbirddd.staffcommunication.utils.GeneralMethods;

public class Toggles implements CommandExecutor {

	private static StaffCommunication plugin;
	private static FileConfiguration config;
	
	public static void setVars() {
		
		plugin = StaffCommunication.getInstance();
		config = plugin.getConfig();
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
		
			Player player = (Player) sender;
			
			if (cmd.getName().equals("sctoggle")) {
				
				ChatType toggledChat = plugin.getToggledChat(player);
				
				boolean toggled = toggledChat != null && toggledChat.equals(ChatType.STAFF_CHAT);
				
				if (toggled) {
					
					StaffCommunication.chatToggled.remove(player);
					
					player.sendMessage(GeneralMethods.colorize(config.getString("messages.toggles.staff.disable")));
					
				} else if (toggledChat == null) {
					
					StaffCommunication.chatToggled.put(player, ChatType.STAFF_CHAT);
					
					player.sendMessage(GeneralMethods.colorize(config.getString("messages.toggles.staff.enable")));
					
				} else if (toggledChat.equals(ChatType.ADMIN_CHAT)) {
					
					StaffCommunication.chatToggled.put(player, ChatType.STAFF_CHAT);
					
					player.sendMessage(GeneralMethods.colorize(config.getString("messages.toggles.admin.disable")));
					player.sendMessage(GeneralMethods.colorize(config.getString("messages.toggles.staff.enable")));
					
				}
				
			} else if (cmd.getName().equals("actoggle")) {
				
				ChatType toggledChat = plugin.getToggledChat(player);
				
				boolean toggled = toggledChat != null && toggledChat.equals(ChatType.ADMIN_CHAT);
				
				if (toggled) {
					
					StaffCommunication.chatToggled.remove(player);
					
					player.sendMessage(GeneralMethods.colorize(config.getString("messages.toggles.admin.disable")));
					
				} else if (toggledChat == null) {
					
					StaffCommunication.chatToggled.put(player, ChatType.ADMIN_CHAT);
					
					player.sendMessage(GeneralMethods.colorize(config.getString("messages.toggles.admin.enable")));
					
				} else if (toggledChat.equals(ChatType.STAFF_CHAT)) {
					
					StaffCommunication.chatToggled.put(player, ChatType.ADMIN_CHAT);
					
					player.sendMessage(GeneralMethods.colorize(config.getString("messages.toggles.staff.disable")));
					player.sendMessage(GeneralMethods.colorize(config.getString("messages.toggles.admin.enable")));
					
				}
				
			}
		
		}
			
		return true;
	}
	
}
