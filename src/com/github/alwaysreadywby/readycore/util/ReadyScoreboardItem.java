package com.github.alwaysreadywby.readycore.util;

import java.util.Hashtable;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ReadyScoreboardItem{
	
	private DisplaySlot eSlot;
	private String sName;
	private String sDisp;
	private Hashtable<OfflinePlayer, Integer> hashScore;
	
	public ReadyScoreboardItem(String name,String disp,DisplaySlot slot) {
		sName=name;
		sDisp=disp;
		eSlot=slot;
		hashScore=new Hashtable<OfflinePlayer, Integer>();
	}
	
	public ReadyScoreboardItem(String name,String disp) {
		sName=name;
		sDisp=disp;
		eSlot=DisplaySlot.SIDEBAR;
	}
	
	public String getName() {
		return sName;
	}
	
	public String getDisp() {
		return sDisp;
	}
	
	public void setDisp(String disp) {
		sDisp=disp;
	}

	public DisplaySlot getSlot() {
		return eSlot;
	}
	
	public void setSlot(DisplaySlot slot) {
		eSlot=slot;
	}
	
	public void setScore(OfflinePlayer p,int i) {
		hashScore.put(p,i);
	}
	
	public int getScore(OfflinePlayer p) {
		return hashScore.getOrDefault(p, 0);
	}
}
