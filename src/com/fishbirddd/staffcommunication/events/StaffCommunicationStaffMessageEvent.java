package com.fishbirddd.staffcommunication.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import com.fishbirddd.staffcommunication.ChatType;

public class StaffCommunicationStaffMessageEvent extends StaffCommunicationChatEvent implements Cancellable {
	
	public StaffCommunicationStaffMessageEvent(ChatType chatType, Player sender, Player receiver, String message) {
		
		super(chatType, sender, message);
		
		this.receiver = receiver;
		
	}

	Player receiver;
	
	public Player getReceiver() { return this.receiver; }
	public void setReceiver(Player receiver) { this.receiver = receiver; }
	
}
