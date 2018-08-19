package com.rubyit.metaltrade;

import static com.rubyit.metaltrade.m.Utils.getGson;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import com.rubyit.metaltrade.m.Asset;

public class Transaction {

	private String ID;
	private Asset asset;
	private BigDecimal amount;
	private Type type;
	
	public Transaction(final Asset asset, final Double amount, final Type type) {
		if (asset == null || type == null) {
			throw new RuntimeException("ERROR: cannot create an invalid transaction");
		}
		ID = UUID.randomUUID().toString();
		this.asset = asset;
		this.amount = new BigDecimal(amount, new MathContext(8, RoundingMode.HALF_EVEN));
		this.type = type;
	}
	
	public Transaction(final Asset asset, final Type type) {
		this(asset, (double) 0, type);
	}

	public Asset getAsset() {
		return asset;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public Type getType() {
		return type;
	}

	public enum Type {
		DEPOSIT, WITHDRAW
	}

	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", ID);
		json.put("asset", asset);
		json.put("amount", amount);
		json.put("type", type);
		return getGson().toJson(json);
	}
}
