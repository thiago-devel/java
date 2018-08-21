package com.rubyit.metaltrade;

import static com.rubyit.metaltrade.Utils.getGson;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public abstract class Trader {

	protected Wallet myWallet;
	protected String name;
	protected String ID;
	
	Trader(String name) {
		myWallet = new Wallet();
		this.name = name;
		ID = UUID.randomUUID().toString();
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("name", name);
		json.put("myWallet", myWallet);
		return getGson().toJson(json);
	}
}
