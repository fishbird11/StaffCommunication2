package com.fishbirddd.staffcommunication.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import com.fishbirddd.staffcommunication.StaffCommunication;
import com.fishbirddd.staffcommunication.UpgradeConfig;
import com.fishbirddd.staffcommunication.utils.GeneralMethods;

public class StaffCommunicationCommand implements CommandExecutor {

	private static Plugin plugin;
	private static FileConfiguration config;
	
	public static void setVars() {
		
		plugin = StaffCommunication.getInstance();
		config = plugin.getConfig();
		
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (command.getName().equals("sctoggle") || command.getName().equals("actoggle")) {
			
			sender.sendMessage(ChatColor.RED + "This command is not available in the beta version of the plugin. It will be added in the official release.");
			
			return true;
			
		}
		
		if (args.length >= 1) {
			
			if (args[0].equals("version")) {
				
				if (sender.hasPermission("staffcommunication.version")) {
					
					sender.sendMessage(GeneralMethods.colorize(config.getString("messages.version").replaceAll("%version%", StaffCommunication.VERSION)));
					
				}
				
			} else if (args[0].equals("reload")) {
				
				if (sender.hasPermission("staffcommunication.reload")) {
					
					sender.sendMessage(GeneralMethods.colorize(config.getString("messages.reload").replaceAll("%version%", StaffCommunication.VERSION)));
					plugin.reloadConfig();
					StaffCommunication.setVars();
					new UpgradeConfig();
					
				}
				
			} else {
				
				sendUsageError(sender);
				
			}
			
		} else {
			
			sendUsageError(sender);
			
		}
		
		return true;
		
	}
	
	private void sendUsageError(CommandSender sender) {
		
		sender.sendMessage(GeneralMethods.colorize(config.getString("messages.errors.usage").replaceAll("%usage%", "/staffcommunication <version|reload>")));
		
	}
	
}
