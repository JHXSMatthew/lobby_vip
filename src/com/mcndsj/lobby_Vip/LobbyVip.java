package com.mcndsj.lobby_Vip;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bukkit.plugin.java.JavaPlugin;

import com.mcndsj.lobby_Vip.api.API;
import com.mcndsj.lobby_Vip.api.SetVipEvent;
import com.mcndsj.lobby_Vip.api.VipType;

public class LobbyVip extends JavaPlugin {
	
	private static LobbyVip instance;
	private ExecutorService pool ;
	private API api = null;
	
	private ConcurrentHashMap<String,Integer> cache;

	public void onEnable(){
		instance = this;
		pool = Executors.newSingleThreadExecutor();
		cache = new ConcurrentHashMap<String,Integer>();
		api = new API();
		this.getServer().getPluginManager().registerEvents(new Listeners(), this);
	}
	
	
	public static API getApi(){
		return instance.api;
	}
	
	public static LobbyVip get(){
		return instance;
	}
	
	public ExecutorService getPool(){
		return this.pool;
	}
	
	
	public ConcurrentHashMap<String,Integer> getCache(){
		return cache;
	}
	
	public boolean isVip(String name){
		return getVipLevel(name) > 0;
	}
	
	public int getVipLevel(String name){
		if(cache.containsKey(name)){
			return cache.get(name) ;
		}else{
			return SQLUtils.getVipLevel(name) ;
		}
	}
	
	public void setVip(SetVipEvent call,String name,VipType type){
		pool.execute(new Runnable(){
			@Override
			public void run() {
				try {
					SQLUtils.setVipLevel(name, type.getLevel(), !isVip(name));
					call.callBack(true);
				} catch (Exception e) {
					call.callBack(false);
					e.printStackTrace();
				}
			}
		});
	}

}
