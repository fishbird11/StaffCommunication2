package com.fishbirddd.staffcommunication;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public final class UpgradeConfig {

	private static StaffCommunication plugin;
	private static FileConfiguration config;
	
	public static void setVars() {
		
		plugin = StaffCommunication.getInstance();
		config = plugin.getConfig();
		
	} 
	
	public UpgradeConfig() {
		
		final int currentVersion = config.getInt("do-not-edit.config-version");
		
		if (currentVersion > StaffCommunication.CONFIG_VERSION) {
			
			Bukkit.getServer().getLogger().warning("[StaffCommunication " + StaffCommunication.VERSION + "] " 
					+ "Your config.yml file (config version " 
					+ currentVersion + ") is newer than the default config of your version of StaffCommunication (config version " 
					+ StaffCommunication.CONFIG_VERSION + ") you are running. You may experience issues.");
			
		}
		
		if (currentVersion < StaffCommunication.CONFIG_VERSION) {
			
			Bukkit.getLogger().warning("=== StaffCommunication ===");
			Bukkit.getLogger().warning("Your config is out of date! Please refer to my wiki: https://github.com/fishbird11/StaffCommunication2/wiki/Upgrading-Your-Config");
			Bukkit.getLogger().warning("==========================");
			
		}
		
		
	}
	
}
