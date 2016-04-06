package com.mcndsj.lobby_Vip.api;

public interface APIInterface {

	/**
	 * @param name
	 * @return
	 */
	public boolean isVip(String name);
	
	/**
	 * @param name
	 * @return null if not vip
	 */
	public VipType getvipType(String name);
	
	/**
	 * @param callback
	 * @param name
	 * @param type
	 * @return
	 */
	public void setVip(SetVipEvent callback,String name, VipType type);

}
