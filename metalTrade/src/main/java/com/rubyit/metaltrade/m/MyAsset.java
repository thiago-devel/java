package com.rubyit.metaltrade.m;

import static com.rubyit.metaltrade.m.Utils.getGson;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyAsset {
	private Asset asset;
	private BigDecimal balance;
	transient private Lock balanceChangeLock;
	transient private Condition sufficientFundsCondition;

	public MyAsset(Asset asset) {
		this.asset = asset;
		balance = BigDecimal.ZERO;
		balanceChangeLock = new ReentrantLock();
		sufficientFundsCondition = balanceChangeLock.newCondition();
	}

	public void deposit(BigDecimal amount) {
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

	public void withdraw(BigDecimal amount) throws InterruptedException {
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
