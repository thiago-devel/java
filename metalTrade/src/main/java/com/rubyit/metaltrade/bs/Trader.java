package com.rubyit.metaltrade.bs;

import static com.rubyit.metaltrade.Utils.getGson;

import java.util.Map;
import java.util.TreeMap;

import com.rubyit.metaltrade.obj.Order;

public class Trader extends Account {

	protected XWallet myWallet;
	private OrderBook orderbook;
	protected String name;
	
	public Trader(String name, OrderBook orderbook) {
		super();
		if (name == null || name.trim().isEmpty() || orderbook == null) {
			throw new RuntimeException("ERROR: unable to create a invalid Trader with { name=" + name + ", orderbook=" + orderbook +  "}");
		}
		this.name = name;
		this.orderbook = orderbook;
	}
	
	@Override
	public String toString() {
		Map<String, Object> json = new TreeMap<String, Object>();
		json.put("ID", getID());
		json.put("name", name);
		json.put("myWallet", myWallet);
		return getGson().toJson(json);
	}

	public String getID() {
		return super.getID();
	}

	public Order createOrder(final String assetID, final Double amount, final String exchangePriceAssetID,
			final Double exchangePriceAssetAmount, final Order.Type type) {
		
		return orderbook.createOrder(getID(), assetID, amount, exchangePriceAssetID, exchangePriceAssetAmount, type);
	}
}