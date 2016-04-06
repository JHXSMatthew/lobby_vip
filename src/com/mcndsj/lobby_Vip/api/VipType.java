package com.mcndsj.lobby_Vip.api;

import org.bukkit.ChatColor;

public enum VipType {

	VIP(1 , ChatColor.GOLD + ChatColor.BOLD.toString()+"VIP")
	,VIP_PLUS(2 , ChatColor.GOLD + ChatColor.BOLD.toString()+"VIP+")
	,MVP(3 , ChatColor.GOLD + ChatColor.BOLD.toString()+"MVP")
	,MVP_PLUS(4 , ChatColor.GOLD + ChatColor.BOLD.toString()+"MVP+");
	
	private int level;
	private String called;
	
	
	VipType(int level, String called){
		this.level = level;
		this.called = called;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public String toString(){
		return this.called;
	}
	
	public static VipType transTypeFromInt(int i){
		for(VipType t : VipType.values()){
			if(t.getLevel() == i){
				return t;
			}
		}
		return null;
	}
}

