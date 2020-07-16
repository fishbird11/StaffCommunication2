package com.fishbirddd.staffcommunication;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.fishbirddd.staffcommunication.commands.AdminBroadcast;
import com.fishbirddd.staffcommunication.commands.AdminChat;
import com.fishbirddd.staffcommunication.commands.Broadcast;
import com.fishbirddd.staffcommunication.commands.StaffBroadcast;
import com.fishbirddd.staffcommunication.commands.StaffChat;
import com.fishbirddd.staffcommunication.commands.StaffCommunicationCommand;
import com.fishbirddd.staffcommunication.commands.StaffMessage;
import com.fishbirddd.staffcommunication.utils.Errors;
import com.fishbirddd.staffcommunication.utils.GeneralMethods;

public class StaffCommunication extends JavaPlugin {

	private static StaffCommunication instance;
	public static final String VERSION = "BETA-2.0.0";
	public static final int CONFIG_VERSION = 0;
	
	public void onEnable() {
		
		saveDefaultConfig();
		registerCommands();
		instance = this;
		setVars();
		new UpgradeConfig();
		
	}
	
	private void registerCommands() {
		
		getCommand("staffchat")      	 .setExecutor(new StaffChat());
		getCommand("adminchat")      	 .setExecutor(new AdminChat());
		getCommand("broadcast")      	 .setExecutor(new Broadcast());
		getCommand("staffbroadcast")	 .setExecutor(new StaffBroadcast());
		getCommand("adminbroadcast")	 .setExecutor(new AdminBroadcast());
		getCommand("staffmessage")   	 .setExecutor(new StaffMessage());
		getCommand("staffcommunication") .setExecutor(new StaffCommunicationCommand());
		getCommand("sctoggle")           .setExecutor(new StaffCommunicationCommand());
		getCommand("actoggle")           .setExecutor(new StaffCommunicationCommand());
		
	}
	
	public static StaffCommunication getInstance() {
		
		return instance;
		
	}
	
	public static void setVars() {
		
		Errors        			  .setVars();
		GeneralMethods 			  .setVars();
		UpgradeConfig  			  .setVars();
		StaffCommunicationCommand .setVars();
		
	}
	
	public static void simulateChat(ChatType chatType, Player player, String message) {
		
		switch (chatType) {
		
		case STAFF_CHAT:
			new StaffChat().onCommand(player, instance.getCommand("staffchat"), "staffchat", message.split(" "));
			break;
		case ADMIN_CHAT:
			new AdminChat().onCommand(player, instance.getCommand("adminchat"), "adminchat", message.split(" "));
			break;
		case BROADCAST:
			new Broadcast().onCommand(player, instance.getCommand("broadcast"), "broadcast", message.split(" "));
			break;
		case STAFF_BROADCAST:
			new AdminChat().onCommand(player, instance.getCommand("staffbroadcast"), "staffbroadcast", message.split(" "));
			break;
		case ADMIN_BROADCAST:
			new AdminBroadcast().onCommand(player, instance.getCommand("adminbroadcast"), "adminbroadcast", message.split(" "));
			break;
		case STAFF_MESSAGE:
			simulateChat(chatType, player, player, message);
			break;
		default:
			break;
		
		}
		 
	}
	
	public static void simulateChat(ChatType chatType, Player player, Player receiver, String message) {
		
		if (chatType.equals(ChatType.STAFF_MESSAGE)) {
			
			String[] args = new String[message.split(" ").length + 1];
			args[0] = receiver.getName();
			for (int i = 0; i < args.length - 1; i++) {
				args[i + 1] = message.split(" ")[i];
			}
			new StaffMessage().onCommand(player, instance.getCommand("smsg"), "smsg", args);
			
		}
		 
	}
	
}
