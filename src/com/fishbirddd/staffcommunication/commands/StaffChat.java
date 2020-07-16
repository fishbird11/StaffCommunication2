package com.fishbirddd.staffcommunication.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fishbirddd.staffcommunication.ChatType;
import com.fishbirddd.staffcommunication.events.StaffCommunicationChatEvent;
import com.fishbirddd.staffcommunication.utils.Errors;
import com.fishbirddd.staffcommunication.utils.GeneralMethods;

public class StaffChat implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if (args.length >= 1) {
				
				if (player.hasPermission("staffcommunication.sc.use")) {
				
					String message = GeneralMethods.argsToString(args);
					
					StaffCommunicationChatEvent event = new StaffCommunicationChatEvent(ChatType.STAFF_CHAT, player, message);
					Bukkit.getPluginManager().callEvent(event);
					
					GeneralMethods.broadcastMessageByPermission(event, player, "staffcommunication.sc.see", message, "messages.chats.staff");
				
				} else {
					
					Errors.sendPermissionError(player);
					
				}
				
			} else {
				
				Errors.sendUsageError(player, "/sc <message>");
				
			}
			
		} else {
			
			sender.sendMessage(ChatColor.RED + "[StaffCommunication] That command can be used by players only!");
			
		}
		
		return true;
		
	}
	
}
