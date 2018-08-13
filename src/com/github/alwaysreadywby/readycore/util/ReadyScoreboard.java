package com.github.alwaysreadywby.readycore.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.github.alwaysreadywby.readycore.player.PlayerHash;

public class ReadyScoreboard{
	
	private Hashtable<UUID, Scoreboard> hashPlayerDisp;
	private Hashtable<String, ReadyScoreboardItem> hashItem;
	private Hashtable<UUID, Boolean> showScb;
	
	public ReadyScoreboard() {
		hashPlayerDisp=new Hashtable<UUID, Scoreboard>();
		hashItem=new Hashtable<String, ReadyScoreboardItem>();
		showScb=new Hashtable<UUID, Boolean>();
	}
	
	public void showFor(Player p) {
		UUID id=p.getUniqueId();
		if(!hashPlayerDisp.containsKey(id)) {
			hashPlayerDisp.put(id, Bukkit.getScoreboardManager().getNewScoreboard());
			hashPlayerDisp.get(id).registerNewObjective("side","dummy");
			hashPlayerDisp.get(id).getObjective("side").setDisplaySlot(DisplaySlot.SIDEBAR);
			hashPlayerDisp.get(id).getObjective("side").setDisplayName(p.getName());
			hashPlayerDisp.get(id).registerNewObjective("list","dummy");
			hashPlayerDisp.get(id).getObjective("list").setDisplaySlot(DisplaySlot.PLAYER_LIST);
			hashPlayerDisp.get(id).getObjective("list").setDisplayName("");
			hashPlayerDisp.get(id).registerNewObjective("name","dummy");
			hashPlayerDisp.get(id).getObjective("name").setDisplaySlot(DisplaySlot.BELOW_NAME);
			hashPlayerDisp.get(id).getObjective("name").setDisplayName("");
		}
		p.setScoreboard(hashPlayerDisp.get(id));
		showScb.put(id, true);
	}

	public void hideFor(Player p) {
		p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		showScb.put(p.getUniqueId(), false);
	}
	
	public void toggleFor(Player p) {
		UUID id=p.getUniqueId();
		if(showScb.containsKey(id) && showScb.get(id)) {
			hideFor(p);
		}else {
			showFor(p);
		}
	}

	public void update() {
		Set<UUID> lId=hashPlayerDisp.keySet();
		Scoreboard scbTmp;
		Set<String> tmp=hashItem.keySet();
		Player p;
		for(UUID id:lId) {
			if(PlayerHash.hasPlayer(id)) {
				scbTmp=hashPlayerDisp.get(id);
				p=PlayerHash.getPlayer(id);
				for(String key:tmp) {
					ReadyScoreboardItem item=hashItem.get(key);
					scbTmp.getObjective(item.getSlot()).getScore(Bukkit.getOfflinePlayer(item.getDisp())).setScore(1);
					scbTmp.getObjective(item.getSlot()).getScore(Bukkit.getOfflinePlayer(item.getDisp())).setScore(item.getScore(p));
				}
			}
		}
	}
	
	public ReadyScoreboardItem getItem(String name) {
		return hashItem.get(name);
	}
	
	public ReadyScoreboardItem registerNewItem(String name,String disp, DisplaySlot slot) {
		ReadyScoreboardItem ret=new ReadyScoreboardItem(name,disp,slot);
		hashItem.put(name, ret);
		return ret;
	}

}
