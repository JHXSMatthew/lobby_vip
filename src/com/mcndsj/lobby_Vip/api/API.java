package com.mcndsj.lobby_Vip.api;

import com.mcndsj.lobby_Vip.LobbyVip;

public class API implements APIInterface{


	@Override
	public boolean isVip(String name) {
		return LobbyVip.get().isVip(name);
	}

	@Override
	public VipType getvipType(String name) {
		return VipType.transTypeFromInt(LobbyVip.get().getVipLevel(name));
	}

	@Override
	public void setVip(SetVipEvent callback, String name, VipType type) {
		
	}


	

}
