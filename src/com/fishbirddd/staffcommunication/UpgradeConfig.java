package com.fishbirddd.staffcommunication;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public final class UpgradeConfig {

	private static Plugin plugin;
	
	public static void setVars() {
		
		plugin = StaffCommunication.getInstance();
		
	} 
	
	public UpgradeConfig() {
		
		while (StaffCommunication.CONFIG_VERSION != plugin.getConfig().getInt("do-not-edit.config-version")) {
			
			final int currentVersion = plugin.getConfig().getInt("do-not-edit.config-version");
			
			if (currentVersion > StaffCommunication.CONFIG_VERSION) {
				
				Bukkit.getServer().getLogger().warning("[StaffCommunication " + StaffCommunication.VERSION + "] " 
						+ "Your config.yml file (config version " 
						+ currentVersion + ") is newer than the default config of your version of StaffCommunication (config version " 
						+ StaffCommunication.VERSION + ") you are running. You may experience issues.");
				
			}
			
		}
		
	}	
	
}
