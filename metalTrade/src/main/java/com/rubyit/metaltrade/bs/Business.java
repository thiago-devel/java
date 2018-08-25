package com.rubyit.metaltrade.bs;

import static com.rubyit.metaltrade.Utils.getGson;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.rubyit.metaltrade.obj.Asset;

class Wallet {
	
	private String ID;
	private Set<MyAsset> assets;
	transient private Lock assetChangeLock;

	public Wallet() {
		ID = UUID.randomUUID().toString();
		assets = new HashSet<>();
		assetChangeLock = new ReentrantLock();
	}
	
	public void transfer(final Asset assetToTransfer, final Double amountToTranfer, final Account accountToTranfer) {
		
		if (assetToTransfer == null || amountToTranfer == null || accountToTranfer == null) {
			throw new RuntimeException("ERROR: unable to perform transfer with "
					+ "invalid fields: {assetToTransfer=" + assetToTransfer + ", amountToTranfer=" 
					+ amountToTranfer + ", accountToTranfer=" + accountToTranfer +"}");
		}
		
		assetChangeLock.lock();
		try {
			final MyAsset myAsset = extractAssetBy(assetToTransfer);
			final Wallet otherWallet = accountToTranfer.getWallet();
			final MyAsset otherAsset = otherWallet.extractAssetBy(assetToTransfer);
			final BigDecimal amount = new BigDecimal(amountToTranfer, new MathContext(8, RoundingMode.HALF_EVEN));
			
			try {
				myAsset.withdraw(amount);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assetChangeLock.unlock();
				throw new RuntimeException("ERROR: unable to perform transfer", e);
			}
			otherAsset.deposit(amount);
			this.addAsset(myAsset);
			otherWallet.addAsset(otherAsset);
		} finally {
			assetChangeLock.unlock();
		}
	}
	
	/*
	 * preventing directly access for a AssetBase.
	 */
	public MyAsset getAsset(final Asset asset) {
		
		for (MyAsset myAsset : assets) {
			if (myAsset.getAsset().equals(asset)) {
				return new MyAsset(myAsset);
			}
		}
		
		return null;
	}
	
	private MyAsset extractAssetBy(final Asset asset) {
		
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
	
	/*
	 * preventing directly access to the field.
	 */
	public String getID() {
		return ID.trim();
	}
	
	public Set getAssets() {
		return null;
	}
}

class MyAsset {
	
	private Asset asset;
	private BigDecimal balance;
	transient private Lock balanceChangeLock;
	transient private Condition sufficientFundsCondition;

	public MyAsset(final Asset asset) {
		this.asset = asset;
		balance = BigDecimal.ZERO;
		balanceChangeLock = new ReentrantLock();
		sufficientFundsCondition = balanceChangeLock.newCondition();
	}
	
	MyAsset(final MyAsset other) {
		this.asset = other.asset;
		this.balance = other.balance;
		balanceChangeLock = new ReentrantLock();
		sufficientFundsCondition = balanceChangeLock.newCondition();
	}

	void deposit(BigDecimal amount) {
		balanceChangeLock.lock();
		try {
			System.out.print("Depositing " + amount);
			BigDecimal newBalance = balance.add(amount);
			System.out.println(", new balance is " + newBalance);
			balance = newBalance;
			sufficientFundsCondition.signalAll();
		} finally {
			balanceChangeLock.unlock();
		}
	}

	void withdraw(BigDecimal amount) throws InterruptedException {
		balanceChangeLock.lock();
		try {
			while (balance.compareTo(amount) < 0) {
				sufficientFundsCondition.await();
			}
			System.out.print("Withdrawing " + amount);
			BigDecimal newBalance = balance.subtract(amount);;
			System.out.println(", new balance is " + newBalance);
			balance = newBalance;
		} finally {
			balanceChangeLock.unlock();
		}
	}

	public BigDecimal getBalance() {
		return balance;
	}
	
	public Asset getAsset() {
		return asset;
	}

	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("asset", asset);
		json.put("balance", balance);
		return getGson().toJson(json);
	}
}
