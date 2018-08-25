package com.rubyit.metaltrade.bs;

import com.rubyit.metaltrade.obj.Order;
import com.rubyit.metaltrade.obj.Order.Type;

public class OrderBook {

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
