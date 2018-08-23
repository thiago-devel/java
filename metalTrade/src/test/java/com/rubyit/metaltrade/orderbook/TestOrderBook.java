package com.rubyit.metaltrade.orderbook;

import static com.rubyit.metaltrade.Utils.getGson;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Test;

import com.rubyit.metaltrade.Asset;
import com.rubyit.metaltrade.MyAsset;
import com.rubyit.metaltrade.Wallet;
import com.rubyit.metaltrade.orderbook.Order.Type;

class OrderBook {

	public Order createOrder(String traderID, MyAsset amountAsset, MyAsset priceAsset, Type sell) {
		return this.createOrder(traderID, amountAsset.getAsset().getID(), amountAsset.getBalance().doubleValue(), priceAsset.getAsset().getID(), priceAsset.getBalance().doubleValue(), Order.Type.SELL);
	}

	public Order createOrder(String traderID, String assetID, Double amount, String exchangePriceAssetID,
			Double exchangePriceAssetAmount, Type sell) {
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


class Trader {

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

	public Wallet getWallet() {
		return myWallet;
	}

	public String getID() {
		return ID;
	}
}

public class TestOrderBook {
	
	@Test
	public void testSellAndBuyViaOrderBook() {
		String error1Message = "ERROR: Asset not found. Did you performed an add funds transaction before used it?";
		Trader thiago = new Trader("Thiago");
		Trader renata = new Trader("Renata");
		Asset GOLD = new Asset("GOLD");
		Asset USD = new Asset("USD");
		
		OrderBook orderbook = new OrderBook();
		Wallet rWallet = renata.getWallet();
		MyAsset rGold = new MyAsset(GOLD);
		
		// TODO: add tx to insert funds here
		rWallet.update(rGold);
		String rTraderID = renata.getID();
		assertNotNull(rTraderID);
		assertTrue(!rTraderID.trim().isEmpty());
		String rAssetID = rGold.getAsset().getID();
		assertNotNull(rAssetID);
		assertTrue(!rAssetID.trim().isEmpty());
		Double rAmount = rGold.getBalance().doubleValue(); // 1
		String rExchangePriceAssetID = USD.getID(); 
		Double rExchangePriceAssetAmount = 12.0;
		Order bidOrder = orderbook.createOrder(rTraderID, rAssetID, rAmount, rExchangePriceAssetID, rExchangePriceAssetAmount, Order.Type.SELL);
		assertNotNull(error1Message, rWallet.getAsset(GOLD.getID()));
		assertEquals(BigDecimal.valueOf(1.0), rWallet.getAsset(GOLD.getID()).getBalance());
		assertEquals(null, rWallet.getAsset(USD.getID()));
		
		Wallet tWallet = thiago.getWallet();
		MyAsset tUsd = new MyAsset(USD);
		// TODO: add tx to insert funds here
		tWallet.update(tUsd);
		String tTraderID = thiago.getID();
		assertNotNull(tTraderID);
		assertTrue(!tTraderID.trim().isEmpty());
		String tAssetID = tUsd.getAsset().getID();
		assertNotNull(tAssetID);
		assertTrue(!tAssetID.trim().isEmpty());
		Double tAmount = tUsd.getBalance().doubleValue(); //12
		String tExchangePriceAssetID = GOLD.getID(); 
		Double tExchangePriceAssetAmount = 1.0;
		Order askOrder = orderbook.createOrder(tTraderID, tAssetID, tAmount, tExchangePriceAssetID, tExchangePriceAssetAmount, Order.Type.BUY);
		assertNotNull(error1Message, tWallet.getAsset(USD.getID()));
		assertEquals(new Double(12.0), tWallet.getAsset(USD.getID()).getBalance());
		assertEquals(null, rWallet.getAsset(GOLD.getID()));
		
		assertEquals(bidOrder, orderbook.getBidOrder());
		assertEquals(askOrder, orderbook.getAskOrder());
		
		// TODO: add tx chain inside of this method
		orderbook.performMatcher();
		
		assertEquals(BigDecimal.ZERO, rWallet.getAsset(GOLD.getID()).getBalance());
		assertEquals(new BigDecimal(12.0), rWallet.getAsset(USD.getID()));
		
		assertEquals(BigDecimal.ZERO, tWallet.getAsset(USD.getID()).getBalance());
		assertEquals(new BigDecimal(1), rWallet.getAsset(GOLD.getID()));
	}
}