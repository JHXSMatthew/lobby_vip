package com.mcndsj.lobby_Vip.Debug;

public class Debug {
	private static boolean isDebug = true;
	
	public static void out(String s){
		if(!isDebug) return;
		System.out.println(s);
	}

}
