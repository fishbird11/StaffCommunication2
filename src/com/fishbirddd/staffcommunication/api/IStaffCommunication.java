package com.fishbirddd.staffcommunication.api;

import org.bukkit.entity.Player;

import com.fishbirddd.staffcommunication.ChatType;
import com.fishbirddd.staffcommunication.exceptions.InvalidChatTypeException;

public interface IStaffCommunication {
	
	/*
	 * 
	 * To use the StaffCommunication API, you have to do the following:
	 * 
	 * StaffCommunication staffCommunication = StaffCommunication.getInstance();
	 * 
	 * You can then access any of these methods through the "staffCommuication" variable
	 * Example: staffCommunication.getToggledChat(Bukkit.getPlayer("Fishbird"));
	 * 
	 */
	
	/* ================================================================= */
	
    /**
     * 
     * Simulates a chat message of the type STAFF_MESSAGE
     * 
     * @param chatType - The type of chat
     * @param player - The player who sent the message
     * @param receiver - The player receiving the chat
     * @param message - The message
     * 
     * @return void
     * 
     * @since Release 2.0.0
     * 
     */
	public void simulateChat(ChatType chatType, Player player, Player receiver, String message);
	
	/**
	 * 
	 * Simulates a chat message of any type besides STAFF_MESSAGE
	 * 
	 * @param chatType - The type of chat
	 * @param player - The player who sent the message
	 * @param message - The message
	 * 
	 * @return void
	 * 
	 * @since Release 2.0.0
	 * 
	 */
	public void simulateChat(ChatType chatType, Player player, String message);
	
	/**
	 * 
	 * @param player - The player who you want to check the toggle status of
	 * 
	 * @return A chat type that is toggled or null if the player does not have a chat toggled
	 * 
	 * @since Release 2.0.0
	 * 
	 */
	public ChatType getToggledChat(Player player);
	
	/**
	 * 
	 * @param chatType - The type of chat you want to toggle; can only be STAFF_CHAT or ADMIN_CHAT
	 * @param player - The player you want to toggle it for
	 * 
	 * @return void
	 * 
	 * @throws InvalidChatTypeException - If a chat type other than STAFF_CHAT or ADMIN_CHAT is used
	 * 
	 * @since Release 2.0.0
	 * 
	 */
	public void toggleChat(ChatType chatType, Player player) throws InvalidChatTypeException;
	
	/**
	 * 
	 * @return Whether or not debug is enabled
	 * 
	 * @since Release 2.0.0
	 * 
	 */
	public boolean debugEnabled();
	
}
