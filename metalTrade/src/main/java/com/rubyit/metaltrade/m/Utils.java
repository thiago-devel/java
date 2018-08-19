package com.rubyit.metaltrade.m;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class Utils {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static Gson getGson() {
				
		return gson;
	}
}
