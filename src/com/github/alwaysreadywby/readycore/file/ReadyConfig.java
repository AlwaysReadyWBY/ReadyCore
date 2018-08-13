package com.github.alwaysreadywby.readycore.file;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ReadyConfig {
	
	public abstract void loadDefault();
	
	private YamlConfiguration fData;
	private File fPath;
	private JavaPlugin iPlug;
	private ConfigSaveTask tAutoSave;
	private int nSaveInterval;
	
	public ReadyConfig(JavaPlugin plug, File file) {
		iPlug=plug;
		fPath=file;
		load();
		tAutoSave=new ConfigSaveTask(this);
		if(nSaveInterval>0) {
			iPlug.getServer().getScheduler().scheduleSyncDelayedTask(iPlug, tAutoSave, nSaveInterval);
		}
	}
	
	public void save() {
		try {
			fData.save(fPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			iPlug.getLogger().info("Error trying to save config file \""+fPath.getName()+"\"");
		}
	}
	
	public void load() {
		fData=YamlConfiguration.loadConfiguration(fPath);
		writeConfig(30,"auto-save",false);
		loadDefault();
		if(!fPath.exists()) {
			try {
				fData.save(fPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				iPlug.getLogger().info("Error trying to create config file \""+fPath.getName()+"\"");
			}
		}
		nSaveInterval=fData.getInt("auto-save")*20;
	}
	
	public YamlConfiguration getData() {
		return fData;
	}
	
	public void writeConfig(Object data,String path,boolean overWrite) {
		if(overWrite || !fData.contains(path)) {
			fData.set(path, data);
		}
	}
	
	public void writeConfig(Object data,String path) {
		writeConfig(data,path,true);
	}
	
	private class ConfigSaveTask implements Runnable{
		
		private ReadyConfig fConf;
		
		public ConfigSaveTask(ReadyConfig conf) {
			fConf=conf;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			fConf.save();
			fConf.iPlug.getServer().getScheduler().scheduleSyncDelayedTask(fConf.iPlug, this, fConf.nSaveInterval);
		}
		
	}
}
