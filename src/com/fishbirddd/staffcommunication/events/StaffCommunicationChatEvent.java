package com.fishbirddd.staffcommunication.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.fishbirddd.staffcommunication.ChatType;

public class StaffCommunicationChatEvent extends Event implements Cancellable {

	public StaffCommunicationChatEvent(ChatType chatType, Player sender, String message) {
		
		this.chatType = chatType;
		this.sender = sender;
		this.message = message;
		
	}
	
	private boolean cancelled = false;
	
	public boolean isCancelled() { return cancelled; }
	
	public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
	
	private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
    
    
    ChatType chatType;
    Player sender;
    String message;
    
    public ChatType getChatType() { return this.chatType; }
    
    public Player getSender() { return this.sender; }
    public void setSender(Player sender) { this.sender = sender; }
    
    public String getMessage() { return this.message; }
    public void setMessage(String message) { this.message = message; }
	
}
