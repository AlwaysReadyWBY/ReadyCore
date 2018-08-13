package com.github.alwaysreadywby.readycore.player;

import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerHash {

	private static Hashtable<UUID, Player> hashId=null;
	
	public static Player getPlayer(UUID id) {
		return hashId.get(id);
	}
	
	public static void addPlayer(Player p) {
		hashId.put(p.getUniqueId(), p);
	}
	
	public static boolean hasPlayer(Player p) {
		return hashId.containsKey(p.getUniqueId());
	}
	public static boolean hasPlayer(UUID id) {
		return hashId.containsKey(id);
	}

	public static void init() {
		if(hashId==null) {
			hashId=new Hashtable<UUID, Player>();
		}
	}
}
