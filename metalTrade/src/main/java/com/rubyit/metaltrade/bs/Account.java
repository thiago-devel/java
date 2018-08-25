package com.rubyit.metaltrade.bs;

import static com.rubyit.metaltrade.Utils.getGson;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class Account {
	
	private Wallet myWallet;
	private String ID;
	
	Account() {
		myWallet = new Wallet();
		ID = UUID.randomUUID().toString();
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("myWallet", myWallet);
		return getGson().toJson(json);
	}
	
	public Wallet getWallet() {
		return myWallet;
	}
	
	public String getID() {
		return ID;
	}
}