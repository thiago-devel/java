package com.rubyit.metaltrade.orderbook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.rubyit.metaltrade.TraderType;
import com.rubyit.metaltrade.obj.AssetType;
import com.rubyit.metaltrade.obj.Pair;

public class OrderBook {
	
	private Set<PairOrders> pairs;
	private Set<TraderType> traders;
	transient private Lock pairChangeLock;
	
	public OrderBook(Pair... pairs) {
		if (pairs == null || pairs.length == 0) {
			throw new RuntimeException("ERROR: unable to create a orderbook without at least one Pair");
		}
		this.pairs = new HashSet<>();
		this.traders = new HashSet<>();
		for (Pair pair : pairs) {
			this.pairs.add(new PairOrders(pair));
		}
		pairChangeLock = new ReentrantLock();
	}

	public Order createOrder(TraderType trader, AssetType offeredAsset, Double offeredAmount, AssetType expectedAsset,
			Double expectedAssetUnitPrice) {
		
		String offeredAssetID = offeredAsset.getID();
		String expectedAssetID = expectedAsset.getID();
		PairOrders pair = findPairBy(offeredAssetID, expectedAssetID);
		Order order = null;

		if (pair == null) {
			
			for (String id : Arrays.asList(offeredAssetID, expectedAssetID)) {
				pair = findPairBy(id);
				if (pair != null) {
					break;
				}
			}
		}
		if (pair == null) {
			throw new RuntimeException("ERROR: unable to find on the orderbook a "
					+ "compatible Pair for Assets with {offeredAsset=" + offeredAsset + 
					", expectedAsset=" + expectedAsset + "}");
		}
		
		String amountAssetID = pair.getPair().getAmountAsset().getID();
		String priceAssetID = pair.getPair().getPriceAsset().getID();
		if (
			(offeredAssetID.equals(amountAssetID))
			&& (expectedAssetID.equals(priceAssetID))
		   ) {
			
			traders.add(trader);
			Order.Type orderType = Order.Type.SELL;
			order = new Order(trader, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice, pair.getPair(), orderType);
			trader.addCreatedOrder(order);
			//look to buyOrders
			Order matchedOrder = (pair.retrieveBuyOrders().size() > 0) ? pair.retrieveBuyOrders().get(0) : null;
			if (matchedOrder == null) {
				
				pair.addSellOrder(order);
				return order;
			}
			
			return processMatchOrders(trader, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice, pair,
					orderType, matchedOrder, order);
		}
		if (
			(offeredAssetID.equals(priceAssetID))
			&& (expectedAssetID.equals(amountAssetID))
		   ) {
			
			traders.add(trader);
			Order.Type orderType = Order.Type.BUY;
			order = new Order(trader, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice, pair.getPair(), orderType);
			trader.addCreatedOrder(order);
			//look to buyOrders
			Order matchedOrder = (pair.retrieveSellOrders().size() > 0) ? pair.retrieveSellOrders().get(0) : null;
			if (matchedOrder == null) {
				
				pair.addBuyOrder(order);
				return order;
			}
			
			return processMatchOrders(trader, offeredAsset, offeredAmount, expectedAsset, expectedAssetUnitPrice, pair,
					orderType, matchedOrder, order);
		}
		
		throw new RuntimeException("ERROR: that order {offeredAsset=" + offeredAsset + ", expectedAsset=" + expectedAsset+ "} doesn't belongs to that pair {" + pair + "}");
	}

	private Order processMatchOrders(TraderType trader, AssetType offeredAsset, Double offeredAmount,
			AssetType expectedAsset, Double expectedAssetUnitPrice, PairOrders pair, Order.Type orderType,
			Order matchedOrder, Order order) {
		
		String otherTraderID = matchedOrder.getTraderID();
		if (trader.getID().equals(otherTraderID)) {
			pair.addSellOrder(order);
			return order;
		}
		
		for (TraderType otherTrader : traders) {
			
			if (otherTrader.getID().equals(otherTraderID)) {
				trader.fillOrder(matchedOrder, order.getID());
				otherTrader.fillOrder(order, matchedOrder.getID());
				trader.removeCreatedOrder(order);
				otherTrader.removeCreatedOrder(matchedOrder);
			}
		}
		
		return order;
	}
	
	public PairOrders findPairBy(String offeredAssetID, String expectedAssetID) {
		
		pairChangeLock.lock();
		try {
			for (PairOrders pair : pairs) {
				String amountAssetID = pair.getPair().getAmountAsset().getID();
				String priceAssetID = pair.getPair().getPriceAsset().getID();
				if ((amountAssetID.equals(offeredAssetID)) && (priceAssetID.equals(expectedAssetID))) {
					return pair;
				}
				if ((priceAssetID.equals(offeredAssetID)) && (amountAssetID.equals(expectedAssetID))) {
					return pair;
				}
			}
		} finally {
			pairChangeLock.unlock();
			
		}
		
		return null;
	}
	
	public PairOrders findPairBy(Optional<String> optionalPairName) {
		
		pairChangeLock.lock();
		try {
			for (PairOrders pair : pairs) {
				String pairName = pair.getPair().getPairName();
				if (optionalPairName.get().equals(pairName)) {
					return pair;
				}
			}
		} finally {
			pairChangeLock.unlock();
			
		}
		
		return null;
	}
	
	public PairOrders findPairBy(String assetID) {
		
		pairChangeLock.lock();
		try {
			for (PairOrders pair : pairs) {
				String amountAssetID = pair.getPair().getAmountAsset().getID();
				String priceAssetID = pair.getPair().getPriceAsset().getID();
				if (amountAssetID.equals(assetID)) {
					return pair;//new CurrencyPair(currencyPair);
				}
				if (priceAssetID.equals(assetID)) {
					return pair;//new CurrencyPair(currencyPair);
				}
			}
		} finally {
			pairChangeLock.unlock();
			
		}
		
		return null;
	}
	
}


