package com.rubyit.metaltrade;
import static com.rubyit.metaltrade.Utils.getGson;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.rubyit.metaltrade.obj.AssetType;

public class Asset {
	
	private AssetType asset;
	private BigDecimal balance;
	transient private Lock balanceChangeLock;
	transient private Condition sufficientFundsCondition;
	
	public Asset(AssetType asset) {
		this.asset = asset;
		balance = BigDecimal.ZERO;
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
	public AssetType getAsset() {
		return asset;
	}
	@Override
	public String toString() {
		Map<String, Object> json = new LinkedHashMap<String, Object>();
		json.put("assetType", asset);
		json.put("balance", balance);
		return getGson().toJson(json);
	}
}
