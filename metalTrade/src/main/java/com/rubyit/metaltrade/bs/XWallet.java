package com.rubyit.metaltrade.bs;

import static com.rubyit.metaltrade.Utils.getGson;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.rubyit.metaltrade.obj.AssetType;

public class XWallet {

	transient private Wallet wallet;
	
	public XWallet() {
		wallet = new Wallet();
	}
	
	public XMyAsset getAsset(final AssetType asset) {
		
		return null;
	}
	
	public Set<XMyAsset> getAssets() {
		return null;
	}
	
	public String getID() {
		return wallet.getID();
	}
	
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", getID());
		json.put("assets", getAssets());
		return getGson().toJson(json);
	}
}
