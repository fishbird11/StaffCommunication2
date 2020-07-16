package com.fishbirddd.staffcommunication.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fishbirddd.staffcommunication.ChatType;
import com.fishbirddd.staffcommunication.events.StaffCommunicationStaffMessageEvent;
import com.fishbirddd.staffcommunication.utils.Errors;
import com.fishbirddd.staffcommunication.utils.GeneralMethods;

public class StaffMessage implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if (Bukkit.getPlayer(args[0]) != null) {
				
				Player receiver = Bukkit.getPlayer(args[0]);
			
				if (args.length >= 2) {
					
					if (player.hasPermission("staffcommunication.bc.use")) {
					
						args[0] = "";
						String message = GeneralMethods.argsToString(args);
						
						StaffCommunicationStaffMessageEvent event = new StaffCommunicationStaffMessageEvent(ChatType.STAFF_MESSAGE, player, receiver, message);
						Bukkit.getPluginManager().callEvent(event);
						
						GeneralMethods.sendStaffMessage(event, player, receiver, message);
					
					} else {
						
						Errors.sendPermissionError(player);
						
					}
					
				} else {
					
					Errors.sendUsageError(player, "/bc <message>");
					
				}
				
			} else {
				
				Errors.sendPlayerError(player);
				
			}
			
		} else {
			
			sender.sendMessage(ChatColor.RED + "[StaffCommunication] That command can be used by players only!");
			
		}
		
		return true;
		
	}
	
}
