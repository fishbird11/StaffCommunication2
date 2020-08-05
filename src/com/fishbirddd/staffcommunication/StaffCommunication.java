package com.fishbirddd.staffcommunication;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.fishbirddd.staffcommunication.api.IStaffCommunication;
import com.fishbirddd.staffcommunication.commands.AdminBroadcast;
import com.fishbirddd.staffcommunication.commands.AdminChat;
import com.fishbirddd.staffcommunication.commands.Broadcast;
import com.fishbirddd.staffcommunication.commands.StaffBroadcast;
import com.fishbirddd.staffcommunication.commands.StaffChat;
import com.fishbirddd.staffcommunication.commands.StaffCommunicationCommand;
import com.fishbirddd.staffcommunication.commands.StaffMessage;
import com.fishbirddd.staffcommunication.commands.Toggles;
import com.fishbirddd.staffcommunication.exceptions.InvalidChatTypeException;
import com.fishbirddd.staffcommunication.listeners.ChatSend;
import com.fishbirddd.staffcommunication.utils.Errors;
import com.fishbirddd.staffcommunication.utils.GeneralMethods;

public final class StaffCommunication extends JavaPlugin implements IStaffCommunication {

	private static StaffCommunication instance;
	public static final String VERSION = "Release-2.0.0";
	public static final int CONFIG_VERSION = 1;
	public static HashMap<Player, ChatType> chatToggled = new HashMap<>();
	
	/**
	 * 
	 * Please use StaffCommunication.getInstance() instead of the constructor
	 * 
	 */
	public StaffCommunication() {}
	
	@Override
	public void onEnable() {
		
		saveDefaultConfig();
		registerCommands();
		instance = this;
		setVars();
		new UpgradeConfig();
		getServer().getPluginManager().registerEvents(new ChatSend(), this);
		
	}
	
	@Override
	public void onDisable() {
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			
			if (chatToggled.containsKey(p)) {
				
				try {
					
					this.toggleChat(chatToggled.get(p), p);
					
				} catch (InvalidChatTypeException e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		}
		
	}
	
	private void registerCommands() {
		
		getCommand("staffchat")      	 .setExecutor(new StaffChat());
		getCommand("adminchat")      	 .setExecutor(new AdminChat());
		getCommand("broadcast")      	 .setExecutor(new Broadcast());
		getCommand("staffbroadcast")	 .setExecutor(new StaffBroadcast());
		getCommand("adminbroadcast")	 .setExecutor(new AdminBroadcast());
		getCommand("staffmessage")   	 .setExecutor(new StaffMessage());
		getCommand("staffcommunication") .setExecutor(new StaffCommunicationCommand());
		getCommand("sctoggle")           .setExecutor(new Toggles());
		getCommand("actoggle")           .setExecutor(new Toggles());
		
	}
	
	public static StaffCommunication getInstance() {
		
		return instance;
		
	}
	
	public static void setVars() {
		
		Errors        			  .setVars();
		GeneralMethods 			  .setVars();
		UpgradeConfig  			  .setVars();
		StaffCommunicationCommand .setVars();
		Toggles                   .setVars();
		
	}
	
	@Override
	public void simulateChat(ChatType chatType, Player player, String message) {
		
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
		
		// Debug
		
		if (this.debugEnabled()) {
			
			Bukkit.getLogger().info("A plugin has attempted to simulate a chat message for type " + chatType.toString() 
				+ ", player " + player.getName() 
				+ ", and message \"" + message + "\"");
			
		}
		 
	}
	
	@Override
	public void simulateChat(ChatType chatType, Player player, Player receiver, String message) {
		
		if (chatType.equals(ChatType.STAFF_MESSAGE)) {
			
			String[] args = new String[message.split(" ").length + 1];
			args[0] = receiver.getName();
			for (int i = 0; i < args.length - 1; i++) {
				args[i + 1] = message.split(" ")[i];
			}
			new StaffMessage().onCommand(player, instance.getCommand("smsg"), "smsg", args);
			
		}
		
		// Debug
		
		if (this.debugEnabled()) {
			
			Bukkit.getLogger().info("A plugin has attempted to simulate a chat message for type " + chatType.toString() 
				+ ", player " + player.getName() 
				+ ", receiver " + receiver.getName()
				+ ", and message \"" + message + "\"");
			
		}
		 
	}

	@Override
	public ChatType getToggledChat(Player player) {
		
		return StaffCommunication.chatToggled.get(player);
		
	}
	
	@Override
	public void toggleChat(ChatType chatType, Player player) throws InvalidChatTypeException {
		
		switch (chatType) {
		
		case STAFF_CHAT:
			new Toggles().onCommand(player, instance.getCommand("sctoggle"), "sctoggle", new String[0]);
			break;
		case ADMIN_CHAT:
			new Toggles().onCommand(player, instance.getCommand("actoggle"), "actoggle", new String[0]);
			break;
		default:
			throw new InvalidChatTypeException(chatType);
		
		}
		
		// Debug
		
		if (this.debugEnabled()) {
			
			Bukkit.getLogger().info("A plugin has attempted to toggle " + chatType.toString() + " for player " + player.getName());
			
		}
		
	}
	
	@Override
	public boolean debugEnabled() {
		
		return getConfig().getBoolean("settings.debug");
		
	}
	
}
