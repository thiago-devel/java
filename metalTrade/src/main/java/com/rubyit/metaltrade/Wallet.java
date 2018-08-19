package com.rubyit.metaltrade;

import static com.rubyit.metaltrade.m.Utils.getGson;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.rubyit.metaltrade.m.Asset;
import com.rubyit.metaltrade.m.MyAsset;

public class Wallet {
	private String ID;
	private Set<MyAsset> assets;
	transient private Lock assetChangeLock;

	Wallet() {
		ID = UUID.randomUUID().toString();
		assets = new HashSet<>();
		assetChangeLock = new ReentrantLock();
	}
	
	public void update(final Transaction tx) throws InterruptedException {
		assetChangeLock.lock();
		try {
			final MyAsset asset = getExtractAssetBy(tx.getAsset());
			
			if (tx.getType() == com.rubyit.metaltrade.Transaction.Type.DEPOSIT) {
				asset.deposit(tx.getAmount());
			} else {
				asset.withdraw(tx.getAmount());
			}
			addAsset(asset);
		} finally {
			assetChangeLock.unlock();
		}
	}
	
	private MyAsset getExtractAssetBy(final Asset asset) {
		
		for (MyAsset myAsset : assets) {
			if (myAsset.getAsset().equals(asset)) {
				assets.remove(myAsset);
				return myAsset;
			}
		}
		
		return new MyAsset(asset);
	}
	
	private void addAsset(final MyAsset newAsset) {
		
		assets.add(newAsset);
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("assets", assets);
		return getGson().toJson(json);
	}
}
