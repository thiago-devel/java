package com.rubyit.metaltrade.orderbook;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.rubyit.metaltrade.orderbook.Order.Type;

class OrderBook {

	public Order createOrder(String rOwnerID, String rAssetID, Double rAmount, String rExchangePriceAssetID,
			Double rExchangePriceAssetAmount, Type sell) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getBidOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getAskOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public void performMatcher() {
		// TODO Auto-generated method stub
		
	}

}

class Order {

	public enum Type {
		BUY, SELL
	}
}

class Wallet {
	
	private List<Asset> assets;
	
	Wallet () {
		assets = new ArrayList<>();
	}
	
	public Asset getAsset(String assetName) {
		return null;
	}
	
	public void update(Asset asset) {
	}
}

class Owner {
	
	private String name;
	private Wallet wallet;
	
	Owner(String name) {
		this.name = name;
		wallet = new Wallet();
	}
	
	public String getName() {
		return name;
	}
	
	public Wallet getWallet() {
		return wallet;
	}

	public String getOwnerID() {
		// TODO Auto-generated method stub
		return null;
	}
}

class Asset {

	private String name;
	private Double amount;
	
	public String getName() {
		return name;
	}
	public Double getAmount() {
		return amount;
	}
	public Asset(String name, Double amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	public String getAssetID() {
		// TODO Auto-generated method stub
		return null;
	}
}

public class TestOrderBook {
	
	@Test
	public void testSellAndBuyViaOrderBook() {
		Owner thiago = new Owner("Thiago");
		Owner renata = new Owner("Renata");
		
		OrderBook orderbook = new OrderBook();
		Wallet rWallet = renata.getWallet();
		Asset rGold = new Asset("GOLD", 1.0);
		rWallet.update(rGold);
		String rOwnerID = renata.getOwnerID();
		assertNotNull(rOwnerID);
		assertTrue(!rOwnerID.trim().isEmpty());
		String rAssetID = rGold.getAssetID();
		assertNotNull(rAssetID);
		assertTrue(!rAssetID.trim().isEmpty());
		Double rAmount = rGold.getAmount(); // 1
		String rExchangePriceAssetID = "USD"; 
		Double rExchangePriceAssetAmount = 12.0;
		Order bidOrder = orderbook.createOrder(rOwnerID, rAssetID, rAmount, rExchangePriceAssetID, rExchangePriceAssetAmount, Order.Type.SELL);
		assertEquals(new Double(1.0), rWallet.getAsset("GOLD").getAmount());
		assertEquals(null, rWallet.getAsset("USD"));
		
		Wallet tWallet = thiago.getWallet();
		Asset tUsd = new Asset("USD", 12.0);
		tWallet.update(tUsd);
		String tOwnerID = thiago.getOwnerID();
		assertNotNull(tOwnerID);
		assertTrue(!tOwnerID.trim().isEmpty());
		String tAssetID = tUsd.getAssetID();
		assertNotNull(tAssetID);
		assertTrue(!tAssetID.trim().isEmpty());
		Double tAmount = tUsd.getAmount(); //12
		String tExchangePriceAssetID = "GOLD"; 
		Double tExchangePriceAssetAmount = 1.0;
		Order askOrder = orderbook.createOrder(tOwnerID, tAssetID, tAmount, tExchangePriceAssetID, tExchangePriceAssetAmount, Order.Type.BUY);
		assertEquals(new Double(12.0), tWallet.getAsset("USD").getAmount());
		assertEquals(null, rWallet.getAsset("GOLD"));
		
		assertEquals(bidOrder, orderbook.getBidOrder());
		assertEquals(askOrder, orderbook.getAskOrder());
		
		orderbook.performMatcher();
		
		assertEquals(new Double(0), rWallet.getAsset("GOLD").getAmount());
		assertEquals(new Double(12.0), rWallet.getAsset("USD"));
		
		assertEquals(new Double(0), tWallet.getAsset("USD").getAmount());
		assertEquals(new Double(1), rWallet.getAsset("GOLD"));
	}
}