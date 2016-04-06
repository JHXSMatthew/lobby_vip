package com.mcndsj.lobby_Vip;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPreJoin(AsyncPlayerPreLoginEvent evt){
		final String name = evt.getName();
		LobbyVip.get().getCache().put(name, SQLUtils.getVipLevel(name));
	
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent evt){
		LobbyVip.get().getCache().remove(evt.getPlayer().getName());
	}
}
