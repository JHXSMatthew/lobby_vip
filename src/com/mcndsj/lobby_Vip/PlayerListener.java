package com.mcndsj.lobby_Vip;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerListener implements Listener {
    private ExecutorService pool = null;
    
    public PlayerListener(){
        pool = Executors.newSingleThreadExecutor();
    }
    
	@EventHandler(priority = EventPriority.MONITOR)
	public void onJoinFail(PlayerLoginEvent evt){
		if(evt.getResult() != PlayerLoginEvent.Result.ALLOWED){
            return;
        }
        pool.execute(new Runnable() {
            public void run() {
        		LobbyVip.get().getCache().put(evt.getPlayer().getName(), SQLUtils.getVipLevel(evt.getPlayer().getName()));
            }
        });
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent evt){
		LobbyVip.get().getCache().remove(evt.getPlayer().getName());
	}
}
