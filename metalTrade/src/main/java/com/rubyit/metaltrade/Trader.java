package com.rubyit.metaltrade;

import static com.rubyit.metaltrade.Utils.getGson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rubyit.metaltrade.obj.AssetType;
import com.rubyit.metaltrade.orderbook.Order;
import com.rubyit.metaltrade.orderbook.OrderBook;
import com.rubyit.metaltrade.orderbook.PairOrders;

public class Trader extends Account implements TraderType {

	protected String name;
	protected List<Order> createdOrders;
	protected List<Order> filledOrders;

	public Trader(String name) {
		super();
		if (name == null || name.trim().isEmpty()) {
			throw new RuntimeException("ERROR: unable to create a invalid name {name=" + name + "}");
		}
		this.name = name;
		this.createdOrders = new ArrayList<>();
		this.filledOrders = new ArrayList<>();
	}

	public String getID() {
		return super.getID();
	}

	public String getName() {
		return name;
	}

	public List<Order> getCreatedOrders() {
		return new ArrayList<>(createdOrders);
	}

	public List<Order> getFilledOrders() {
		return new ArrayList<>(filledOrders);
	}

	public Order createOrder(OrderBook orderbook, AssetType offeredAsset, Double offeredAmount, AssetType expectedAsset,
			Double expectedAssetUnitPrice) {

		if (orderbook == null || offeredAsset == null || offeredAmount == null || expectedAsset == null
				|| expectedAssetUnitPrice == null) {
			throw new RuntimeException("ERROR: unable to create order with invalid fields {orderbook=" + orderbook
					+ ", offeredAsset=" + offeredAsset + ", offeredAmount=" + offeredAmount + ", expectedAsset="
					+ expectedAsset + ", expectedAssetUnitPrice=" + expectedAssetUnitPrice + "}");
		}

		final BigDecimal assetTotalAmountPrice = BigDecimal.valueOf(offeredAmount)
				.multiply(BigDecimal.valueOf(expectedAssetUnitPrice));
		final Asset myAsset = myWallet.getAsset(offeredAsset);
		final BigDecimal balances = myAsset.getBalance().subtract(myAsset.getBlockedBalance());
		if (balances.compareTo(assetTotalAmountPrice) < 0) {

			/*throw new RuntimeException("ERROR: unable to create order for the asset {asset=" + offeredAsset.getName()
					+ "} offering the amount {offeredAmount=" + offeredAmount
					+ "} and expecting for {expectedAssetUnitPrice=" + expectedAssetUnitPrice
					+ "} having a balance {balance=" + myAsset.getBalance()
					+ "} lower than assetTotalAmountPrice {assetTotalAmountPrice=" + assetTotalAmountPrice + "}.");*/
			throw new RuntimeException("ERROR: unable to create order for the asset {asset="
					+ offeredAsset.getName() + "} offering the amount {offeredAmount="
					+ offeredAmount +"} and expecting for {expectedAssetUnitPrice="
					+ expectedAssetUnitPrice + "} having a balance "
					+ "{balance=" + myAsset.getBalance() + "} minus blocked balance "
					+ "{blockedBalance=" + myAsset.getBlockedBalance() + "} lower than assetTotalAmountPrice "
					+ "{assetTotalAmountPrice=" + assetTotalAmountPrice + "}.");
		}

		Order createdOrder = orderbook.createOrder(this, offeredAsset, offeredAmount, expectedAsset,
				expectedAssetUnitPrice);

		return createdOrder;
	}

	public void addCreatedOrder(Order order, OrderBook orderbook, PairOrders pair) {
		if (order.getType() == Order.Type.BUY) {
			orderbook.retrievePairOrders(pair.getPair()).addBuyOrder(order);
		} else { 
			orderbook.retrievePairOrders(pair.getPair()).addSellOrder(order);
		}
		myWallet.getAsset(order.getOfferedAsset()).blockBalance(order.getOfferedAmount());
		createdOrders.add(order);
	}

	public void removeCreatedOrder(Order order, OrderBook orderbook, PairOrders pair) {
		for (Order o : createdOrders) {

			if (o.getID().equals(order.getID())) {
				
				if (order.getType() == Order.Type.BUY) {
					orderbook.retrievePairOrders(pair.getPair()).removeBuyOrder(order);
				} else { 
					orderbook.retrievePairOrders(pair.getPair()).removeSellOrder(order);
				}
				myWallet.getAsset(order.getOfferedAsset()).unblockBalance(order.getOfferedAmount());
				createdOrders.remove(o);
				return;
			}
		}
	}

	public void fillOrder(Order filledOrder, String createdOrderID) {

		// perform withdraw and deposit
		AssetType offeredAsset = filledOrder.getOfferedAsset();
		BigDecimal offeredAmount = filledOrder.getOfferedAmount();
		AssetType expectedAsset = filledOrder.getExpectedAsset();
		BigDecimal totalAmountPrice = filledOrder.getAssetTotalAmountPrice();
		try {
			myWallet.getAsset(expectedAsset).withdraw(totalAmountPrice);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// assetChangeLock.unlock();
			throw new RuntimeException("ERROR: unable to perform transfer", e);
		}
		myWallet.getAsset(offeredAsset).deposit(offeredAmount);

		filledOrders.add(filledOrder);
	}

	@Override
	public String toString() {
		Map<String, Object> json = new LinkedHashMap<String, Object>();
		json.put("ID", getID());
		json.put("name", name);
		json.put("wallet", myWallet);
		json.put("createdOrders", createdOrders);
		json.put("filledOrders", filledOrders);
		return getGson().toJson(json);
	}
}
