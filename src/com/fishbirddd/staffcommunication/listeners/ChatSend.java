package com.fishbirddd.staffcommunication.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.fishbirddd.staffcommunication.StaffCommunication;

public class ChatSend implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		
		if (StaffCommunication.chatToggled.get(e.getPlayer()) != null) {
			
			e.setCancelled(true);
			
			Bukkit.getScheduler().runTask(JavaPlugin.getPlugin(StaffCommunication.class), new Runnable() {
				
				@Override
				public void run() {
					
					StaffCommunication.getInstance().simulateChat(StaffCommunication.chatToggled.get(e.getPlayer()), e.getPlayer(), e.getMessage());
					
				}
				
			});
			
		}
		
	}
	
}
